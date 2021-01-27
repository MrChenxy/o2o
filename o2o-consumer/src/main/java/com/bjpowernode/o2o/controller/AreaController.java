package com.bjpowernode.o2o.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bjpowernode.o2o.domain.Area;
import com.bjpowernode.o2o.service.AreaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Mr.chenxy
 * @date 2021/1/27
 */
@Controller
public class AreaController {

    @Reference(interfaceClass = AreaService.class,version = "1.0.0",check = false)
    private AreaService areaService;

    @RequestMapping("/superAdmin/listArea")
    @ResponseBody
    public Object listArea(){
        List<Area> allArea = areaService.getAllArea();
        return allArea;
    }

}
