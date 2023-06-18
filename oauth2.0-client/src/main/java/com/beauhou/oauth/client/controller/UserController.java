package com.beauhou.oauth.client.controller;

import com.beauhou.oauth.client.security.OauthService;
import com.oauth.core.constant.HttpResultConstant;
import com.oauth.core.constant.RpcUser;
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
        return HttpResultConstant.success();
    }
}
