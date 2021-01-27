package com.bjpowernode.o2o.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.bjpowernode.o2o.domain.Area;
import com.bjpowernode.o2o.mapper.AreaMapper;
import com.bjpowernode.o2o.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Mr.chenxy
 * @date 2021/1/27
 */
@Component
@Service(interfaceClass = AreaService.class,version = "1.0.0",timeout = 15000)
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaMapper areaMapper;

    @Override
    public List<Area> getAllArea() {
        return areaMapper.selectAllArea();
    }
}
