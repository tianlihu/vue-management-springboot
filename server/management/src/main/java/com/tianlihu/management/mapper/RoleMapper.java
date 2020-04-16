package com.tianlihu.management.mapper;

import com.tianlihu.management.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 角色 Mapper 接口
 * </p>
 *
 * @author tianlihu
 * @since 2020-04-13
 */
@Repository
public interface RoleMapper extends BaseMapper<Role> {

    void deleteByUserId(Integer userId);

    void saveUserRole(Integer userId, Integer roleId);

    List<Integer> getRoleIds(Integer userId);
}
