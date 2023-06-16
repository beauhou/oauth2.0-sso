package com.oauth.server.service;

import com.oauth.server.manager.CodeManager;
import com.oauth.server.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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


    @Autowired
    private UserService userService;


    public String userLogin(User user) {
        User queryUserInfo = userService.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (queryUserInfo == null) {
            throw new RuntimeException("账号或密码错误");
        }
        String code = codeManager.generationCode(queryUserInfo);
        String redirectUrl = user.getRedirectUrl();
        if (StringUtils.isEmpty(user.getRedirectUrl())) {
            redirectUrl = "https://www.baidu.com";
        }
        return redirectUrl + "?code=" + code;
    }
}
