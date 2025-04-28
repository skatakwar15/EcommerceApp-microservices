package com.sarthak.notification.kafka.email;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfig {


    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        String host = "localhost";
        mailSender.setHost(host); // Use your SMTP host
        int port = 1025;
        mailSender.setPort(port); // Port for TLS

        String username = "sarthak";
        mailSender.setUsername(username); // Your email ID
        String password = "sarthak";
        mailSender.setPassword(password); // Your email password

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}
