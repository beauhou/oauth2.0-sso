package com.beauhou.oauth.client.controller;

import com.oauth.core.constant.HttpResultConstant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author BeauHou
 */
@RestController
public class CallbackController {

    /**
     * 客户端回调方法
     *
     * @param code 临时授权码
     */
    @GetMapping("callback")
    public HttpResultConstant callback(String code) {

        return HttpResultConstant.success("");
    }
}
