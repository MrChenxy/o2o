package com.bjpowernode.o2o.mapper;

import com.bjpowernode.o2o.domain.ShopCategory;

public interface ShopCategoryMapper {
    int deleteByPrimaryKey(Integer shopCategoryId);

    int insert(ShopCategory record);

    int insertSelective(ShopCategory record);

    ShopCategory selectByPrimaryKey(Integer shopCategoryId);

    int updateByPrimaryKeySelective(ShopCategory record);

    int updateByPrimaryKey(ShopCategory record);
}