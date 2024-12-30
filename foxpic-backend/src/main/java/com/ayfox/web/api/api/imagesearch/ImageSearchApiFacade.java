package com.ayfox.web.api.api.imagesearch;

import com.ayfox.web.api.api.imagesearch.model.ImageSearchResult;
import com.ayfox.web.api.api.imagesearch.sub.GetImageFirstUrlApi;
import com.ayfox.web.api.api.imagesearch.sub.GetImageListApi;
import com.ayfox.web.api.api.imagesearch.sub.GetImagePageUrlApi;

import java.util.List;

public class ImageSearchApiFacade {

    /**
     * 搜索图片
     * @param imageUrl
     * @return
     */
    public static List<ImageSearchResult> searchImage(String imageUrl) {
        String imagePageUrl = GetImagePageUrlApi.getImagePageUrl(imageUrl);
        String imageFirstUrl = GetImageFirstUrlApi.getImageFirstUrl(imagePageUrl);
        List<ImageSearchResult> imageList = GetImageListApi.getImageList(imageFirstUrl);
        return imageList;
    }

    public static void main(String[] args) {
        List<ImageSearchResult> imageList = searchImage("https://www.codefather.cn/logo.png");
        System.out.println("结果列表" + imageList);
    }
}
