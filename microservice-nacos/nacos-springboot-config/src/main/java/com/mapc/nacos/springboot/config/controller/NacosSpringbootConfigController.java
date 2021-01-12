package com.mapc.nacos.springboot.config.controller;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.api.exception.NacosException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Properties;

/**
 * 动态配置
 *
 * @author duchao
 */
@RestController
@RequestMapping("config")
public class NacosSpringbootConfigController {

    @NacosValue(value = "${useLocalCache:false}", autoRefreshed = true)
    private boolean useLocalCache;

    @Value("${nacos.config.server-addr}")
    private String nacosUrl;

    @Value("${nacos.config.namespace}")
    private String namespace;

    @NacosInjected
    private ConfigService configService;

    @GetMapping("get")
    public boolean get() {
        return useLocalCache;
    }

    @GetMapping("createConfigByApi")
    public HttpStatus createConfigByApi(Boolean useLocalCache) {
        RequestEntity requestEntity = new RequestEntity(null, HttpMethod.POST, URI.create("http://"+nacosUrl + "/nacos/v1/cs/configs?dataId=example.properties&group=DEFAULT_GROUP&content=useLocalCache="+useLocalCache.toString()));
        ResponseEntity<Object> responseEntity = new RestTemplate().exchange(requestEntity, Object.class);
        return responseEntity.getStatusCode();
    }

    @GetMapping("createConfigBySdk")
    public void createConfigBySdk(Boolean useLocalCache) {
        try {
            // 初始化配置服务，控制台通过示例代码自动获取下面参数
            String dataId = "example.properties";
            String group = "DEFAULT_GROUP";
            Properties properties = new Properties();
            properties.put("serverAddr", nacosUrl);
            properties.put("namespace", namespace);
            ConfigService configService = NacosFactory.createConfigService(properties);
            boolean isPublishOk = configService.publishConfig(dataId, group, "useLocalCache="+useLocalCache);
            System.out.println(isPublishOk);
        } catch (NacosException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("createConfigByInjected")
    public void createConfigByInjected(Boolean useLocalCache) {
        try {
            // 初始化配置服务，控制台通过示例代码自动获取下面参数
            String dataId = "example.properties";
            String group = "DEFAULT_GROUP";
            boolean isPublishOk = configService.publishConfig(dataId, group, "useLocalCache="+useLocalCache);
            System.out.println(isPublishOk);
        } catch (NacosException e) {
            e.printStackTrace();
        }
    }
}
