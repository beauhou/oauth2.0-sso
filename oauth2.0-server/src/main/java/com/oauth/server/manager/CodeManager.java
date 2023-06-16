package com.oauth.server.manager;

import com.oauth.server.controller.UserVo;
import com.oauth.server.model.OauthDetails;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * <p>授权码管理器</p>
 *
 * @author BeauHou
 */
@Service
public class CodeManager {

    /**
     * 根据token生成授权码
     *
     * @return
     */
    public String generationCode(UserVo userVo, OauthDetails oauthDetails) {
        String code = UUID.randomUUID().toString();
        return code;
    }

}
