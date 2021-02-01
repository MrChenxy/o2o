package com.bjpowernode.o2o.utils;

import com.bjpowernode.o2o.domain.Shop;
import com.bjpowernode.o2o.dto.ImageHolder;

/**
 * @author Mr.chenxy
 * @date 2021/2/1
 */
public class ShopSetImgUtil {
    public static void addShopImg(Shop shop, ImageHolder thumbnail) {
        // 获取shop图片目录的相对值路径
        String dest = PathUtil.getShopImagePath(shop.getShopId());
        String shopImgAddr = ImageUtil.generateThumbnail(thumbnail, dest);
        shop.setShopImg(shopImgAddr);
    }
}
