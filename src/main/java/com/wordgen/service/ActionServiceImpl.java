package com.wordgen.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.wordgen.appobj.ActionAO;
import com.wordgen.dao.custom.ActionMapperCustom;
import com.wordgen.dao.gen.ActionMapper;
import com.wordgen.model.gen.ActionExample;
import com.wordgen.util.ReqResult;

@Service
public class ActionServiceImpl implements ActionService {

    @Resource
    private ActionMapperCustom actionMapperCustom;
    @Resource
    private ActionMapper actionMapper;

    @Override
    public ActionAO getById(String id) {
        ActionExample example = new ActionExample();
        example.createCriteria().andIdEqualTo(id);
        List<ActionAO> actionList = this.actionMapper.selectByExample(example);
        if (!actionList.isEmpty()) {
            return actionList.get(0);
        }
        return null;
    }

    @Override
    public List<ActionAO> getList(Map<String, Object> queryMap) {
        return this.actionMapperCustom.selectList(queryMap);
    }

    @Override
    public int countList(Map<String, Object> queryMap) {
        return this.actionMapperCustom.countList(queryMap);
    }

    @Override
    public List<ActionAO> getListAuthed(Map<String, Object> queryMap) {
        return this.actionMapperCustom.selectListAuthed(queryMap);
    }

    @Override
    public Map<String, Object> getListMixedWithAuthed(Map<String, Object> queryMap) {
        Map<String, Object> retMap = new HashMap<String, Object>();
        boolean allAuthed = false;
        List<ActionAO> allList = this.actionMapperCustom.selectList(queryMap);
        List<ActionAO> authedList = this.actionMapperCustom.selectListAuthed(queryMap);
        if (!allList.isEmpty() && !authedList.isEmpty()) {
            if (allList.size() == authedList.size()) {
                allAuthed = true;
            }
        }
        for (ActionAO action : allList) {
            for (ActionAO actionx : authedList) {
                if (!StringUtils.isBlank(actionx.getId()) && !StringUtils.isBlank(action.getId())) {
                    if (actionx.getId().equals(action.getId())) {
                        action.setAuthed(true);
                    }
                }
            }
        }
        retMap.put("actionList", allList);
        retMap.put("allAuthed", allAuthed);
        return retMap;
    }

    @Override
    public boolean save(ActionAO action) {
        action.setCreateTime(new Date());
        action.setState(true);
        if (this.actionMapper.insert(action) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public ReqResult<Boolean> update(ActionAO action) {
        ReqResult<Boolean> ret = new ReqResult<Boolean>();
        if (action == null) {
            ret.setSuccess(false);
            ret.setMsg("保存失败！");
            return ret;
        }
        ActionExample example = new ActionExample();
        example.createCriteria().andIdEqualTo(action.getId());
        List<ActionAO> actionList = this.actionMapper.selectByExample(example);
        if (actionList.isEmpty()) {
            ret.setSuccess(false);
            ret.setMsg("保存失败！功能不存在或已被删除！");
            return ret;
        }
        example.clear();
        example.createCriteria().andCodeEqualTo(action.getCode())
                .andModuleIdEqualTo(action.getModuleId());
        actionList = this.actionMapper.selectByExample(example);
        for (ActionAO aa : actionList) {
            if (!aa.getId().equals(action.getId())) {
                ret.setSuccess(false);
                ret.setMsg("保存失败！功能代码已被同模块下的其他功能使用！");
                return ret;
            }
        }
        action.setUpdateTime(new Date());
        if (this.actionMapper.updateByPrimaryKeySelective(action) < 0) {
            ret.setSuccess(false);
            ret.setMsg("保存失败！");
            return ret;
        }
        ret.setSuccess(true);
        return ret;
    }

    @Override
    public ReqResult<Boolean> delete(String actionId) {
        ReqResult<Boolean> ret = new ReqResult<Boolean>();
        if (StringUtils.isBlank(actionId)) {
            ret.setSuccess(false);
            ret.setMsg("删除失败！");
            return ret;
        }
        ActionExample example = new ActionExample();
        example.createCriteria().andIdEqualTo(actionId);
        if (this.actionMapper.deleteByExample(example) < 0) {
            ret.setSuccess(false);
            ret.setMsg("删除失败！");
            return ret;
        }
        ret.setSuccess(true);
        return ret;
    }

}
