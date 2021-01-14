package com.mapc.nacos.springboot.discovery.provider2.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试服务控制器
 *
 * @author duchao
 */
@RestController
public class TestController {

    @Value("${spring.application.name}")
    private String serviceName;

    @Value("${server.port}")
    private int servicePort;

    @GetMapping("hello")
    public String hello(){
        return String.format("你好，我是Nacos服务%s:%s!",serviceName,servicePort);
    }

}
