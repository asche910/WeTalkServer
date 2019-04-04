package com.asche.wetalk;

import com.asche.wetalk.bean.EmailBean;
import com.asche.wetalk.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.asche.wetalk.util.PrintUtils.println;

@SpringBootApplication
public class WetalkApplication {

  /*  @Autowired
    private static EmailService emailService;*/

    public static void main(String[] args) {
        SpringApplication.run(WetalkApplication.class, args);

        SpringApplication.run(Test.class, args);

     /*   EmailBean emailBean = new EmailBean();
        emailBean.setFrom("asche910@163.com");
        emailBean.setTo("apknet@163.com");
        emailBean.setSubject("Hello, World!");
        emailBean.setMessageText("-----------------Test Message!----------------");

        emailService.sendMail(emailBean);*/
    }

    @Bean
    CommandLineRunner init(){
        return args -> {
            println("------------------------> Main Start!");
        };
    }
}
