package cn.tedu.jsd2203.csmall.passport.controller;

import cn.tedu.jsd2203.csmall.passport.pojo.dto.AdminAddNewDTO;
import cn.tedu.jsd2203.csmall.passport.pojo.vo.AdminListItemVO;
import cn.tedu.jsd2203.csmall.passport.service.IAdminService;
import cn.tedu.jsd2203.csmall.passport.validation.AdminValidationConst;
import cn.tedu.jsd2203.csmall.passport.web.JsonResult;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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



    @ApiOperation("用户注册")
    @ApiOperationSupport(order = 10)
    @PostMapping("/add-new")
    public JsonResult addNew(@RequestBody @Valid AdminAddNewDTO adminAddNewDTO){
        log.info("接收到要添加的管理员账号,{}",adminAddNewDTO);
        adminService.addNew(adminAddNewDTO);
        return JsonResult.ok(AdminValidationConst.OK_USERNAME);
    }

    @ApiOperation("用户列表")
    @ApiOperationSupport(order = 20)
    @GetMapping("")
    public JsonResult list(){
        log.info("AdminController.list");
        List<AdminListItemVO> list = adminService.list();
        return JsonResult.ok(list);
    }
}
