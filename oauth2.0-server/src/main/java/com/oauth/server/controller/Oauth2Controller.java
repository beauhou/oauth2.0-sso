package com.oauth.server.controller;

import com.oauth.core.constant.HttpResultConstant;
import com.oauth.server.Dto.OauthDetailsDTO;
import com.oauth.server.service.Oauth2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author BeauHou
 */
@RestController
@RequestMapping("oauth2")
public class Oauth2Controller {


    @Autowired
    private Oauth2Service oauth2Service;

    /**
     * 通过accessToken进行
     *
     * @return
     */
    @GetMapping("access_token")
    public HttpResultConstant getAccessToken(OauthDetailsDTO oauthDetailsDTO) {
        oauth2Service.getAccessToken(oauthDetailsDTO);
        return HttpResultConstant.success("");
    }


    @GetMapping(value = "/refresh_token")
    public HttpResultConstant refreshToken() {
        oauth2Service.refreshToken();
        return HttpResultConstant.success("");
    }

}
