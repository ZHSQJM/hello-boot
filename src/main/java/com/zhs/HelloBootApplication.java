package com.zhs;

import com.zhs.utils.SnowflakeIdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HelloBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloBootApplication.class, args);
    }

    @Bean
    public SnowflakeIdWorker getSnowflakeIdWorker(){

        return new SnowflakeIdWorker(1,1);
    }
}
