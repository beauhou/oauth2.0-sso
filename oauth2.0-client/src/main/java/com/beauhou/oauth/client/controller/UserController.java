package com.beauhou.oauth.client.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import com.beauhou.oauth.client.configuration.OauthPropertiesConfiguration;
import com.beauhou.oauth.client.constant.HttpUrlConstant;
import com.beauhou.oauth.client.security.OauthService;
import com.oauth.core.constant.HttpResultConstant;
import com.oauth.core.constant.RpcUser;
import com.oauth.core.utils.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author BeauHou
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private OauthService oauthService;

    @Autowired
    private OauthPropertiesConfiguration oauthProperties;

    /**
     * 获取我的信息
     */
    @GetMapping("getMyInfo")
    public HttpResultConstant getMyInfo() {
        RpcUser user = oauthService.getUser();
        return HttpResultConstant.success(user);
    }

    /**
     * 退出登录
     */
    @GetMapping("logout")
    public HttpResultConstant logout() {
        String url = HttpUrlConstant.oauth2_logout_url + "?appCode=" + oauthProperties.getAppCode()
                + "&appSecret=" + oauthProperties.getAppSecret();
        String access_token = ServletUtils.getRequest().getHeader("access_token");
        String body = HttpUtil.createGet(oauthProperties.getOauthUrl() + url)
                .header("access_token", access_token)
                .execute()
                .body();
        HttpResultConstant httpResultConstant = BeanUtil.copyProperties(new JSONObject(body), HttpResultConstant.class);
        return httpResultConstant;
    }
}
