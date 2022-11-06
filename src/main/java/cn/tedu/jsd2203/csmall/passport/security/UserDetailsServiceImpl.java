package cn.tedu.jsd2203.csmall.passport.security;

import cn.tedu.jsd2203.csmall.passport.mapper.AdminMapper;
import cn.tedu.jsd2203.csmall.passport.pojo.vo.AdminLoginVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    AdminMapper adminMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        log.debug("Spring Security 根据用户名[{}]查询用户信息",s);

        AdminLoginVo adminLoginVo = adminMapper.getByUsername(s);

        /**
         * 并获取返回的UserDetails对象
         *      Spring Security会自动将登录界面中获取的密码原文进行加密，
         *      并与`UserDetails`中的密文进行对比，以判断是否可以成功登录
         * @param s 用户名
         * @return 用户详情对象
         * @throws UsernameNotFoundException
         */
        if(adminLoginVo != null){
            UserDetails userDetails = User.builder()
                    .username(adminLoginVo.getUsername())
                    .password(adminLoginVo.getPassword())
                    .disabled(false)//账号是否禁用
                    .accountLocked(false)//账号是否锁定
                    .accountExpired(false)//账号是否过期
                    .credentialsExpired(false)//认证是否过期
                    .authorities("指定权限(任意字符串)")//*必须给此账号权限信息
                    .build();
            log.debug("userDetails: {}",userDetails);
            return userDetails;
        }
        return null;
    }



}
