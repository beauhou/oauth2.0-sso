package com.oauth.server.service;

import cn.hutool.core.util.StrUtil;
import com.oauth.server.controller.UserVo;
import com.oauth.server.manager.CodeManager;
import com.oauth.server.model.OauthDetails;
import com.oauth.server.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;

/**
 * @author BeauHou
 */
@Service
public class LoginService {

    @Autowired
    private CodeManager codeManager;

    @Autowired
    private OauthDetailsService oauthDetailsService;

    /**
     * 用户登录
     */
    public String login(OauthDetails oauthDetails, Model model) {
        if (!oauthDetailsService.appIdIsExist(oauthDetails.getAppCode())) {
            throw new RuntimeException("应用不存在");
        }
        model.addAttribute("appCode", oauthDetails.getAppCode());
        String redirectUrl = oauthDetails.getRedirectUrl();
        if (StrUtil.isBlank(redirectUrl)) {
            redirectUrl = "https://www.baidu.com";
        }
        model.addAttribute("redirectUrl", redirectUrl);
        return "index";
    }


    public String userLogin(User user) {
        String code = codeManager.generationCode(new UserVo(), new OauthDetails());
        String redirectUrl = user.getRedirectUrl();
        if (StringUtils.isEmpty(user.getRedirectUrl())) {
            redirectUrl = "https://www.baidu.com";
        }
        return redirectUrl + "?code=" + code;
    }
}
