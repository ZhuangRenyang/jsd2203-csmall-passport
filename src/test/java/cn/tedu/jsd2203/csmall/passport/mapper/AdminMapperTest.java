package cn.tedu.jsd2203.csmall.passport.mapper;

import cn.tedu.jsd2203.csmall.passport.pojo.entity.Admin;
import cn.tedu.jsd2203.csmall.passport.pojo.vo.AdminLoginVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class AdminMapperTest {

    @Autowired
    AdminMapper adminMapper;

    @Test
    public void testInsert(){
        Admin admin = new Admin();
        admin.setUsername("hhh");
        int insert = adminMapper.insert(admin);
        log.info("插入成功,受影响的行数为:{}",insert);
    }

    //	AdminLoginVo getByUsername(String username);
    @Test
    void textGetByUsername() {

        AdminLoginVo byUsername = adminMapper.getByUsername("qqqq");
        log.debug("用户名为:{}",byUsername);
    }
}
