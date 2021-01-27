package com.bjpowernode.o2o.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bjpowernode.o2o.domain.PersonInfo;
import com.bjpowernode.o2o.service.PersonInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Mr.chenxy
 * @date 2021/1/27
 */
@Controller
public class PersonInfoController {

    @Reference(interfaceClass = PersonInfoService.class,version = "1.0.0",check = false)
    private PersonInfoService personInfoService;

    @RequestMapping("/personInfo/{id}")
    @ResponseBody
    public Object personInfoShow(@PathVariable("id") Integer id){
        PersonInfo person = personInfoService.findPersonInfoById(id);
        return person;
    }



}
