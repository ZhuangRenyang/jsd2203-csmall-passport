package cn.tedu.jsd2203.csmall.passport.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AdminLoginVo implements Serializable {

    /**
     * 管理员id
     */
    private Long id;

    /**
     * 管理员名称
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 账号是否启用 1-启用 0-未启用
     */
    private Integer enable;

    /**
     * 此账号的权限列表
     */
    private List<String> permissions;
}
