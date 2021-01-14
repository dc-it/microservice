package com.mapc.nacos.springboot.discovery.provider;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.naming.NamingService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

@SpringBootApplication
public class NacosSpringbootDiscoveryProviderApplication implements ApplicationListener<ContextRefreshedEvent> {

    public static void main(String[] args) {
        SpringApplication.run(NacosSpringbootDiscoveryProviderApplication.class, args);
    }

    @NacosInjected
    private NamingService namingService;

    @Value("${spring.application.name}")
    private String serviceName;

    @Value("${server.port}")
    private int servicePort;

    /**
     * 容器启动完成服务注册到Nacos注册中心
     *
     * @param event
     */
    @SneakyThrows
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        namingService.registerInstance(serviceName, "127.0.0.1", servicePort);
    }
}
