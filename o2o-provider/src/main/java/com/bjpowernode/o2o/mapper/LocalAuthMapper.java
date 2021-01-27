package com.bjpowernode.o2o.mapper;

import com.bjpowernode.o2o.domain.LocalAuth;

public interface LocalAuthMapper {
    int deleteByPrimaryKey(Integer localAuthId);

    int insert(LocalAuth record);

    int insertSelective(LocalAuth record);

    LocalAuth selectByPrimaryKey(Integer localAuthId);

    int updateByPrimaryKeySelective(LocalAuth record);

    int updateByPrimaryKey(LocalAuth record);
}