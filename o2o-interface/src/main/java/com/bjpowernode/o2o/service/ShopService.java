package com.bjpowernode.o2o.service;

import com.bjpowernode.o2o.domain.Shop;
import com.bjpowernode.o2o.dto.ImageHolder;
import com.bjpowernode.o2o.dto.ShopExecution;
import com.bjpowernode.o2o.exception.ShopOperationException;

/**
 * Mr.chenxy
 * 2021/1/27
 */
public interface ShopService {

    ShopExecution addShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException;
}



