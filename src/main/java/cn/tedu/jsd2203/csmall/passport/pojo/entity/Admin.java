package cn.tedu.jsd2203.csmall.passport.pojo.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Admin implements Serializable {
    /**
     * 数据id
     */
    @ApiModelProperty(value="数据id")
    private Long id;

    /**
     * 用户名
     */
    @ApiModelProperty(value="用户名")
    private String username;

    /**
     * 密码（密文）
     */
    @ApiModelProperty(value="密码")
    private String password;

    /**
     * 昵称
     */
    @ApiModelProperty(value="昵称")
    private String nickname;

    /**
     * 头像URL
     */
    @ApiModelProperty(value="头像url")
    private String avatar;

    /**
     * 手机号码
     */
    @ApiModelProperty(value="手机号码")
    private String phone;

    /**
     * 电子邮箱
     */
    @ApiModelProperty(value="电子邮箱")
    private String email;

    /**
     * 描述
     */
    @ApiModelProperty(value="描述")
    private String description;

    /**
     * 是否启用，1=启用，0=未启用
     */
    @ApiModelProperty(value="是否启用，1=启用，0=未启用")
    private Integer enable;

    /**
     * 最后登录IP地址（冗余）
     */
    @ApiModelProperty(value="最后登录IP地址")
    private String lastLoginIp;

    /**
     * 累计登录次数（冗余）
     */
    @ApiModelProperty(value="累计登录次数")
    private Integer loginCount;

    /**
     * 最后登录时间（冗余）
     */
    @ApiModelProperty(value="最后登录时间")
    private LocalDateTime gmtLastLogin;

    /**
     * 数据创建时间
     */
    @ApiModelProperty(value="数据创建时间")
    private LocalDateTime gmtCreate;

    /**
     * 数据最后修改时间
     */
    @ApiModelProperty(value="数据最后修改时间")
    private LocalDateTime gmtModified;

}
