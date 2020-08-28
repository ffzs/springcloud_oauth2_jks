package com.ffzs.sso_auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
@EnableSwagger2
public class SsoAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsoAuthApplication.class, args);
    }

}
