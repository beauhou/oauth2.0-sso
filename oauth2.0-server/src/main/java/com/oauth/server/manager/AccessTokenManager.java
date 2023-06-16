package com.oauth.server.manager;

import cn.hutool.core.lang.UUID;
import com.oauth.server.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>令牌管理器</p>
 *
 * @author BeauHou
 */
@Service
public class AccessTokenManager {

    private Map<String, User> accessTokenMap = new HashMap<>();

    /**
     * 生成token信息
     *
     * @param user 用户信息
     * @return
     */
    public String generationAccessToken(User user) {
        String accessToken = UUID.fastUUID().toString();
        accessTokenMap.put(accessToken, user);
        return accessToken;
    }

    /**
     * 生成令牌
     *
     * @param accessToken
     * @return 用户信息
     */
    public User get(String accessToken) {
        return accessTokenMap.get(accessToken);
    }
}
