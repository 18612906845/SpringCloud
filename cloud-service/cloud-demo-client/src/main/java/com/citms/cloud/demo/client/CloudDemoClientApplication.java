package com.citms.cloud.demo.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = {"com.citms.cloud.demo.server"})
public class CloudDemoClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudDemoClientApplication.class, args);
    }

}
