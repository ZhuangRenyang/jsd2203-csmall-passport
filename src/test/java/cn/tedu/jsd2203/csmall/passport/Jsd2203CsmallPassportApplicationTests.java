package cn.tedu.jsd2203.csmall.passport;

import cn.tedu.jsd2203.csmall.passport.mapper.AdminMapper;
import cn.tedu.jsd2203.csmall.passport.pojo.entity.AdminRole;
import cn.tedu.jsd2203.csmall.passport.pojo.vo.AdminLoginVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class Jsd2203CsmallPassportApplicationTests {
    @Autowired
    AdminMapper adminMapper;

    @Test
    void contextLoads(){
        String username = "root";
        AdminLoginVo byUsername = adminMapper.getByUsername(username);
        log.debug("用户名为,{}",byUsername);
    }


}
