package cn.tedu.jsd2203.csmall.passport.mapper;

import cn.tedu.jsd2203.csmall.passport.pojo.entity.Admin;
import cn.tedu.jsd2203.csmall.passport.pojo.vo.AdminListItemVO;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AdminMapper {
    int insert(Admin admin);
    int countByUsername(String username);//用户唯一
    List<AdminListItemVO> list();
}
