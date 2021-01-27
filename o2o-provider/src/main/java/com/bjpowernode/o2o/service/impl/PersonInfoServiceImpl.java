package com.bjpowernode.o2o.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.bjpowernode.o2o.domain.PersonInfo;
import com.bjpowernode.o2o.mapper.PersonInfoMapper;
import com.bjpowernode.o2o.service.PersonInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Mr.chenxy
 * @date 2021/1/27
 */
@Component
@Service(interfaceClass = PersonInfoService.class,version = "1.0.0",timeout = 15000)
public class PersonInfoServiceImpl implements PersonInfoService {
    @Autowired
    private PersonInfoMapper personInfoMapper;

    @Override
    public PersonInfo findPersonInfoById(Integer id) {
        PersonInfo personInfo = personInfoMapper.selectByPrimaryKey(id);
        return personInfo;
    }
}
