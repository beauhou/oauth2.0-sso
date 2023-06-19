package com.oauth.server.manager;

import com.oauth.server.constant.CodeConstant;
import com.oauth.server.constant.TicketConstant;
import com.oauth.server.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * <p>授权码管理器</p>
 *
 * @author BeauHou
 */
@Service
public class CodeManager {


    private Map<String, CodeConstant> codeCacheMap = new HashMap<>();

    /**
     * 根据token生成授权码
     *
     * @return
     */
    public String generationCode(User user, TicketConstant ticketConstant) {
        String code = UUID.randomUUID().toString();
        CodeConstant codeConstant = new CodeConstant();
        codeConstant.setCode(code);
        codeConstant.setUser(user);
        codeConstant.setAppCode(ticketConstant.getAppCode());
        codeConstant.setTicketConstant(ticketConstant);
        codeCacheMap.put(code, codeConstant);
        return code;
    }

    /**
     * 获取临时授权码信息
     *
     * @param code 临时授权码
     * @return
     */
    public CodeConstant getCode(String code) {
        return codeCacheMap.get(code);
    }

    /**
     * 移除临时授权码
     *
     * @param code 临时授权码
     */
    public void removeCode(String code) {
        codeCacheMap.remove(code);
    }
}
