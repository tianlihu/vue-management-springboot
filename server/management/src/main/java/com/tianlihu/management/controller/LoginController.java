package com.tianlihu.management.controller;

import com.tianlihu.management.response.ResponseData;
import com.tianlihu.management.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping("/login")
    public ResponseData login(String username, String password) {
        String result = loginService.login(username, password);
        if (result == null) {
            return ResponseData.success("登录成功");
        } else {
            return ResponseData.error(401, result);
        }
    }

    @RequestMapping("/logout")
    public ResponseData logout(HttpSession session) {
        session.invalidate();
        return ResponseData.success("退出成功");
    }
}
