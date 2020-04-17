package com.tianlihu.management.service;

import com.tianlihu.management.commons.DateTool;
import com.tianlihu.management.entity.Permission;
import com.tianlihu.management.entity.User;
import com.tianlihu.management.mapper.PermissionMapper;
import com.tianlihu.management.mapper.UserMapper;
import com.tianlihu.management.response.ResponseData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoginService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PermissionMapper permissionMapper;

    @Transactional
    public ResponseData login(String username, String password) {
        if (StringUtils.isEmpty(username)) {
            return ResponseData.error(401, "用户名不能为空");
        }
        if (StringUtils.isEmpty(password)) {
            return ResponseData.error(401, "密码不能为空");
        }

        User user = userMapper.findByAccount(username);
        if (user == null) {
            return ResponseData.error(401, "用户不存在");
        }

        if (!StringUtils.equals(password, user.getPassword())) {
            return ResponseData.error(401, "密码不正确");
        }

        user.setLastLoginTime(DateTool.currentTime());
        user.setLoginTimes(user.getLoginTimes() + 1);
        userMapper.updateById(user);

        List<Permission> permissions = permissionMapper.findByUserId(user.getUserId());
        Map<String, Object> data = new HashMap<>();
        data.put("user", user);
        data.put("permissions", permissions);
        return ResponseData.success(data);
    }
}
