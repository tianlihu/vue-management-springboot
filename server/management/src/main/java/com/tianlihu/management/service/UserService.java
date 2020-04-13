package com.tianlihu.management.service;

import com.tianlihu.management.entity.User;
import com.tianlihu.management.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author tianlihu
 * @since 2020-04-13
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User> {

}
