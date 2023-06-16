package com.oauth.server.constant;

import com.oauth.server.model.User;
import lombok.Data;

/**
 * @author BeauHou
 */
@Data
public class CodeConstant {

    /**
     * 临时授权码
     */
    private String code;

    /**
     * 用户信息
     */
    private User user;

    /**
     * 应用id
     */
    private String appId;

}
