package com.mapc.nacos.springboot.config;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@NacosPropertySource(dataId = "${nacos.config.data-id}", groupId = "${spring.application.name}", autoRefreshed = true)
public class NacosSpringbootConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosSpringbootConfigApplication.class, args);
    }

}
