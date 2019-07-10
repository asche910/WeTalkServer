package com.asche.wetalk.controller;

import com.asche.wetalk.entity.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.internet.MimeMessage;

import static com.asche.wetalk.util.PrintUtils.println;

@Controller
public class EmailController {

    @Autowired
    JavaMailSender mailSender;

    @GetMapping("/email")
    @ResponseBody
    public String mailTest(){
        Email email = new Email();
        email.setFrom("apknet@163.com");
        email.setTo("asche910@163.com");
        email.setName("Asche");
        email.setSubject("你好哈！");
        email.setMessageText("org.springframework.mail.MailSendException: Failed messages: com.sun.mail.smtp.SMTPSendFailedException:");
        sendEmail(email);
        return "Send success!";
    }

    public void sendEmail(Email bean){
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
