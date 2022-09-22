package com.codecool.snoopnews;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SnoopNewsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SnoopNewsApplication.class, args);
    }

}
