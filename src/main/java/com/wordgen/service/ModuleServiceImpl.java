package com.wordgen.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.wordgen.appobj.ActionAO;
import com.wordgen.appobj.ModuleAO;
import com.wordgen.dao.custom.ModuleMapperCustom;
import com.wordgen.dao.gen.ActionMapper;
import com.wordgen.dao.gen.ModuleMapper;
import com.wordgen.dao.gen.RoleActionMapper;
import com.wordgen.model.gen.ActionExample;
import com.wordgen.model.gen.ModuleExample;
import com.wordgen.model.gen.RoleActionExample;
import com.wordgen.util.ReqResult;

@Service
public class ModuleServiceImpl implements ModuleService {
    
    @Resource
    private ModuleMapperCustom moduleMapperCustom;
    @Resource
    private ModuleMapper moduleMapper;
    @Resource
    private ActionMapper actionMapper;
    @Resource
    private RoleActionMapper roleActionMapper;
    
    @Override
    public ModuleAO getById(String id) {
        ModuleExample example = new ModuleExample();
        example.createCriteria().andIdEqualTo(id);
        List<ModuleAO> moduleList = this.moduleMapper.selectByExample(example);
        if (moduleList.size() > 0) {
            return moduleList.get(0);
        }
        return null;
    }

    @Override
    public List<ModuleAO> getList(Map<String, Object> queryMap) {
        return this.moduleMapperCustom.selectList(queryMap);
    }

    @Override
    public int countList(Map<String, Object> queryMap) {
        return this.moduleMapperCustom.countList(queryMap);
    }
    
    @Override
    public boolean save(ModuleAO ma) {
        ma.setCreateTime(new Date());
        if (this.moduleMapper.insert(ma) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public ReqResult<Boolean> update(ModuleAO module) {
        ReqResult<Boolean> ret = new ReqResult<Boolean>();
        if (module == null) {
            ret.setSuccess(false);
            ret.setMsg("保存失败！");
            return ret;
        }
        ModuleExample example = new ModuleExample();
        example.createCriteria().andIdEqualTo(module.getId());
        List<ModuleAO> moduleList = this.moduleMapper.selectByExample(example);
        if (moduleList.isEmpty()) {
            ret.setSuccess(false);
            ret.setMsg("保存失败！模块不存在或已被删除！");
            return ret;
        }
        example.clear();
        example.createCriteria().andNameEqualTo(module.getName());
        List<ModuleAO> moduleList0 = this.moduleMapper.selectByExample(example);
        if (!moduleList.isEmpty()) {
            for (ModuleAO ma : moduleList0) {
                if (!ma.getId().equals(module.getId())) {
                    ret.setSuccess(false);
                    ret.setMsg("保存失败！已存在重名的其他模块！");
                    return ret;
                }
            }
        }
        example.clear();
        example.createCriteria().andCodeEqualTo(module.getCode());
        List<ModuleAO> moduleList1 = this.moduleMapper.selectByExample(example);
        if (!moduleList0.isEmpty()) {
            for (ModuleAO ma : moduleList1) {
                if (!ma.getId().equals(module.getId())) {
                    ret.setSuccess(false);
                    ret.setMsg("保存失败！已存在相同的模块代码的其他模块！");
                    return ret;
                }
            }
        }
        if (this.moduleMapper.updateByPrimaryKeySelective(module) < 0) {
            ret.setSuccess(false);
            ret.setMsg("保存失败！");
            return ret;
        }
        ret.setSuccess(true);
        return ret;
    }

    @Override
    public ReqResult<Boolean> delete(String moduleId) {
        ReqResult<Boolean> ret = new ReqResult<Boolean>();
        if (StringUtils.isBlank(moduleId)) {
            ret.setSuccess(false);
            ret.setMsg("删除失败！");
            return ret;
        }
        
        // Delete relations related to module to be deleted.
        ActionExample ae = new ActionExample();
        ae.createCriteria().andModuleIdEqualTo(moduleId);
        List<ActionAO> actionList = this.actionMapper.selectByExample(ae);
        List<String> actionIdList = new ArrayList<String>();
        for (ActionAO action : actionList) {
            actionIdList.add(action.getId());
        }
        // Delete action records
        this.actionMapper.deleteByExample(ae);
        RoleActionExample rae = new RoleActionExample();
        rae.createCriteria().andActionIdIn(actionIdList);
        // Delete role_action records
        this.roleActionMapper.deleteByExample(rae);
        
        if (this.moduleMapper.deleteByPrimaryKey(moduleId) < 0) {
            throw new RuntimeException("删除失败！");
        }
        ret.setSuccess(true);
        return ret;
    }

}
