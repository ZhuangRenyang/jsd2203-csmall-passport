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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AdminServiceImpl implements IAdminService {

    @Autowired
    public BCryptPasswordEncoder passwordEncoder;

    @Autowired
    AdminMapper adminMapper;

    @Override
    public void addNew(AdminAddNewDTO adminAddNewDTO) {
        log.debug("开始处理添加管理员的业务，参数：{}", adminAddNewDTO);

        String name = adminAddNewDTO.getUsername();
        // 检查此用户名有没有被占用
        int count = adminMapper.countByUsername(name);
        if (count > 0){
            String message = "添加管理员失败，用户名【\" + username + \"】已经被占用！";
            log.error(message);
            throw new ServiceException(ServiceCode.ERR_CONFLICT,message);
        }
        Admin admin = new Admin();// 创建实体对象
        BeanUtils.copyProperties(adminAddNewDTO, admin);// 将当前方法参数的值复制到实体类型的对象中
        // 补全属性值（不由客户端提交的属性的值，应该在插入之前补全）
        admin.setGmtCreate(BeanConfig.localDateTime());//注册时间

        String rawPassword = admin.getPassword();//将原密码从admin对象取出，加密后再存入到admin对象中
        String encodePassword = passwordEncoder.encode(rawPassword);//加密密码
        admin.setPassword(encodePassword);//插入密码
        // 将管理员数据写入到数据库中
        log.debug("即将向管理员表中写入数据：{}", admin);
        int rows = adminMapper.insert(admin);
        if (rows !=1){
            String message = "添加管理员失败，服务器忙，请稍后再次尝试！【错误码：1】";
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
