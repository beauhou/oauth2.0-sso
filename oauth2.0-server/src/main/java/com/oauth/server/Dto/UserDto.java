package com.oauth.server.Dto;

import lombok.Data;

/**
 * @author BeauHou
 */
@Data
public class UserDto {

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
