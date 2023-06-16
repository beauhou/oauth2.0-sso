package com.oauth.server.service;

import cn.hutool.core.collection.CollectionUtil;
import com.oauth.server.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author BeauHou
 */
@Service
public class UserService {
    private List<User> userList = new ArrayList<>();

    {
        User demo = new User().setUsername("demo").setPassword("123456");
        User oauth = new User().setUsername("oauth").setPassword("123456");
        userList.add(demo);
        userList.add(oauth);
    }

    /**
     * 通过账号密码查询用户
     *
     * @param username 用户名
     * @param password 密码
     * @return 用户信息
     */
    public User findByUsernameAndPassword(String username, String password) {
        List<User> users = userList.stream().filter(val -> val.getUsername().equals(username) && val.getPassword().equals(password))
                .collect(Collectors.toList());
        if (CollectionUtil.isNotEmpty(users)) {
            return users.get(0);
        }
        return null;
    }


}
