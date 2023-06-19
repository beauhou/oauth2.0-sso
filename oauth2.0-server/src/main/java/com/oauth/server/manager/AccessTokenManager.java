package com.oauth.server.manager;

import cn.hutool.core.lang.UUID;
import com.oauth.server.constant.AccessTokenConstant;
import com.oauth.server.constant.CodeConstant;
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

    private Map<String, AccessTokenConstant> accessTokenMap = new HashMap<>();

    /**
     * 授权码与token关联
     */
    private Map<String, AccessTokenConstant> codeTokenRelationMap = new HashMap<>();


    /**
     * 生成token信息
     *
     * @param codeConstant 授权码信息
     * @return
     */
    public String generationAccessToken(User user, CodeConstant codeConstant) {
        if (codeConstant != null) {
            AccessTokenConstant accessTokenConstant = codeTokenRelationMap.get(codeConstant.getTicketConstant().getTct());
            if (accessTokenConstant != null) {
                return accessTokenConstant.getAccessToken();
            }
        }
        String accessToken = UUID.fastUUID().toString();
        AccessTokenConstant accessTokenConstant = new AccessTokenConstant();
        accessTokenConstant.setAccessToken(accessToken);
        accessTokenConstant.setUser(user);
        accessTokenConstant.setCodeConstant(codeConstant);
        accessTokenMap.put(accessToken, accessTokenConstant);
        if (codeConstant != null) {
            codeTokenRelationMap.put(codeConstant.getTicketConstant().getTct(), accessTokenConstant);
        }
        return accessToken;
    }

    /**
     * 生成令牌
     *
     * @param accessToken
     * @return 用户信息
     */
    public AccessTokenConstant get(String accessToken) {
        return accessTokenMap.get(accessToken);
    }

    /**
     * 退出登录
     *
     * @param accessToken
     */
    public void delete(String accessToken) {
        AccessTokenConstant accessTokenConstant = accessTokenMap.get(accessToken);
        accessTokenMap.remove(accessToken);
        CodeConstant codeConstant = accessTokenConstant.getCodeConstant();
        if (codeConstant != null) {
            codeTokenRelationMap.remove(codeConstant.getCode());
        }
    }
}
