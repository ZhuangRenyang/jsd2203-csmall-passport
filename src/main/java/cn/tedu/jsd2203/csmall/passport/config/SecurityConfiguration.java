package cn.tedu.jsd2203.csmall.passport.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {//白名单
        http.csrf().disable(); //禁用跨域访问

        // /*  - 匹配1层级路径，例如"/admins","/brands"..
        // /** - 匹配若干层级路径
        http.authorizeRequests()//请求需要被授权才可以访问
                .antMatchers("/**") //匹配某些路径
                .permitAll();//允许直接访问(不需要经过认证授权)
    }
}
