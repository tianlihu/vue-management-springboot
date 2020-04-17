package com.tianlihu.management.mapper;

import com.tianlihu.management.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 权限 Mapper 接口
 * </p>
 *
 * @author tianlihu
 * @since 2020-04-13
 */
@Repository
public interface PermissionMapper extends BaseMapper<Permission> {

    List<Permission> findByParentId(Integer parentId);

    List<Integer> findLeafPermissionIdsByRoleId(Integer roleId);

    void saveRolePermission(Integer roleId, List<Integer> permissionIds);

    void deleteByRoleId(Integer roleId);
}
