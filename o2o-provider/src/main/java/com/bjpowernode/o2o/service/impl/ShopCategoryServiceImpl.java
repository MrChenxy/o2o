package com.bjpowernode.o2o.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.bjpowernode.o2o.domain.ShopCategory;
import com.bjpowernode.o2o.mapper.ShopCategoryMapper;
import com.bjpowernode.o2o.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Mr.chenxy
 * @date 2021/1/27
 */
@Component
@Service(interfaceClass = ShopCategoryService.class,version = "1.0.0",timeout = 15000)
public class ShopCategoryServiceImpl implements ShopCategoryService {

    @Autowired
    private ShopCategoryMapper shopCategoryMapper;

    @Override
    public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition) {
        return shopCategoryMapper.selectShopCategory(shopCategoryCondition);
    }
}
