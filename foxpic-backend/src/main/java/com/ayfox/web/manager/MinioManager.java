package com.ayfox.web.manager;

import com.ayfox.web.config.MinioClientConfig;
import io.minio.*;
import io.minio.http.Method;
import io.minio.messages.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * minio对象存储操作
 *
 * @author yovvis
 * @date 2024/5/25
 */
@Component
public class MinioManager {
    Logger logger = LoggerFactory.getLogger(MinioManager.class);

    @Resource
    private MinioClientConfig minioClientConfig;

    @Resource
    private MinioClient minioClient;

    /**
     * 上传对象
     *
     * @param key
     * @param file
     */
    public ObjectWriteResponse uploadObject(String key, File file) {
        ObjectWriteResponse objectWriteResponse;
        try {
            UploadObjectArgs args = UploadObjectArgs.builder().bucket(minioClientConfig.getBucket()).object(key).filename(file.getAbsolutePath()).build();
            objectWriteResponse = minioClient.uploadObject(args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return objectWriteResponse;
    }

    /**
     * 大文件上传
     *
     * @param is
     * @param fileName
     * @param contentType
     */
    public void uploadObject(InputStream is, String fileName, String contentType) {
        try {
            PutObjectArgs putObjectArgs = PutObjectArgs.builder().bucket(minioClientConfig.getBucket()).object(fileName).contentType(contentType).stream(is, is.available(), -1).build();
            minioClient.putObject(putObjectArgs);
            is.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 获取列表
     *
     * @return
     */
    public List<String> listObjects() {
        List<String> list = new ArrayList<>();
        try {

            ListObjectsArgs listObjectsArgs = ListObjectsArgs.builder().bucket(minioClientConfig.getBucket()).build();
            Iterable<Result<Item>> results = minioClient.listObjects(listObjectsArgs);
            for (Result<Item> result : results) {
                Item item = result.get();
                list.add(item.objectName());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    /**
     * 删除
     *
     * @param objectName
     */
    public void deleteObject(String objectName) {
        try {
            RemoveObjectArgs removeObjectArgs = RemoveObjectArgs.builder().bucket(minioClientConfig.getBucket()).object(objectName).build();
            minioClient.removeObject(removeObjectArgs);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //获取minio中地址
    public String getObjectUrl(String objectName) {
        try {
            GetPresignedObjectUrlArgs getPresignedObjectUrlArgs = GetPresignedObjectUrlArgs.builder().method(Method.GET).bucket(minioClientConfig.getBucket()).object(objectName).expiry(7, TimeUnit.DAYS).build();
            return minioClient.getPresignedObjectUrl(getPresignedObjectUrlArgs);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 下载minio服务的文件
     *
     * @param objectName
     * @return
     */
    public InputStream getObject(String objectName) {
        try {
            GetObjectArgs getObjectArgs = GetObjectArgs.builder().bucket(minioClientConfig.getBucket()).object(objectName).build();
            return minioClient.getObject(getObjectArgs);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
