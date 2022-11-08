package cn.tedu.jsd2203.csmall.passport.controller;

import cn.tedu.jsd2203.csmall.passport.pojo.dto.AdminAddNewDTO;
import cn.tedu.jsd2203.csmall.passport.pojo.dto.AdminLoginDTO;
import cn.tedu.jsd2203.csmall.passport.pojo.vo.AdminListItemVO;
import cn.tedu.jsd2203.csmall.passport.service.IAdminService;
import cn.tedu.jsd2203.csmall.passport.validation.AdminValidationConst;
import cn.tedu.jsd2203.csmall.passport.web.JsonResult;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * 管理员控制器
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Slf4j
@RestController
@Api(tags = "用户管理")
@RequestMapping("/admins")
public class AdminController {
    @Autowired
    private IAdminService adminService;

    public AdminController() {
        log.info("AdminController");
    }

    // http://localhost:9081/admins/add-new
    @ApiOperation("用户注册")
    @ApiOperationSupport(order = 10)
    @PostMapping("/add-new")
    public JsonResult addNew(@RequestBody @Valid AdminAddNewDTO adminAddNewDTO) {
        log.info("接收到要添加的管理员账号,{}", adminAddNewDTO);
        //需要调用业务层的方法
        adminService.addNew(adminAddNewDTO);
        return JsonResult.ok(AdminValidationConst.OK_USERNAME);
    }

    // http://localhost:9081/admins
    @ApiOperation("用户列表")
    @ApiOperationSupport(order = 20)
    @GetMapping("")
    public JsonResult list() {
        log.info("AdminController.list");
        List<AdminListItemVO> list = adminService.list();
        return JsonResult.ok(list);
    }

    @ApiOperation("删除用户")
    @ApiOperationSupport(order = 30)
    @PostMapping("/{id:[0-9]+}/delete")
    public JsonResult delete(@PathVariable("id") Long id) {
        log.info("接收到删除用户的请求,参数id:{}", id);
        adminService.deleteById(id);
        return JsonResult.ok();
    }

    @ApiOperation("修改昵称")
    @ApiOperationSupport(order = 40)
    @PostMapping("/{id:[0-9]+}/update/{nickname}")
    public JsonResult update(@PathVariable("id") Long id, @PathVariable("nickname") String nickname) {
        log.info("接收到修改用户的请求,参数id:{},参数名称:{}", id, nickname);
        adminService.updateById(id, nickname);
        return JsonResult.ok();
    }

    // http://localhost:9081/admins
    @ApiOperation("管理员登录")
    @ApiOperationSupport(order = 50)
    @PostMapping("/login")
    public JsonResult login(AdminLoginDTO adminLoginDTO) {
// 请求 ==> Controller ==> Service ==> SecurityConfiguration类方法{AuthenticationManager()} ==> UserDetailsServiceImpl ==> Mapper
        log.debug("接收到用户登录请求:{}", adminLoginDTO);
        //需要调用业务层的方法
        String jwt = adminService.login(adminLoginDTO);
        return JsonResult.ok(jwt);
    }
}
