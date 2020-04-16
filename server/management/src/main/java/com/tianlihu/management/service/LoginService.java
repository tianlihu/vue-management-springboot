package com.tianlihu.management.service;

import com.tianlihu.management.entity.User;
import com.tianlihu.management.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UserMapper userMapper;

    public String login(String username, String password) {
        if (StringUtils.isEmpty(username)) {
            return "用户名不能为空";
        }
        if (StringUtils.isEmpty(password)) {
            return "密码不能为空";
        }

        User user = userMapper.findByAccount(username);
        if (user == null) {
            return "用户不存在";
        }

        if (!StringUtils.equals(password, user.getPassword())) {
            return "密码不正确";
        }

        return null;
    }
}
