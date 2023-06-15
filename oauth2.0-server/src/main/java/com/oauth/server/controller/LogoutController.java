package com.oauth.server.controller;

import com.oauth.core.constant.HttpResultConstant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author BeauHou
 */
@RestController
@RequestMapping
public class LogoutController {

    @GetMapping("logout")
    public HttpResultConstant logout() {
        return null;
    }
}
