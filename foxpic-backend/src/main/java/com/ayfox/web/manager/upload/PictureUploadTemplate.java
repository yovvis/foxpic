package com.ayfox.web.manager.upload;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.ayfox.web.config.CosClientConfig;
import com.ayfox.web.exception.BusinessException;
import com.ayfox.web.exception.ErrorCode;
import com.ayfox.web.manager.CosManager;
import com.ayfox.web.model.dto.file.UploadPictureResult;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.model.ciModel.persistence.CIObject;
import com.qcloud.cos.model.ciModel.persistence.ImageInfo;
import com.qcloud.cos.model.ciModel.persistence.ProcessResults;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * 图片上传模板
 */

public abstract class PictureUploadTemplate {

    @Resource
    private CosClientConfig cosClientConfig;

    @Resource
    private CosManager cosManager;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 上传图片
     *
     * @param inputSource      文件
     * @param uploadPathPrefix 上传路径前缀
     * @return
     */
    public UploadPictureResult uploadPicture(Object inputSource, String uploadPathPrefix) {
        // 1. 校验图片
        validPicture(inputSource);
        // 2. 图片上传地址
        String uuid = RandomUtil.randomString(16);
        String originalFilename = getOriginFilename(inputSource);
        // 自己拼接文件上传路径，而不是使用原始文件名称，可以增强安全性
        String uploadFilename = String.format("%s_%s.%s", DateUtil.formatDate(new Date()), uuid,
                FileUtil.getSuffix(originalFilename));
        String uploadPath = String.format("/%s/%s", uploadPathPrefix, uploadFilename);
        File file = null;
        try {
            // 3. 创建临时文件，获取文件到服务器
            file = File.createTempFile(uploadPath, null);
            // 处理文件来源
            processFile(inputSource, file);
            // 4. 上传图片到对象存储
            PutObjectResult putObjectResult = cosManager.putPictureObject(uploadPath, file);
            // 5. 获取图片信息对象，封装返回结果
            ImageInfo imageInfo = putObjectResult.getCiUploadResult().getOriginalInfo().getImageInfo();
            // 获取处理结果
            ProcessResults processResults = putObjectResult.getCiUploadResult().getProcessResults();
            List<CIObject> objectList = processResults.getObjectList();
            if (CollUtil.isNotEmpty(objectList)) {
                // 压缩后的文件信息
                CIObject compressCiObject = objectList.get(0);
                // 缩略图默认就是压缩的图
                CIObject thumbnailCiObject = compressCiObject;
                // 缩略图的文件信息
                if (objectList.size() > 1) {
                    thumbnailCiObject = objectList.get(1);
                }
                // 封装压缩图的返回结果
                return buildResult(originalFilename, compressCiObject, thumbnailCiObject, imageInfo);
            }
            return buildResult(originalFilename, file, uploadPath, imageInfo);
        } catch (Exception e) {
            logger.error("图片上传到对象存储失败", e);
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "上传失败");
        } finally {
            // 6. 临时文件清理
            this.deleteTempFile(file);
        }

    }

    /**
     * 校验输入源（本地文件或 URL）
     */
    protected abstract void validPicture(Object inputSource);

    /**
     * 获取输入源的原始文件名
     */
    protected abstract String getOriginFilename(Object inputSource);

    /**
     * 处理输入源并生成本地临时文件
     */
    protected abstract void processFile(Object inputSource, File file) throws Exception;

    /**
     * 封装返回结果
     *
     * @param originalFilename
     * @param file
     * @param uploadPath
     * @param imageInfo        对象存储返回的图片信息
     * @return
     */
    private UploadPictureResult buildResult(String originalFilename, File file, String uploadPath, ImageInfo imageInfo) {
        // 计算宽高
        int picWidth = imageInfo.getWidth();
        int picHeight = imageInfo.getHeight();
        double picScale = NumberUtil.round(picWidth * 1.0 / picHeight, 2).doubleValue();
        // 封装返回结果
        UploadPictureResult uploadPictureResult = new UploadPictureResult();
        uploadPictureResult.setUrl(cosClientConfig.getHost() + "/" + uploadPath);
        uploadPictureResult.setPicName(FileUtil.mainName(originalFilename));
        uploadPictureResult.setPicSize(FileUtil.size(file));
        uploadPictureResult.setPicWidth(picWidth);
        uploadPictureResult.setPicHeight(picHeight);
        uploadPictureResult.setPicScale(picScale);
        uploadPictureResult.setPicFormat(imageInfo.getFormat());
        uploadPictureResult.setOriginFormat(StrUtil.isBlank(imageInfo.getFormat()) ? "" : imageInfo.getFormat().toLowerCase());
        uploadPictureResult.setPicColor(imageInfo.getAve());

        // 返回可访问的地址
        return uploadPictureResult;
    }

    /**
     * 封装返回结果
     *
     * @param originalFilename
     * @param compressCiObject  webp压缩
     * @param thumbnailCiObject 缩略图
     * @param imageInfo         图片信息
     * @return
     */
    private UploadPictureResult buildResult(String originalFilename, CIObject compressCiObject, CIObject thumbnailCiObject, ImageInfo imageInfo) {

        // 计算宽高
        int picWidth = compressCiObject.getWidth();
        int picHeight = compressCiObject.getHeight();
        double picScale = NumberUtil.round(picWidth * 1.0 / picHeight, 2).doubleValue();
        // 封装返回结果
        UploadPictureResult uploadPictureResult = new UploadPictureResult();
        // 设置压缩后的原图地址
        uploadPictureResult.setUrl(cosClientConfig.getHost() + "/" + compressCiObject.getKey());
        uploadPictureResult.setThumbnailUrl("");
        uploadPictureResult.setPicName(FileUtil.mainName(originalFilename));
        uploadPictureResult.setPicSize(compressCiObject.getSize().longValue());
        uploadPictureResult.setPicWidth(picWidth);
        uploadPictureResult.setPicHeight(picHeight);
        uploadPictureResult.setPicScale(picScale);
        uploadPictureResult.setPicFormat(compressCiObject.getFormat());
        uploadPictureResult.setOriginFormat(StrUtil.isBlank(imageInfo.getFormat()) ? "" : imageInfo.getFormat().toLowerCase());
        uploadPictureResult.setPicColor(imageInfo.getAve());
        // 设置缩略图地址
        uploadPictureResult.setThumbnailUrl(cosClientConfig.getHost() + "/" + thumbnailCiObject.getKey());
        // 返回可访问的地址
        return uploadPictureResult;
    }

    /**
     * 清理临时文件
     *
     * @param file
     */
    public void deleteTempFile(File file) {
        if (file == null) {
            return;
        }
        // 删除临时文件
        boolean deleteResult = file.delete();
        if (!deleteResult) {
            logger.error("file delete error, filepath = {}", file.getAbsolutePath());
        }
    }
}













