package com.asche.wetalk;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.asche.wetalk.util.PrintUtils.println;

@SpringBootApplication
public class WetalkApplication {

    public static void main(String[] args) {
        SpringApplication.run(WetalkApplication.class, args);

    }

    @Bean
    CommandLineRunner init(){
        return args -> println("------------------------> Main Start!");
    }
}
