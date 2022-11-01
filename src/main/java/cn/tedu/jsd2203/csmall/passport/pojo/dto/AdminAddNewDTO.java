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
    @ApiModelProperty(value = "用户名",required = true,example = "管理员一号")
    @NotBlank(message = "请填写有效名称")
    private String username;

    @ApiModelProperty(value = "密码",required = true,example = "1223")
    @NotBlank(message = "请填写有效密码，密码不能带有空格")
    @Pattern(regexp = AdminValidationConst.REGEXP_PASSWORD,message = AdminValidationConst.MESSAGE_PASSWORD)
    private String password;

    @ApiModelProperty(value = "昵称",required = true,example = "guanLiYuan")
    @NotBlank(message = "请填写有效昵称")
    private String nickname;

    @ApiModelProperty(value = "头像URL",required = true,example = "http://admin/user/list")
    private String avatar;

    @ApiModelProperty(value = "手机号码",required = true,example = "18800030005")
    private String phone;

    @ApiModelProperty(value = "电子邮箱",required = true,example = "fafafae@163.com")
    private String email;

    @ApiModelProperty(value = "描述",example = "我就是我，是不一样的烟火")
    private String description;

    @ApiModelProperty(value = "是否启用",required = true,example = "1")
    @Range(min = 1,max = 2,message = "选择范围1或2")
    private Integer enable;
}
