package com.oauth.server.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.oauth.core.constant.RpcUser;
import com.oauth.server.manager.AccessTokenManager;
import com.oauth.server.manager.CodeManager;
import com.oauth.server.model.OauthDetails;
import com.oauth.server.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

/**
 * @author BeauHou
 */
@Service
public class Oauth2Service {

    @Autowired
    private OauthDetailsService oauthDetailsService;

    @Autowired
    private CodeManager codeManager;

    @Autowired
    private AccessTokenManager accessTokenManager;

    /**
     * 用户登录
     */
    public String login(OauthDetails oauthDetails, Model model) {
        if (!oauthDetailsService.appIdIsExist(oauthDetails.getAppCode())) {
            throw new RuntimeException("应用不存在");
        }
        model.addAttribute("appCode", oauthDetails.getAppCode());
        String redirectUrl = oauthDetails.getRedirectUrl();
        if (StrUtil.isBlank(redirectUrl)) {
            redirectUrl = "https://www.baidu.com";
        }
        model.addAttribute("redirectUrl", redirectUrl);
        return "index";
    }

    /**
     * 获取accessToken
     *
     * @param oauthDetails
     * @return
     */
    public String getAccessToken(OauthDetails oauthDetails, String code) {
        if (!oauthDetailsService.verifyAppCodeAndAppSecret(oauthDetails.getAppCode(), oauthDetails.getAppSecret())) {
            throw new RuntimeException("应用编码或秘钥错误");
        }
        User user = codeManager.getCode(code);
        if (user == null) {
            throw new RuntimeException("授权码不存在");
        }
        codeManager.removeCode(code);
        return accessTokenManager.generationAccessToken(user);
    }


    /**
     * 获取accessToken
     *
     * @param oauthDetails
     * @return
     */
    public RpcUser getAccessTokenInfo(OauthDetails oauthDetails, String accessToken) {
        if (!oauthDetailsService.verifyAppCodeAndAppSecret(oauthDetails.getAppCode(), oauthDetails.getAppSecret())) {
            throw new RuntimeException("应用编码或秘钥错误");
        }
        User user = accessTokenManager.get(accessToken);
        RpcUser rpcUser = BeanUtil.copyProperties(user, RpcUser.class);
        return rpcUser;
    }

    /**
     * 刷新token
     */
    public void refreshToken() {

    }
}
