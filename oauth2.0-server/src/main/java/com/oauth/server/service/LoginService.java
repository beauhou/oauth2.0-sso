package com.oauth.server.service;

import com.oauth.server.manager.CodeManager;
import com.oauth.server.manager.TicketManager;
import com.oauth.server.model.OauthDetails;
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

    @Autowired
    private TicketManager ticketManager;


    public String userLogin(User user, OauthDetails oauthDetails) {
        oauthDetails = oauthDetailsService.getByAppCode(oauthDetails.getAppCode());
        if (oauthDetails == null) {
            throw new RuntimeException("应用信息不存在");
        }
        User queryUserInfo = userService.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (queryUserInfo == null) {
            throw new RuntimeException("账号或密码错误");
        }
        String code = codeManager.generationCode(queryUserInfo);
        ticketManager.generationLocalTicketManager(queryUserInfo, oauthDetails.getAppCode());
        String redirectUrl = user.getRedirectUrl();
        if (StringUtils.isEmpty(user.getRedirectUrl())) {
            redirectUrl = oauthDetails.getRedirectUrl();
        }
        return redirectUrl + "?code=" + code;
    }
}
