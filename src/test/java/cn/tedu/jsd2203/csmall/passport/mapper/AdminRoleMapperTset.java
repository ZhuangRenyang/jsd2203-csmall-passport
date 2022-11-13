package cn.tedu.jsd2203.csmall.passport.mapper;


import cn.tedu.jsd2203.csmall.passport.pojo.entity.AdminRole;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@Slf4j
@SpringBootTest
public class AdminRoleMapperTset {

    @Autowired
    AdminRoleMapper adminRoleMapper;

    @Test
    public void insertRole(){
        Long adminId = 4L;
        Long roleId = 2L;
        AdminRole adminRole = new AdminRole();
        adminRole.setAdminId(adminId);
        adminRole.setRoleId(roleId);
        int rows = adminRoleMapper.insertRole(adminRole);
        log.debug("插入成功:{}",rows);
    }
}
