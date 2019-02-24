package com.product.judge.api.plugins.email;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author haiyan
 * @version v0.0.1
 * @project judgeApi
 * @describe 邮件发送属性类
 * @since 2018/5/10
 **/
@Getter
@Setter(AccessLevel.PUBLIC)
@NoArgsConstructor
@Component
@ConfigurationProperties
@PropertySource("classpath:properties/config-mail.properties")
public class EmailConfigurationProperties
{

    private String mailhost;

    private String mailusername;

    private String mailpassword;

    private int mailport;

    private String mailprotocol;

    private String mailauth;

    private String mailtimeout;

    private String mailfrom;

    private String mailto;

    private String[] mailcc;

    private String mailstarttlsenable;

    private String mailstarttlsrequired;

}
