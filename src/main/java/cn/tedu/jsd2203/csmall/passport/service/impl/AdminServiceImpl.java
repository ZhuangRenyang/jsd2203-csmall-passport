package cn.tedu.jsd2203.csmall.passport.service.impl;

import cn.tedu.jsd2203.csmall.passport.config.BeanConfig;
import cn.tedu.jsd2203.csmall.passport.exception.ServiceException;
import cn.tedu.jsd2203.csmall.passport.mapper.AdminMapper;
import cn.tedu.jsd2203.csmall.passport.pojo.dto.AdminAddNewDTO;
import cn.tedu.jsd2203.csmall.passport.pojo.dto.AdminLoginDTO;
import cn.tedu.jsd2203.csmall.passport.pojo.entity.Admin;
import cn.tedu.jsd2203.csmall.passport.pojo.vo.AdminListItemVO;
import cn.tedu.jsd2203.csmall.passport.service.IAdminService;
import cn.tedu.jsd2203.csmall.passport.web.ServiceCode;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 品牌业务实现
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Slf4j
@Service
public class AdminServiceImpl implements IAdminService {

    @Autowired
    public BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public void addNew(AdminAddNewDTO adminAddNewDTO) {
        log.debug("开始处理添加管理员的业务，参数：{}", adminAddNewDTO);

        // 检查此用户名有没有被占用
        String username = adminAddNewDTO.getUsername();
        int count = adminMapper.countByUsername(username);
        if (count > 0) {
            String message = "添加管理员失败，用户名【 " + username + " 】已经被占用！";
            log.error(message);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }
        Admin admin = new Admin();// 创建实体对象
        BeanUtils.copyProperties(adminAddNewDTO, admin);// 将当前方法参数的值复制到实体类型的对象中
        // 补全属性值（不由客户端提交的属性的值，应该在插入之前补全）
        admin.setGmtCreate(BeanConfig.localDateTime());//注册时间

        log.debug("输入的用户名为:{},密码为:{}", admin.getUsername(), admin.getPassword());//输出打印密码
        String rawPassword = admin.getPassword();//将原密码从admin对象取出，加密后再存入到admin对象中
        String encodePassword = passwordEncoder.encode(rawPassword);//加密密码
        admin.setPassword(encodePassword);//插入密码
        // 将管理员数据写入到数据库中
        log.debug("即将向管理员表中写入数据：{}", admin);
        int rows = adminMapper.insert(admin);
        if (rows != 1) {
            String message = "添加管理员失败，服务器忙，请稍后再次尝试！【错误码：1】";
            throw new ServiceException(ServiceCode.ERR_UNKNOWN, message);
        }
        log.info("插入成功,受影响的行数:{}", rows);

    }

    @Override
    public String login(AdminLoginDTO adminLoginDTO) {
        log.debug("开始处理登录的业务:参数:{}", adminLoginDTO);

        //调用 authenticationManager 执行Spring Security的认证
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(
                        adminLoginDTO.getUsername(),
                        adminLoginDTO.getPassword());

        //获取返回结果
        Authentication loginResult = authenticationManager.authenticate(authentication);

        log.debug("登录成功，认证的方法返回结果：{}",loginResult);
        // 从认证结果获取Principal，本质就时User类型，是loadUserByUsername()方法返回的结果
        log.debug("尝试获取Principal：{}",loginResult.getPrincipal());

        User user = (User) loginResult.getPrincipal();
        String username = user.getUsername();
        log.debug("登录成功后的用户名：{}",username);
        //生成jwt
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", user.getUsername());
        //过期时间： 60分钟
        Date expirationDate = new Date(System.currentTimeMillis() + 60 * 60 * 1000);//System.currentTimeMillis();//1970年1月1日0时0分0秒 到 此时此刻 的毫秒值

        String jwt = Jwts.builder()//JWT的组成部分：header（头） ，payload（载荷），signature（签名）
                .setHeaderParam("typ", "jwt") //header: 用于配置算法与此结果数据的类型
                .setHeaderParam("alg", "HS256")
                .setClaims(claims)//payload: 用于配置需要封装到JWT的数据
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, "udiasjia")//signature:用于指定算法与密匙
                .compact(); //打包
        log.debug("加密验证信息{}", jwt);
        //如果能执行到此处，则表示用户名与密码是匹配的(以上方法红户名与密码不匹配则抛出异常)
        return jwt;
    }

    @Override
    public void deleteById(Long id) {
        log.debug("处理删除用户数据的业务，id为:{}", id);
        AdminListItemVO adminListItemVO = adminMapper.getById(id);
        if (adminListItemVO == null) {
            String message = "删除用户失败,删除的数据(id:" + id + ")不存在";
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }
        int rows = adminMapper.deleteById(id);
        if (rows != 1) {
            String message = "删除失败,服务器忙,请稍后重试";
            throw new ServiceException(ServiceCode.ERR_DELETE, message);
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
        if (adminListItemVO == null) {
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
        log.info("修改的用户id为:{},昵称为:{}", id, nickname);
        log.info("修改用户昵称成功~");
    }


}
