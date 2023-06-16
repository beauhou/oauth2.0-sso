package com.oauth.server.constant;

import com.oauth.server.model.User;
import lombok.Data;

/**
 * @author BeauHou
 */
@Data
public class TicketConstant {

    /**
     * 票据
     */
    private String tct;

    /**
     * 应用编码
     */
    private String appCode;

    /**
     * 用户信息
     */
    private User user;

}
