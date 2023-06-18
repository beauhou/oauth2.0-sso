package com.beauhou.oauth.client.filter;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import com.beauhou.oauth.client.configuration.OauthPropertiesConfiguration;
import com.beauhou.oauth.client.security.OauthService;
import com.oauth.core.constant.HttpResultConstant;
import com.oauth.core.constant.RpcUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author BeauHou
 */
@Component
public class OauthClientFilter extends OncePerRequestFilter {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Autowired
    private OauthPropertiesConfiguration oauthPropertiesConfiguration;

    @Autowired
    private OauthService oauthService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (verifyIsAnonymousAccessUrl(request.getRequestURI())) {
            filterChain.doFilter(request, response);
        } else {
            RpcUser rpcUser = RpcUser(request);
            if (rpcUser == null) {
                String scheme = request.getScheme();
                String host = ServletUtil.getHeaderIgnoreCase(request, "host");
                String url = oauthPropertiesConfiguration.getOauthUrl() + "/oauth2/login?appCode=" + oauthPropertiesConfiguration.getAppCode()
                        + "&redirectUrl=" + scheme + "://" + host + "/callback";
                response.sendRedirect(url);
                return;
            } else {
                try {
                    oauthService.setUser(rpcUser);
                    filterChain.doFilter(request, response);
                } finally {
                    oauthService.removeUser();
                }
            }
        }
    }

    private RpcUser RpcUser(HttpServletRequest request) {
        String accessToken = request.getHeader("access_token");
        String url = "/oauth2/access_token_info?appCode="
                +oauthPropertiesConfiguration.getAppCode()+"&appSecret="+oauthPropertiesConfiguration.getAppSecret();
        String accessTokenResult = HttpUtil.createGet(oauthPropertiesConfiguration.getOauthUrl() + url)
                .header("access_token", accessToken)
                .execute().body();
        HttpResultConstant httpResultConstant = BeanUtil.copyProperties(new JSONObject(accessTokenResult), HttpResultConstant.class);
        if (httpResultConstant.getCode() != 200) {
            return null;
        }
        RpcUser rpcUser = BeanUtil.copyProperties(httpResultConstant.getData(), RpcUser.class);
        return rpcUser;
    }

    /**
     * 验证是否是白名单
     *
     * @param uri 请求地址
     * @return true- 属于白名单  false-不属于白名单
     */
    private boolean verifyIsAnonymousAccessUrl(String uri) {
        List<String> anonymousAccessList = oauthPropertiesConfiguration.getAnonymousAccess();
        for (String anonymousAccess : anonymousAccessList) {
            if (antPathMatcher.match(anonymousAccess, uri)) {
                return true;
            }
        }
        return false;
    }
}
