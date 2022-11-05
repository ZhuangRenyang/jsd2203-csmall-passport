package cn.tedu.jsd2203.csmall.passport.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class AdminListItemVO implements Serializable {

    /**
     * 数据id
     */
    @ApiModelProperty(value = "数据id")
    private Long id;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String username;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickname;

    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码")
    private String phone;

    /**
     * 电子邮箱
     */
    @ApiModelProperty(value = "电子邮箱")
    private String email;

    /**
     * 是否启用，1=启用，0=未启用
     */
    @ApiModelProperty(value = "是否启用，1=启用，0=未启用")
    private Integer enable;

    private String lastLoginIp;

    private Integer loginCount;

    private String gmtLastLogin;

    private String gmtCreate;

    private String gmtModified;
}
