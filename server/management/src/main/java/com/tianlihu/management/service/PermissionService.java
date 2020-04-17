package com.tianlihu.management.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tianlihu.management.entity.Permission;
import com.tianlihu.management.mapper.PermissionMapper;
import com.tianlihu.management.query.PermissionQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>权限 服务实现类</p>
 *
 * @author tianlihu
 * @since 2020-04-14
 */
@Service
@Transactional
public class PermissionService extends ServiceImpl<PermissionMapper, Permission> {

    public IPage<Permission> page(PermissionQuery query) {
        QueryWrapper<Permission> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(query.getName())) {
            wrapper.like("name", query.getName());
        }
        if (query.getMenu() != null) {
            wrapper.eq("menu", query.getMenu());
        }
        return super.page(query, wrapper);
    }

    public List<Permission> findByParentId(Integer parentId) {
        return getBaseMapper().findByParentId(parentId);
    }

    @Override
    public boolean updateById(Permission permission) {
        Permission dbPermission = getById(permission.getPermissionId());
        dbPermission.setParentId(permission.getParentId());
        dbPermission.setName(permission.getName());
        dbPermission.setMenu(permission.getMenu());
        dbPermission.setUrl(permission.getUrl());
        dbPermission.setIcon(permission.getIcon());
        dbPermission.setSort(permission.getSort());
        return super.updateById(permission);
    }
}

