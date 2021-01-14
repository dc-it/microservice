package com.mapc.nacos.springboot.discovery.consumer.controller;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Nacos服务发现与消费
 *
 * @author duchao
 */
@Slf4j
@RestController
public class TestController {

    @NacosInjected
    private NamingService namingService;

    @GetMapping("test")
    public String hello() throws NacosException {
        Instance instance = namingService.selectOneHealthyInstance("nacos-springboot-discovery-provider");
        RestTemplate template = new RestTemplate();
        String url = String.format("http://%s:%d/hello", instance.getIp(), instance.getPort());
        String result = template.getForObject(url, String.class);
        return result;
    }
}
