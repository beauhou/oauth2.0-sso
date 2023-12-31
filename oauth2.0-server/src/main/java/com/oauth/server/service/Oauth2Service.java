package com.oauth.server.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.oauth.core.constant.RpcUser;
import com.oauth.server.constant.AccessTokenConstant;
import com.oauth.server.constant.CodeConstant;
import com.oauth.server.constant.TicketConstant;
import com.oauth.server.manager.AccessTokenManager;
import com.oauth.server.manager.CodeManager;
import com.oauth.server.manager.TicketManager;
import com.oauth.server.model.OauthDetails;
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

    @Autowired
    private TicketManager ticketManager;

    /**
     * 用户登录
     */
    public String login(OauthDetails oauthDetails, Model model) {
        OauthDetails queryOauthDetails = oauthDetailsService.getByAppCode(oauthDetails.getAppCode());
        if (queryOauthDetails == null) {
            throw new RuntimeException("应用不存在");
        }
        TicketConstant localTicket = ticketManager.getLocalTicket();
        //本地票据存在登录态，则直接生成授权码
        if (localTicket != null) {
            String code = codeManager.generationCode(localTicket.getUser(), localTicket);
            String redirectUrl = oauthDetails.getRedirectUrl();
            if (StrUtil.isBlank(redirectUrl)) {
                redirectUrl = queryOauthDetails.getRedirectUrl();
            }
            return "redirect:" + redirectUrl + "?code=" + code;
        } else {
            model.addAttribute("appCode", oauthDetails.getAppCode());
            String redirectUrl = oauthDetails.getRedirectUrl();
            if (StrUtil.isBlank(redirectUrl)) {
                redirectUrl = queryOauthDetails.getRedirectUrl();
            }
            model.addAttribute("redirectUrl", redirectUrl);
            return "index";
        }
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
        CodeConstant codeConstant = codeManager.getCode(code);
        if (codeConstant == null) {
            throw new RuntimeException("授权码不存在");
        }
        codeManager.removeCode(code);
        return accessTokenManager.generationAccessToken(codeConstant.getUser(), codeConstant);
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
        AccessTokenConstant accessTokenConstant = accessTokenManager.get(accessToken);
        RpcUser rpcUser = BeanUtil.copyProperties(accessTokenConstant.getUser(), RpcUser.class);
        return rpcUser;
    }

    /**
     * 刷新token
     */
    public void refreshToken() {

    }

    /**
     * 退出登录
     *
     * @param accessToken
     */
    public void logout(String accessToken) {
        AccessTokenConstant accessTokenConstant = accessTokenManager.get(accessToken);
        accessTokenManager.delete(accessToken);
        CodeConstant codeConstant = accessTokenConstant.getCodeConstant();
        if (codeConstant != null) {
            String tct = codeConstant.getTicketConstant().getTct();
            ticketManager.deleteLocalTicketManager(tct);
        }
    }
}
