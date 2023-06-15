package com.oauth.server.Dto;

import lombok.Data;

/**
 * 接收
 * @author BeauHou
 */
@Data
public class OauthDetailsDTO {

    /**
     * 应用编码
     */
    private String appCode;

    /**
     * 应用秘钥
     */
    private String appSecret;

    /**
     * 类型
     */
    private String type;

    /**
     *
     */
    private String code;
}
