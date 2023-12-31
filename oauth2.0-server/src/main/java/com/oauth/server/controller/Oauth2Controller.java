package com.oauth.server.controller;

import com.oauth.core.constant.HttpResultConstant;
import com.oauth.core.utils.ServletUtils;
import com.oauth.server.model.OauthDetails;
import com.oauth.server.service.Oauth2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author BeauHou
 */
@Controller
@RequestMapping("oauth2")
public class Oauth2Controller {


    @Autowired
    private Oauth2Service oauth2Service;

    @GetMapping("login")
    public String login(OauthDetails oauthDetails, Model model) {
        return oauth2Service.login(oauthDetails, model);
    }


    /**
     * 通过accessToken进行
     *
     * @return
     */
    @ResponseBody
    @GetMapping("access_token")
    public HttpResultConstant getAccessToken(OauthDetails details, String code) {
        return HttpResultConstant.success(oauth2Service.getAccessToken(details, code));
    }

    /**
     * 获取登录用户信息
     */
    @ResponseBody
    @GetMapping("access_token_info")
    public HttpResultConstant getAccessTokenInfo(OauthDetails details) {
        String accessToken = ServletUtils.getRequest().getHeader("access_token");
        return HttpResultConstant.success(oauth2Service.getAccessTokenInfo(details, accessToken));
    }

    @ResponseBody
    @GetMapping(value = "/refresh_token")
    public HttpResultConstant refreshToken() {
        oauth2Service.refreshToken();
        return HttpResultConstant.success("");
    }

    /**
     * 退出登录
     *
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/logout")
    public HttpResultConstant logout() {
        String accessToken = ServletUtils.getRequest().getHeader("access_token");
        oauth2Service.logout(accessToken);
        return HttpResultConstant.success("");
    }
}
