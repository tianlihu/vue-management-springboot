package com.tianlihu.management.controller;

import com.tianlihu.management.response.ResponseData;
import com.tianlihu.management.service.LoginService;
import com.tianlihu.management.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/login")
    public ResponseData login(String username, String password) {
        return loginService.login(username, password);
    }

    @RequestMapping("/logout")
    public ResponseData logout(HttpSession session) {
        session.invalidate();
        return ResponseData.success("退出成功");
    }
}
