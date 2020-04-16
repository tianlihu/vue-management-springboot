package com.tianlihu.management.mapper;

import com.tianlihu.management.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author tianlihu
 * @since 2020-04-13
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    User findByAccount(String username);
}
