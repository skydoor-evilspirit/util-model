package com.example.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class SendEmail {
    @Autowired
    private JavaMailSender sender;

    public void send(){
        System.out.println("执行send方法。。。");
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("小伙子，快去注册啊！！！开放注册啦！！！");
        mailMessage.setText("小伙子，快去注册啊！！！开放注册啦！！！");
        mailMessage.setTo("evil_spirit@foxmail.com");
        mailMessage.setFrom("evil_spirit@foxmail.com");
        sender.send(mailMessage);
    }


}
