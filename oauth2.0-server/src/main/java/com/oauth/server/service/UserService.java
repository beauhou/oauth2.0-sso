package com.oauth.server.service;

import com.oauth.server.controller.UserVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BeauHou
 */
@Service
public class UserService {
    private List<UserVo> userList = new ArrayList<>();

    {
        UserVo demo = new UserVo().setUsername("demo").setPassword("123456");
        UserVo oauth = new UserVo().setUsername("oauth").setPassword("123456");
        userList.add(demo);
        userList.add(oauth);
    }
}
