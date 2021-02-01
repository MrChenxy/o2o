package com.bjpowernode.o2o.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.bjpowernode.o2o.domain.Shop;
import com.bjpowernode.o2o.dto.ImageHolder;
import com.bjpowernode.o2o.dto.ShopExecution;
import com.bjpowernode.o2o.enums.ShopStateEnum;
import com.bjpowernode.o2o.exception.ShopOperationException;
import com.bjpowernode.o2o.mapper.ShopMapper;
import com.bjpowernode.o2o.service.ShopService;
import com.bjpowernode.o2o.utils.ImageUtil;
import com.bjpowernode.o2o.utils.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author Mr.chenxy
 * @date 2021/1/27
 */
@Component
@Transactional
@Service(interfaceClass = ShopService.class, version = "1.0.0", timeout = 15000)
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopMapper shopMapper;


    @Override
    public ShopExecution addShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException {
        // 空值判断
        if (shop == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        try {
            // 给店铺信息赋初始值
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());
            // 添加店铺信息
            int effectedNum = shopMapper.insertSelective(shop);
            if (effectedNum <= 0) {
                throw new ShopOperationException("店铺创建失败");
            } /*else {
                if (thumbnail.getImage() != null) {
                    // 存储图片
                    try {
                        addShopImg(shop, thumbnail);
                    } catch (Exception e) {
                        throw new ShopOperationException("addShopImg error:" + e.getMessage());
                    }
                    // 更新店铺的图片地址
                    effectedNum = shopMapper.updateByPrimaryKeySelective(shop);
                    if (effectedNum <= 0) {
                        throw new ShopOperationException("更新图片地址失败");
                    }
                }
            }*/
        } catch (Exception e) {
            throw new ShopOperationException("addShop error:" + e.getMessage());
        }
        return new ShopExecution(ShopStateEnum.CHECK, shop);
    }

    public void addShopImg(Shop shop, ImageHolder thumbnail) {
        // 获取shop图片目录的相对值路径
        String dest = PathUtil.getShopImagePath(shop.getShopId());
        String shopImgAddr = ImageUtil.generateThumbnail(thumbnail, dest);
        shop.setShopImg(shopImgAddr);
    }

    @Override
    public int updateShop(Shop shop) {
        return shopMapper.updateByPrimaryKeySelective(shop);
    }


}

