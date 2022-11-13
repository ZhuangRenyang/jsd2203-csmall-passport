package cn.tedu.jsd2203.csmall.passport.security;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Setter
@Getter
@EqualsAndHashCode
@ToString(callSuper = true)

public class AdminDetails extends User {

    private Long id;

    public AdminDetails(String username, String password,
                        boolean enable, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enable,
                true, //账号未过期
                true, //凭证未过期
                true, //账号未锁定
                authorities);
    }
}
