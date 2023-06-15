package com.oauth.server.controller;

import com.oauth.core.constant.HttpResultConstant;
import com.oauth.server.Dto.UserDto;
import com.oauth.server.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author BeauHou
 */
@Controller
@RequestMapping
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("login")
    public String login() {
        return "index";
    }

    /**
     * 用户登录
     *
     * @return
     */
    public String userLogin(@RequestBody UserDto userDto) {
        return loginService.userLogin(userDto);
    }


}
