package com.bjpowernode.o2o.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;


/**
 * @author Mr.chenxy
 * @date 2021/1/31
 */
//@Configuration
//@EnableAutoConfiguration(exclude = {MultipartAutoConfiguration.class})
public class MultipartConfig {

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver getCommonsMultipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(20971520);
        multipartResolver.setMaxInMemorySize(1048576);
        multipartResolver.setDefaultEncoding("utf-8");
        return multipartResolver;
    }
}
