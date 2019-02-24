package com.product.judge.api.plugins.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

/**
 * @author haiyan
 * @version v0.0.1
 * @project judgeApi
 * @describe 邮件发送类
 * @since 2018/5/10
 **/
@Component
public class EmailSendFactory
{
    @Autowired
    private MailSender mailSender;
    @Autowired
    private SimpleMailMessage simpleMailMessage;

    public void sendEmail(String title, String message)
    {
        simpleMailMessage.setSubject(title);
        simpleMailMessage.setText(message);
        mailSender.send(simpleMailMessage);
    }
}
