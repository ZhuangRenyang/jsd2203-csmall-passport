package cn.tedu.jsd2203.csmall.passport.service;


import cn.tedu.jsd2203.csmall.passport.pojo.dto.AdminAddNewDTO;
import cn.tedu.jsd2203.csmall.passport.pojo.dto.AdminLoginDTO;
import cn.tedu.jsd2203.csmall.passport.pojo.vo.AdminListItemVO;

import java.util.List;

public interface IAdminService {

    /**
     * 添加管理员
     *
     * @param adminAddNewDTO 管理员数据
     */
    void addNew(AdminAddNewDTO adminAddNewDTO);

    /**
     * 处理登录业务
     * @param adminLoginDTO 管理员登录信息
     */
    String login(AdminLoginDTO adminLoginDTO);

    void deleteById(Long id);

    /**
     * 查询管理员列表
     *
     * @return 管理员列表的集合
     */
    List<AdminListItemVO> list();

    void updateById(Long id, String nickname);


}
