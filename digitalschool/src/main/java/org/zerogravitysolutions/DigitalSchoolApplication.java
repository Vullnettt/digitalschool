package org.zerogravitysolutions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DigitalSchoolApplication {
    public static void main(String[] args) {
        SpringApplication.run(DigitalSchoolApplication.class, args);
    }
}