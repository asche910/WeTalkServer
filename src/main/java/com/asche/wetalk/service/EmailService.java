package com.asche.wetalk.service;

import com.asche.wetalk.bean.EmailBean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private JavaMailSender javaMailSender;

    public void sendMail(EmailBean emailBean){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(emailBean.getTo());
        mailMessage.setFrom(emailBean.getFrom());
        mailMessage.setSubject(emailBean.getSubject());
        mailMessage.setText(emailBean.getMessageText());
        javaMailSender.send(mailMessage);
    }


}
