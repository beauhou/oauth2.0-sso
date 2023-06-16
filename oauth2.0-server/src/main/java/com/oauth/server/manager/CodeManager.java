package com.oauth.server.manager;

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


    private Map<String, User> codeCacheMap = new HashMap<>();

    /**
     * 根据token生成授权码
     *
     * @return
     */
    public String generationCode(User user) {
        String code = UUID.randomUUID().toString();
        codeCacheMap.put(code, user);
        return code;
    }

    /**
     * 获取临时授权码信息
     *
     * @param code 临时授权码
     * @return
     */
    public User getCode(String code) {
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
