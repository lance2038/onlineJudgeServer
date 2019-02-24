package com.product.judge.api.plugins.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * @author haiyan
 * @version v0.0.1
 * @project judgeApi
 * @describe 邮件发送配置类
 * @since 2018/5/10
 **/
@SuppressWarnings("SpringJavaAutowiringInspection")
@Configuration
public class EmailSendConfiguration
{

    @Autowired
    EmailConfigurationProperties emailConfigurationProperties;

    @Bean
    public MailSender mailSender()
    {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setDefaultEncoding("UTF-8");
        mailSender.setHost(emailConfigurationProperties.getMailhost());
        mailSender.setUsername(emailConfigurationProperties.getMailusername());
        mailSender.setPassword(emailConfigurationProperties.getMailpassword());
        mailSender.setPort(emailConfigurationProperties.getMailport());
        mailSender.setProtocol(emailConfigurationProperties.getMailprotocol());
        Properties javaMailProperties = new Properties();
        javaMailProperties.setProperty("mail.smtp.auth", emailConfigurationProperties.getMailauth());
        javaMailProperties.setProperty("mail.smtp.timeout", emailConfigurationProperties.getMailtimeout());
        javaMailProperties.setProperty("mail.smtp.starttls.enable", emailConfigurationProperties.getMailstarttlsenable());
        javaMailProperties.setProperty("mail.smtp.starttls.required", emailConfigurationProperties.getMailstarttlsrequired());
        javaMailProperties.setProperty("mail.smtp.socketFactory.port", String.valueOf(emailConfigurationProperties.getMailport()));
        mailSender.setJavaMailProperties(javaMailProperties);
        return mailSender;
    }

    @Bean
    public SimpleMailMessage simpleMailMessage()
    {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(emailConfigurationProperties.getMailfrom());
        simpleMailMessage.setTo(emailConfigurationProperties.getMailto());
        simpleMailMessage.setCc(emailConfigurationProperties.getMailcc());
        return simpleMailMessage;
    }

}
