package cn.tedu.jsd2203.csmall.passport.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@MapperScan("cn.tedu.jsd2203.csmall.passport.mapper")
@Configuration
public class MybatisConfiguration {
    public MybatisConfiguration() {
        System.out.println("加载配置类.MybatisConfiguration");
    }
}
