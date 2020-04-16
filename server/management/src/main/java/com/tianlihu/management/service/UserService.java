package com.tianlihu.management.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tianlihu.management.commons.DateTool;
import com.tianlihu.management.entity.User;
import com.tianlihu.management.mapper.RoleMapper;
import com.tianlihu.management.mapper.UserMapper;
import com.tianlihu.management.query.UserQuery;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author tianlihu
 * @since 2020-04-13
 */
@Service
@Transactional
public class UserService extends ServiceImpl<UserMapper, User> {

    @Autowired
    private RoleMapper roleMapper;

    public IPage<User> page(UserQuery query) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (query.getDepartmentId() != null) {
            wrapper.eq("department_id", query.getDepartmentId());
        }
        if (query.getAccount() != null) {
            wrapper.eq("account", query.getAccount());
        }
        if (StringUtils.isNoneBlank(query.getName())) {
            wrapper.like("name", query.getName());
        }
        if (query.getPhone() != null) {
            wrapper.like("phone", query.getPhone());
        }
        if (query.getStatus() != null) {
            wrapper.eq("status", query.getStatus());
        }
        if (query.getAdmin() != null) {
            wrapper.eq("admin", query.getAdmin());
        }
        return super.page(query, wrapper);
    }

    @Override
    public boolean save(User user) {
        user.setCreateTime(DateTool.currentTime());
        user.setUpdateTime(user.getCreateTime());
        user.setLastLoginTime(null);
        user.setLoginTimes(0);
        user.setDel(false);
        return super.save(user);
    }

    @Override
    public boolean updateById(User user) {
        User dbUser = getById(user.getUserId());
        dbUser.setDepartmentId(user.getDepartmentId());
        dbUser.setAccount(user.getAccount());
        dbUser.setName(user.getName());
        if (StringUtils.isNotEmpty(user.getPassword())) {
            dbUser.setPassword(user.getPassword());
        }
        dbUser.setPassword(user.getPassword());
        dbUser.setPhone(user.getPhone());
        dbUser.setAdmin(user.getAdmin());
        dbUser.setUpdateTime(DateTool.currentTime());
        dbUser.setStatus(user.getStatus());
        return super.updateById(user);
    }

    public void setRoles(Integer userId, List<Integer> roleIds) {
        roleMapper.deleteByUserId(userId);
        for (Integer roleId : roleIds) {
            roleMapper.saveUserRole(userId, roleId);
        }
    }
}
