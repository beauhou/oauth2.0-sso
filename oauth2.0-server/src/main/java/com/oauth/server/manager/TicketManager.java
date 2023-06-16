package com.oauth.server.manager;

import com.oauth.server.utils.CookieUtils;

import java.util.UUID;

/**
 * @author BeauHou
 */
public class TicketManager {

    /**
     * 本地票据id
     */
    private final String ticketKey = "tgt";

    /**
     * 生成本地票据
     */
    private void generationLocalTicketManager() {
        String ticketCode = UUID.randomUUID().toString();
        CookieUtils.addCookie(ticketKey, ticketCode, "/");

    }

    /**
     * 获取本地票据
     *
     * @return
     */
    private String getLocalTicket() {
        String ticketCode = CookieUtils.getCookie(ticketKey);
        return ticketCode;
    }

    /**
     * 删除本地票据
     */
    public void deleteLocalTicketManager() {

    }
}
