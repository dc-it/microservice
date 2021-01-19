package com.mapc.nacos.springboot.config.sample;

import com.alibaba.nacos.api.config.annotation.NacosConfigListener;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import lombok.Data;
import org.springframework.context.annotation.Configuration;

/**
 * nacos配置动态刷新监听测试
 *
 * @author duchao
 */
@Data
@Configuration
public class TestConfiguration {

    @NacosValue(value = "${people.count:0}", autoRefreshed = true)
    private String count;

    @NacosConfigListener(dataId = "listener.properties", groupId = "${spring.application.name}", timeout = 3000)
    public void onChange(String newContent) throws Exception {
        System.out.println("onChange : " + newContent);
    }
}
