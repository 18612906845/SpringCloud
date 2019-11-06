package com.citms.cloud.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author wanglei@citms.cn
 * @date 2019/11/06 10:16
 */
@SpringBootApplication
@EnableDiscoveryClient
public class CloudDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudDemoApplication.class, args);
    }
}
