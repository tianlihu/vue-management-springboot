package com.tianlihu.management.controller;

import com.alibaba.excel.EasyExcel;
import com.tianlihu.management.query.UserQuery;
import com.tianlihu.management.response.ResponseData;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.tianlihu.management.entity.User;
import com.tianlihu.management.query.UserQuery;
import com.tianlihu.management.service.UserService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author tianlihu
 * @since 2020-04-13
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/user/page")
    public IPage<User> page(@RequestBody UserQuery query){
        Page<User> page= new Page<User>(query.getPage(), query.getPageSize());
        return userService.page(page);
    }

    @RequestMapping("/user/list")
    public List<User> list(@RequestBody UserQuery query){
        return userService.list();
    }

    @RequestMapping("/user/findById")
    public ResponseData findById(@RequestParam Integer id) {
        User user = userService.getById(id);
        return ResponseData.success(user);
    }

    @RequestMapping("/user/save")
    public ResponseData save(@RequestBody User user) {
        userService.save(user);
        return ResponseData.success(user);
    }

    @RequestMapping("/user/update")
    public ResponseData update(@RequestBody User user) {
        userService.updateById(user);
        return ResponseData.success(user);
    }

    @RequestMapping("/user/delete")
    public ResponseData delete(@RequestParam List<Integer> id) {
        userService.removeByIds(id);
        return ResponseData.success(id);
    }

    @ResponseBody
    @RequestMapping("/user/export")
    public void export(UserQuery query, HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("用户.xlsx", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        EasyExcel.write(response.getOutputStream(), User.class).sheet("用户").doWrite(list(query));
    }
}
