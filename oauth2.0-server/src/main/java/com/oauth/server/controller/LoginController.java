package com.oauth.server.controller;

import com.oauth.core.constant.HttpResultConstant;
import com.oauth.server.model.OauthDetails;
import com.oauth.server.model.User;
import com.oauth.server.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 * @author BeauHou
 */
@Controller
@RequestMapping
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("login")
    public String login(OauthDetails oauthDetails, Model model) {
        return loginService.login(oauthDetails,model);
    }

    /**
     * 用户登录
     *
     * @return
     */
    @PostMapping("userLogin")
    @ResponseBody
    public HttpResultConstant userLogin(@RequestBody User user) {
        return HttpResultConstant.success(loginService.userLogin(user));
    }


}
