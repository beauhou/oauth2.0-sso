package com.oauth.server.manager;

import com.oauth.core.utils.CookieUtils;
import com.oauth.server.constant.TicketConstant;
import com.oauth.server.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author BeauHou
 */
@Service
public class TicketManager {

    /**
     * 本地票据id
     */
    private final String ticketKey = "tgt";

    private Map<String, TicketConstant> map = new HashMap<>();

    /**
     * 生成本地票据
     *
     * @return
     */
    public TicketConstant generationLocalTicketManager(User user, String appCode) {
        String ticketCode = UUID.randomUUID().toString();
        TicketConstant ticketConstant = new TicketConstant();
        ticketConstant.setAppCode(appCode);
        ticketConstant.setUser(user);
        ticketConstant.setTct(ticketCode);
        map.put(ticketCode, ticketConstant);
        CookieUtils.addCookie(ticketKey, ticketCode, "/");
        return ticketConstant;
    }

    /**
     * 获取本地票据
     *
     * @return 票据用户信息
     */
    public TicketConstant getLocalTicket() {
        String ticketCode = CookieUtils.getCookie(ticketKey);
        return map.get(ticketCode);
    }

    /**
     * 删除本地票据
     */
    public void deleteLocalTicketManager(String tct) {
        map.remove(tct);
    }
}
