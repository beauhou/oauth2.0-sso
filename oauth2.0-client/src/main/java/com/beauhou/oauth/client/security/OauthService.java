package com.beauhou.oauth.client.security;

import com.beauhou.oauth.client.configuration.OauthPropertiesConfiguration;
import com.beauhou.oauth.client.constant.HttpUrlConstant;
import com.oauth.core.constant.RpcUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author BeauHou
 */
@Service
public class OauthService {

    @Autowired
    private OauthPropertiesConfiguration oauthPropertiesConfiguration;
    private final ThreadLocal<RpcUser> threadLocal = new ThreadLocal<>();

    /**
     * 设置当前线程变量用户信息
     *
     * @param rpcUser 远程调用用户信息
     */
    public void setUser(RpcUser rpcUser) {
        threadLocal.set(rpcUser);
    }

    /**
     * 获取当前ThreadLocal中存储的用户信息
     */
    public RpcUser getUser() {
        return threadLocal.get();
    }

    /**
     * 移除threadLocal中的用户信息
     */
    public void removeUser() {
        threadLocal.remove();
    }

    /**
     * 构建oauth2.0登录地址
     */
    public String buildOauth2LoginUrl(String callbackUrl) {
        String url = oauthPropertiesConfiguration.getOauthUrl() + HttpUrlConstant.oauth2_login_url
                + "?appCode=" + oauthPropertiesConfiguration.getAppCode()
                + "&redirectUrl=" + callbackUrl;
        return url;
    }
}
