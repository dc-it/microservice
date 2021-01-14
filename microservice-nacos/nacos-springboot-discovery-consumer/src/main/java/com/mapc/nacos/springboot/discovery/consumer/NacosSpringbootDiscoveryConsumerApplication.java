package com.mapc.nacos.springboot.discovery.consumer;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

@Slf4j
@SpringBootApplication
public class NacosSpringbootDiscoveryConsumerApplication implements ApplicationListener<ContextRefreshedEvent> {

    public static void main(String[] args) {
        SpringApplication.run(NacosSpringbootDiscoveryConsumerApplication.class, args);
    }

    @NacosInjected
    private NamingService namingService;

    /**
     * 容器启动完成消费者订阅服务
     *
     * @param event
     */
    @SneakyThrows
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        namingService.subscribe("nacos-springboot-discovery-provider", e -> {
            try {
                log.info("订阅服务{}成功，实例如下：{}", "nacos-springboot-discovery-provider", JSONArray.toJSONString(namingService.getAllInstances(namingService.getSubscribeServices().get(0).getName())));
            } catch (NacosException nacosException) {
                nacosException.printStackTrace();
            }
        });

    }
}
