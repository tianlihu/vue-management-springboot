package com.tianlihu.management.mapper;

import com.tianlihu.management.entity.Permission;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PersmissionMapperTest {

    @Autowired
    private PermissionMapper permissionMapper;

    @Test
    public void list() {
        List<Permission> permissions = permissionMapper.findByParentId(null);
        permissions.forEach(System.out::println);
    }
}
