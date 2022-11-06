package cn.tedu.jsd2203.csmall.passport.pojo.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class AdminLoginVo implements Serializable {
    private String username;
    private String password;
}
