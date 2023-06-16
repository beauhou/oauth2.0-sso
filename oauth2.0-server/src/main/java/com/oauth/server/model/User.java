package com.oauth.server.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author BeauHou
 */
@Data
@Accessors(chain = true)
public class User {

    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;


    /**
     * 重定向地址
     */
    private String redirectUrl;
}
