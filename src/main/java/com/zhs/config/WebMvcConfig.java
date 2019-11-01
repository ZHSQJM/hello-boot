package com.zhs.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author: zhouhuasheng
 * @date: 2019/11/1 17:36
 * @Description:
 * @version: 1.0
 */

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        //注意前面要加file,不然是访问不了的
        registry.addResourceHandler("/image/**").addResourceLocations("file:"+"F:");

    }

}
