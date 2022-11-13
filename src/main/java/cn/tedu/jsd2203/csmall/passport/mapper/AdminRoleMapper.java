package cn.tedu.jsd2203.csmall.passport.mapper;

import cn.tedu.jsd2203.csmall.passport.pojo.entity.AdminRole;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRoleMapper {

    /**
     * 插入管理员与角色关联数据
     * @param adminRole
     * @return
     */
    int insertRole(AdminRole adminRole);
}
