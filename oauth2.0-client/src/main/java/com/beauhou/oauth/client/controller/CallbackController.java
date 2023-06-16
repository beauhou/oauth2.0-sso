package com.beauhou.oauth.client.controller;

import cn.hutool.http.HttpUtil;
import com.beauhou.oauth.client.configuration.OauthPropertiesConfiguration;
import com.oauth.core.constant.HttpResultConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author BeauHou
 */
@RestController
public class CallbackController {

    @Autowired
    private OauthPropertiesConfiguration oauthProperties;

    /**
     * 客户端回调方法
     *
     * @param code 临时授权码
     */
    @GetMapping("callback")
    public HttpResultConstant callback(String code) {
        String url = "/oauth2/access_token?appCode=demo&appSecret=demo&code=" + code;
        String body = HttpUtil.createGet(oauthProperties.getOauthUrl() + url)
                .execute().body();
        System.out.println(body);
        return HttpResultConstant.success("");
    }
}
