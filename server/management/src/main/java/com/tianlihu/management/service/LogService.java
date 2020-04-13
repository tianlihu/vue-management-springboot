package com.tianlihu.management.service;

import com.tianlihu.management.entity.Log;
import com.tianlihu.management.mapper.LogMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 日志 服务实现类
 * </p>
 *
 * @author tianlihu
 * @since 2020-04-13
 */
@Service
@Transactional
public class LogService extends ServiceImpl<LogMapper, Log> {

}
