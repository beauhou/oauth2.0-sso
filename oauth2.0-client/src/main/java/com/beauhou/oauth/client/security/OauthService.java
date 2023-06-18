package com.beauhou.oauth.client.security;

import com.oauth.core.constant.RpcUser;
import org.springframework.stereotype.Service;

/**
 * @author BeauHou
 */
@Service
public class OauthService {

    private final ThreadLocal<RpcUser> threadLocal = new ThreadLocal<>();

    public void setUser(RpcUser rpcUser) {
        threadLocal.set(rpcUser);
    }

    /**
     * 获取当前登录人
     *
     * @return
     */
    public RpcUser getUser() {
        return threadLocal.get();
    }

    public void removeUser() {
        threadLocal.remove();
    }
}
