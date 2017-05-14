package com.wordgen.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wordgen.appobj.RoleActionAO;
import com.wordgen.dao.gen.RoleActionMapper;
import com.wordgen.model.gen.RoleActionExample;

@Service
public class RoleActionServiceImpl implements RoleActionService {
    
    @Resource
    private RoleActionMapper roleActionMapper;

    @Override
    public boolean save(String roleId, List<String> actionIdList) {
        RoleActionExample rae = new RoleActionExample();
        rae.createCriteria().andRoleIdEqualTo(roleId);
        roleActionMapper.deleteByExample(rae);
        for (String actionId : actionIdList) {
            RoleActionAO raa = new RoleActionAO();
            raa.setRoleId(roleId);
            raa.setActionId(actionId);
            raa.setCreateTime(new Date());
            if (this.roleActionMapper.insert(raa) < 0) {
                return false;
            }
        }
        return true;
    }

}
