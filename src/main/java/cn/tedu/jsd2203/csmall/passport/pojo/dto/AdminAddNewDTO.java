package cn.tedu.jsd2203.csmall.passport.pojo.dto;

import cn.tedu.jsd2203.csmall.passport.validation.AdminValidationConst;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 管理员列表
 */
@Data
public class AdminAddNewDTO implements Serializable {

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名",required = true,example = "管理员一号")
    @NotBlank(message = "请填写有效名称")
    private String username;

    /**
     * 密码（密文）
     */
    @ApiModelProperty(value = "密码",required = true,example = "1223")
    @NotBlank(message = "请填写有效密码，密码不能带有空格")
    @Pattern(regexp = AdminValidationConst.REGEXP_PASSWORD,message = AdminValidationConst.MESSAGE_PASSWORD)
    private String password;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称",required = true,example = "guanLiYuan")
    @NotBlank(message = "请填写有效昵称")
    private String nickname;

    /**
     * 头像URL
     */
    @ApiModelProperty(value = "头像URL",required = true,example = "http://admin/user/list")
    private String avatar;

    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码",required = true,example = "18800030005")
    private String phone;

    /**
     * 电子邮箱
     */
    @ApiModelProperty(value = "电子邮箱",required = true,example = "fafafae@163.com")
    private String email;

    /**
     * 描述
     */
    @ApiModelProperty(value = "介绍",example = "我就是我，是不一样的烟火")
    private String description;

    /**
     * 是否启用，1=启用，0=未启用
     */
    @ApiModelProperty(value = "是否启用",required = true,example = "1")
    @Range(min = 0,max = 1,message = "选择范围0或1")
    private Integer enable;
}
