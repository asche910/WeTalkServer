package com.asche.wetalk;

import com.asche.wetalk.service.RedisService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.asche.wetalk.util.PrintUtils.println;

@MapperScan("com.asche.wetalk.mapper")
@SpringBootApplication
public class WetalkApplication {


    @Autowired
    RedisService redisService;

    public static void main(String[] args) {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(WetalkApplication.class, args);

    }

    @Bean
    CommandLineRunner init(){

        String key = "hello";
        String str = "Redis ---> Hello, World!";
        redisService.write(key, str);

        println(redisService.get(key));

        return args -> println("------------------------> Main Start!");
    }
}
