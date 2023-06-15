package com.oauth.server.controller;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author BeauHou
 */
@Data
@Accessors(chain = true)
public class UserVo {

    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
