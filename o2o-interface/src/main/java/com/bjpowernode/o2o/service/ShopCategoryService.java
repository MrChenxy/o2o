package com.bjpowernode.o2o.service;

import com.bjpowernode.o2o.domain.ShopCategory;

import java.util.List;



public interface ShopCategoryService {

	String SCLISTKEY = "shopcategorylist";

	List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition);
}
