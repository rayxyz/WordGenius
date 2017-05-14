package com.wordgen.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.wordgen.appobj.RoleAO;
import com.wordgen.dao.custom.RoleMapperCustom;
import com.wordgen.dao.gen.RoleMapper;
import com.wordgen.model.gen.RoleExample;
import com.wordgen.util.ReqResult;

@Service
public class RoleServiceImpl implements RoleService {
    
    @Resource
    private RoleMapperCustom roleMapperCustom;
    @Resource
    private RoleMapper roleMapper;

    @Override
    public List<RoleAO> getAll() {
        RoleExample example = new RoleExample();
        example.createCriteria();
        return roleMapper.selectByExample(example);
    }

    @Override
    public int countAll() {
        RoleExample example = new RoleExample();
        example.createCriteria();
        return roleMapper.countByExample(example);
    }

    @Override
    public RoleAO getById(String roleId) {
        RoleExample example = new RoleExample();
        example.createCriteria().andIdEqualTo(roleId);
        List<RoleAO> roleList = this.roleMapper.selectByExample(example);
        if (!roleList.isEmpty()) {
            return roleList.get(0);
        }
        return null;
    }

    @Override
    public List<RoleAO> getList(Map<String, Object> queryMap) {
        return this.roleMapperCustom.selectList(queryMap);
    }

    @Override
    public int countList(Map<String, Object> queryMap) {
        return this.roleMapperCustom.countList(queryMap);
    }

    @Override
    public boolean save(RoleAO ra) {
        if (ra == null) {
            return false;
        }
        if (!this.checkUnique(ra)) {
            return false;
        }
        ra.setCreateTime(new Date());
        if (this.roleMapper.insert(ra) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public ReqResult<Boolean> update(RoleAO ra) {
        ReqResult<Boolean> ret = new ReqResult<>();
        if (ra == null) {
            ret.setSuccess(false);
            ret.setMsg("保存失败！");
            return ret;
        }
        RoleExample example = new RoleExample();
        example.createCriteria().andIdEqualTo(ra.getId());
        List<RoleAO> roleList = this.roleMapper.selectByExample(example);
        if (roleList.isEmpty()) {
            ret.setSuccess(false);
            ret.setMsg("保存失败，角色不存在或已被删除！");
            return ret;
        }
        if (!this.checkUnique(ra)) {
            ret.setSuccess(false);
            ret.setMsg("保存失败，角色已存在！");
            return ret;
        }
        ra.setUpdateTime(new Date());
        if (this.roleMapper.updateByPrimaryKeySelective(ra) < 0) {
            ret.setSuccess(false);
            ret.setMsg("保存失败！");
            return ret;
        }
        ret.setSuccess(true);
        return ret;
    }
    
    private boolean checkUnique(RoleAO ra) {
        RoleExample example = new RoleExample();
        example.createCriteria().andNameEqualTo(ra.getName());
        List<RoleAO> roleList = this.roleMapper.selectByExample(example);
        if (!roleList.isEmpty()) {
            if (roleList.size() > 1) {
                return false;
            }
            for (RoleAO role : roleList) {
                if (!role.getId().equals(ra.getId())) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public ReqResult<Boolean> delete(String roleId) {
        ReqResult<Boolean> ret = new ReqResult<>();
        if (StringUtils.isBlank(roleId)) {
            ret.setSuccess(false);
            ret.setMsg("删除失败！");
            return ret;
        }
        RoleExample example = new RoleExample();
        example.createCriteria().andIdEqualTo(roleId);
        if (this.roleMapper.deleteByExample(example) < 0) {
            ret.setSuccess(false);
            ret.setMsg("删除失败！");
            return ret;
        }
        ret.setSuccess(true);
        return ret;
    }

}
