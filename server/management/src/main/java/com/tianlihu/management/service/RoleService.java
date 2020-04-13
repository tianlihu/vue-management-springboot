package com.tianlihu.management.service;

import com.tianlihu.management.entity.Role;
import com.tianlihu.management.mapper.RoleMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author tianlihu
 * @since 2020-04-13
 */
@Service
@Transactional
public class RoleService extends ServiceImpl<RoleMapper, Role> {

}
