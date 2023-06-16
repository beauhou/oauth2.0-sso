package com.oauth.server.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 应用明细
 *
 * @author BeauHou
 */
@Data
@Accessors(chain = true)
public class OauthDetails {

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 应用编码
     */
    private String appCode;

    /**
     * 应用秘钥
     */
    private String appSecret;

    /**
     * 重定向地址
     */
    private String redirectUrl;

}
