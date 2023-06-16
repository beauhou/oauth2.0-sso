package com.oauth.server.constant;

import com.oauth.server.controller.UserVo;
import com.oauth.server.model.OauthDetails;
import lombok.Data;

/**
 * @author BeauHou
 */
@Data
public class TicketConstant {

    /**
     * 用户信息
     */
    private UserVo userVo;

    /**
     * oauth信息
     */
    private OauthDetails oauthDetails;
}
