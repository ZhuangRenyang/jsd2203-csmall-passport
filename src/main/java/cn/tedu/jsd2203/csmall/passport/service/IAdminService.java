package cn.tedu.jsd2203.csmall.passport.service;


import cn.tedu.jsd2203.csmall.passport.pojo.dto.AdminAddNewDTO;
import cn.tedu.jsd2203.csmall.passport.pojo.vo.AdminListItemVO;

import java.util.List;

public interface IAdminService {

    void addNew(AdminAddNewDTO adminAddNewDTO);

    void deleteById(Long id);

    List<AdminListItemVO> list();

    void updateById(Long id, String nickname);
}
