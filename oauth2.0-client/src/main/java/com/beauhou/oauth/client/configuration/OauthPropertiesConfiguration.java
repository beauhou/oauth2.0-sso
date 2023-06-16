package com.beauhou.oauth.client.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author BeauHou
 */
@Data
@Configuration
@ConfigurationProperties("oauth2")
public class OauthPropertiesConfiguration {

    /**
     * 认证中心地址
     */
    private String oauthUrl;

    /**
     * 应用编码
     */
    private String appCode;

    /**
     * 应用秘钥
     */
    private String appSecret;
}
