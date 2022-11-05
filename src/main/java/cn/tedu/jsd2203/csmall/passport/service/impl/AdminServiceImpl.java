package cn.tedu.jsd2203.csmall.passport.service.impl;

import cn.tedu.jsd2203.csmall.passport.config.BeanConfig;
import cn.tedu.jsd2203.csmall.passport.exception.ServiceException;
import cn.tedu.jsd2203.csmall.passport.mapper.AdminMapper;
import cn.tedu.jsd2203.csmall.passport.pojo.dto.AdminAddNewDTO;
import cn.tedu.jsd2203.csmall.passport.pojo.entity.Admin;
import cn.tedu.jsd2203.csmall.passport.pojo.vo.AdminListItemVO;
import cn.tedu.jsd2203.csmall.passport.service.IAdminService;
import cn.tedu.jsd2203.csmall.passport.web.ServiceCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        admin.setGmtCreate(BeanConfig.localDateTime());
        int rows = adminMapper.insert(admin);
        if (rows !=1){
            String message = "添加用户失败，服务器忙，请稍后重试";
            throw new ServiceException(ServiceCode.ERR_UNKNOWN,message);
        }
        log.info("插入成功,受影响的行数:{}", rows);

    }

    @Override
    public void deleteById(Long id) {
        log.debug("处理删除用户数据的业务，id为:{}",id);
        AdminListItemVO adminListItemVO = adminMapper.getById(id);
        if (adminListItemVO == null){
            String message = "删除用户失败,删除的数据(id:"+id+")不存在";
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND,message);
        }
        int rows = adminMapper.deleteById(id);
        if (rows != 1){
            String message = "删除失败,服务器忙,请稍后重试";
            throw new ServiceException(ServiceCode.ERR_DELETE,message);
        }
    }

    @Override
    public List<AdminListItemVO> list() {
        log.debug("处理查询用户列表的业务...");
        return adminMapper.list();
    }

    @Override
    public void updateById(Long id, String nickname) {
        AdminListItemVO adminListItemVO = adminMapper.getById(id);
        if (adminListItemVO == null){
            String message = "修改用户昵称失败，修改的数据(id:" + id + ")不存在";
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }

        Admin admin = new Admin();
        admin.setId(id);
        admin.setNickname(nickname);
        int rows = adminMapper.updateAdmin(admin);
        if (rows != 1) {
            String message = "修改类别名称失败，服务器忙，请稍后重试~";
            throw new ServiceException(ServiceCode.ERR_DELETE, message);
        }
        log.info("修改的用户id为:{},昵称为:{}",id,nickname);
        log.info("修改用户昵称成功~");
    }


}
