package com.oauth.server.utils;

import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;

/**
 * <h3>cookie操作工具</h3>
 */
public class CookieUtils {

    private CookieUtils() {
    }

    /**
     * 按名称获取cookie
     */
    public static String getCookie(String name) {
        Cookie[] cookies = ServletUtils.getRequest().getCookies();
        if (cookies == null || StringUtils.isEmpty(name)) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (name.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }

    /**
     * 添加cookie
     *
     * @param name
     * @param value
     * @param path
     */
    public static void addCookie(String name, String value, String path) {
        Cookie cookie = new Cookie(name, value);
        if (path != null) {
            cookie.setPath(path);
        }
        if ("https".equals(ServletUtils.getRequest().getScheme())) {
            cookie.setSecure(true);
        }
        cookie.setHttpOnly(true);
        cookie.setMaxAge(7 * 24 * 60 * 60);
        ServletUtils.getResponse().addCookie(cookie);
    }


    /**
     * 删除cookie
     *
     * @param name 名称
     * @param path 路径
     */
    public static void delete(String name, String path) {
        Cookie cookie = new Cookie(name, null);
        if (path != null) {
            cookie.setPath(path);
        }
        if ("https".equals(ServletUtils.getRequest().getScheme())) {
            cookie.setSecure(true);
        }
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        ServletUtils.getResponse().addCookie(cookie);
    }

}
