package com.asche.wetalk.controller;

import com.asche.wetalk.bean.EmailBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import static com.asche.wetalk.util.PrintUtils.println;

@Controller
public class EmailController {

    @Autowired
    JavaMailSender mailSender;

    @GetMapping("/email")
    @ResponseBody
    public String mailTest(){
        EmailBean emailBean = new EmailBean();
        emailBean.setFrom("apknet@163.com");
        emailBean.setTo("asche910@163.com");
        emailBean.setName("Asche");
        emailBean.setSubject("Hello, World!");
        emailBean.setMessageText("-----------------Test Message!----------------");
        sendEmail(emailBean);
        return "Send success!";
    }

    public void sendEmail(EmailBean bean){
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setFrom(bean.getFrom(), bean.getName());
            helper.setTo(bean.getTo());
            helper.setSubject(bean.getSubject());
            helper.setText(bean.getMessageText());
            mailSender.send(message);

            println("Email sent success!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
