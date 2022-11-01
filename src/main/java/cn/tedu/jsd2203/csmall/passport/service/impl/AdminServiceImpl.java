package cn.tedu.jsd2203.csmall.passport.service.impl;

import cn.tedu.jsd2203.csmall.passport.exception.ServiceException;
import cn.tedu.jsd2203.csmall.passport.mapper.AdminMapper;
import cn.tedu.jsd2203.csmall.passport.pojo.dto.AdminAddNewDTO;
import cn.tedu.jsd2203.csmall.passport.pojo.entity.Admin;
import cn.tedu.jsd2203.csmall.passport.service.IAdminService;
import cn.tedu.jsd2203.csmall.passport.web.ServiceCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AdminServiceImpl implements IAdminService {
    @Autowired
    AdminMapper adminMapper;

    @Override
    public void addNew(AdminAddNewDTO adminAddNewDTO) {
        String name = adminAddNewDTO.getUsername();
        int count = adminMapper.countByUsername(name);
        if (count > 0){
            String message = "注册失败用户名称已存在";
            log.error(message);
            throw new ServiceException(ServiceCode.ERR_CONFLICT,message);
        }
        Admin admin = new Admin();
        BeanUtils.copyProperties(adminAddNewDTO, admin);
        int rows = adminMapper.insert(admin);
        if (rows !=1){
            String message = "添加用户失败，服务器忙，请稍后重试";
            throw new ServiceException(ServiceCode.ERR_UNKNOWN,message);
        }
        log.info("插入成功,受影响的行数:{}", rows);

    }
}
