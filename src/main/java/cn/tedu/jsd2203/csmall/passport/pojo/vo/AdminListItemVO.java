package cn.tedu.jsd2203.csmall.passport.pojo.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class AdminListItemVO implements Serializable {
    private Long id;
    private String username;
    private String password;
    private String nickname;
    private String avatar;
    private String phone;
    private String email;
    private String description;
    private Integer enable;
    private String lastLoginIp;
    private Integer loginCount;
    private String gmtLastLogin;
    private String gmtCreate;
    private String gmtModified;
}
