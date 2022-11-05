package cn.tedu.jsd2203.csmall.passport.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * Mybatis配置类
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@MapperScan("cn.tedu.jsd2203.csmall.passport.mapper")
@Configuration
public class MybatisConfiguration {
    public MybatisConfiguration() {
        System.out.println("加载配置类.MybatisConfiguration");
    }
}
