package com.asche.wetalk;

import com.asche.wetalk.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.asche.wetalk.util.PrintUtils.println;

@SpringBootApplication
public class WetalkApplication {


    @Autowired
    RedisService redisService;

    public static void main(String[] args) {
        SpringApplication.run(WetalkApplication.class, args);

    }

    @Bean
    CommandLineRunner init(){

        String key = "hello";
        String str = "Hello, World!";
        redisService.write(key, str);

        println(redisService.get(key));

        return args -> println("------------------------> Main Start!");
    }
}
