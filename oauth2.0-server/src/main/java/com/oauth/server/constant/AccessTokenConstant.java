package com.oauth.server.constant;

import com.oauth.server.model.User;
import lombok.Data;

/**
 * @author BeauHou
 */
@Data
public class AccessTokenConstant {

    /**
     * 令牌
     */
    private String accessToken;

    /**
     * 用户信息
     */
    private User user;


    /**
     * 本地票据
     */
    private CodeConstant codeConstant;

}
