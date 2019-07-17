package com.asche.wetalk.controller;

import com.asche.wetalk.common.CommonResult;
import com.asche.wetalk.common.IErrorCode;
import com.asche.wetalk.common.ResultCode;
import com.asche.wetalk.entity.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import static com.asche.wetalk.util.PrintUtils.println;

@Controller
public class EmailController {

    @Autowired
    JavaMailSender mailSender;

    @GetMapping("/email")
    @ResponseBody
    public CommonResult mailTest() {
        Email email = new Email();
        email.setFrom("apknet@163.com");
        email.setTo("asche910@163.com");
        email.setName("Asche");
        email.setSubject("你好哈！");
        email.setMessageText("org.springframework.mail.MailSendException: Failed messages: com.sun.mail.smtp.SMTPSendFailedException: \n --- " + new Date().toString());

        try {
            sendEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed(ResultCode.FAILED);
        }
        return CommonResult.success(null);
    }

    public void sendEmail(Email bean) throws UnsupportedEncodingException, MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(bean.getFrom(), bean.getName());
        helper.setTo(bean.getTo());
        helper.setSubject(bean.getSubject());
        helper.setText(bean.getMessageText());
        mailSender.send(message);
        println("Email sent success!");
    }
}
