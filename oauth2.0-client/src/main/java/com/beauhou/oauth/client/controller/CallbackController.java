package com.beauhou.oauth.client.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import com.beauhou.oauth.client.configuration.OauthPropertiesConfiguration;
import com.beauhou.oauth.client.constant.HttpUrlConstant;
import com.oauth.core.constant.HttpResultConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author BeauHou
 */
@Controller
@RequestMapping("callback")
public class CallbackController {

    @Autowired
    private OauthPropertiesConfiguration oauthProperties;

    /**
     * callback页面，专门处理oauth相关业务逻辑
     */
    @GetMapping
    public String callback() {
        return "callback";
    }

    /**
     * 客户端回调方法
     *
     * @param code 临时授权码
     */
    @PostMapping
    @ResponseBody
    public HttpResultConstant callback(String code) {
        String url = HttpUrlConstant.oauth2_get_access_token_url + "?appCode=demo&appSecret=demo&code=" + code;
        String body = HttpUtil.createGet(oauthProperties.getOauthUrl() + url)
                .execute().body();
        HttpResultConstant httpResultConstant = BeanUtil.copyProperties(new JSONObject(body), HttpResultConstant.class);
        return httpResultConstant;
    }
}
