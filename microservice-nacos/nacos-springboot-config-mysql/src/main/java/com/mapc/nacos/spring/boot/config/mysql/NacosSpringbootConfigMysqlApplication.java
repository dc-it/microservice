package com.mapc.nacos.spring.boot.config.mysql;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@NacosPropertySource(dataId = "mysql.properties",autoRefreshed = true)
public class NacosSpringbootConfigMysqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(NacosSpringbootConfigMysqlApplication.class, args);
	}

}
