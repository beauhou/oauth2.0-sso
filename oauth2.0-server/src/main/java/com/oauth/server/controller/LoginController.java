package com.oauth.server.controller;

import com.oauth.core.constant.HttpResultConstant;
import com.oauth.server.model.OauthDetails;
import com.oauth.server.model.User;
import com.oauth.server.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author BeauHou
 */
@Controller
@RequestMapping
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 用户登录
     *
     * @return
     */
    @PostMapping("login")
    @ResponseBody
    public HttpResultConstant login(@RequestBody User user, OauthDetails oauthDetails) {
        return HttpResultConstant.success(loginService.userLogin(user,oauthDetails));
    }


}
