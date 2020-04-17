package com.tianlihu.management.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tianlihu.management.entity.Role;
import com.tianlihu.management.mapper.PermissionMapper;
import com.tianlihu.management.mapper.RoleMapper;
import com.tianlihu.management.query.RoleQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>角色 服务实现类</p>
 *
 * @author tianlihu
 * @since 2020-04-14
 */
@Service
@Transactional
public class RoleService extends ServiceImpl<RoleMapper, Role> {

    @Autowired
    private PermissionMapper permissionMapper;

    public IPage<Role> page(RoleQuery query) {
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(query.getName())) {
            wrapper.like("name", query.getName());
        }
        return super.page(query, wrapper);
    }

    public List<Integer> findLeafPermissionIdsByRoleId(Integer roleId) {
        return permissionMapper.findLeafPermissionIdsByRoleId(roleId);
    }

    @Override
    public boolean updateById(Role role) {
        Role dbRole = getById(role.getRoleId());
        dbRole.setName(role.getName());
        dbRole.setSort(role.getSort());
        dbRole.setRemark(role.getRemark());
        return super.updateById(role);
    }

    public void setPermisssions(Integer roleId, List<Integer> permissionIds) {
        permissionMapper.deleteByRoleId(roleId);
        if (!CollectionUtils.isEmpty(permissionIds)) {
            permissionMapper.saveRolePermission(roleId, permissionIds);
        }
    }
}

