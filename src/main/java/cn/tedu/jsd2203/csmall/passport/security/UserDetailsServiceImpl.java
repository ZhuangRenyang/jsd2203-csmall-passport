package cn.tedu.jsd2203.csmall.passport.security;

import cn.tedu.jsd2203.csmall.passport.mapper.AdminMapper;
import cn.tedu.jsd2203.csmall.passport.pojo.vo.AdminLoginVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    AdminMapper adminMapper;

    /**
     * 并获取返回的UserDetails对象
     * Spring Security会自动将登录界面中获取的密码原文进行加密，
     * 并与`UserDetails`中的密文进行对比，以判断是否可以成功登录
     *
     * @param s 用户名
     * @return 用户详情对象
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        log.debug("Spring Security 根据用户名[{}]查询用户信息", s);

        AdminLoginVo adminLoginVo = adminMapper.getByUsername(s);

        if (adminLoginVo != null) {
            List<String> permissions = adminLoginVo.getPermissions();
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            for (String permission : permissions) {
                authorities.add(new SimpleGrantedAuthority(permission));
            }
            AdminDetails adminDetails = new AdminDetails(
                    adminLoginVo.getUsername(),
                    adminLoginVo.getPassword(),
                    adminLoginVo.getEnable() == 1,
                    authorities
            );
            adminDetails.setId(adminLoginVo.getId());
            return adminDetails;
        }
        return null;
    }


}
