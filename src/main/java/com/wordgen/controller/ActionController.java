package com.wordgen.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wordgen.appobj.ActionAO;
import com.wordgen.appobj.ModuleAO;
import com.wordgen.dict.Resources;
import com.wordgen.service.ActionService;
import com.wordgen.service.ModuleService;
import com.wordgen.util.CommonUtil;
import com.wordgen.util.ReqResult;

@Controller
@RequestMapping("/action")
public class ActionController {
    
    @Resource
    private CommonUtil commonUtil;
    @Resource
    private ActionService actionService;
    @Resource
    private ModuleService moduleService;

    @RequestMapping("/index/{moduleId}")
    public String index(Model model, @PathVariable("moduleId") String moduleId) {
        ModuleAO ma = this.moduleService.getById(moduleId);
        model.addAttribute("module", ma);
        return Resources.Page.ACTION_MGMT;
    }
    
    @RequestMapping("/add_dlg")
    public String add_dlg(Model model) {
        return Resources.Page.ACTION_ADD_DLG;
    }
    
    @RequestMapping("/update_dlg/{action_id}")
    public String update_dlg(Model model, @PathVariable("action_id") String actionId) {
        model.addAttribute("action", this.actionService.getById(actionId));
        return Resources.Page.ACTION_UPDATE_DLG;
    }
    
    @RequestMapping("/getList")
    public String getList(Model model, String queryParams) {
        Map<String, Object> queryMap = this.commonUtil.parse2Map(queryParams);
        List<ActionAO> list = this.actionService.getList(queryMap);
        model.addAttribute("actionList", list);
        return Resources.Page.ACTION_LIST;
    }
    
    @RequestMapping("/getListAuth")
    public String getListAuth(Model model, String queryParams) {
        Map<String, Object> queryMap = this.commonUtil.parse2Map(queryParams);
        queryMap.put("state", true);
        Map<String, Object> retMap = this.actionService.getListMixedWithAuthed(queryMap);
        model.addAttribute("actionList", retMap.get("actionList"));
        model.addAttribute("allAuthed", retMap.get("allAuthed"));
        return Resources.Page.AUTH_ACTION_LIST;
    }
    
    @RequestMapping(value="/save", method={RequestMethod.POST})
    @ResponseBody
    public Object save(String params) {
        ReqResult<Boolean> ret = new ReqResult<>();
        try {
            ActionAO aa = (ActionAO) this.commonUtil.string2Bean(params, ActionAO.class);
            if (this.actionService.save(aa)) {
                ret.setSuccess(true);
            } else {
                ret.setSuccess(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ret.setSuccess(false);
        }
        return ret;
    }
    
    @RequestMapping(value="/update", method={RequestMethod.POST})
    @ResponseBody
    public Object update(String params) {
        ReqResult<Boolean> ret = new ReqResult<>();
        try {
            ActionAO aa = (ActionAO) this.commonUtil.string2Bean(params, ActionAO.class);
            ret = this.actionService.update(aa);
        } catch (Exception e) {
            e.printStackTrace();
            ret.setSuccess(false);
            ret.setMsg("保存失败！");
        }
        return ret;
    }
    
    @RequestMapping(value="/delete", method={RequestMethod.POST})
    @ResponseBody
    public Object delete(String actionId) {
        ReqResult<Boolean> ret = new ReqResult<>();
        try {
            ret = this.actionService.delete(actionId);
        } catch (Exception e) {
            e.printStackTrace();
            ret.setSuccess(false);
            ret.setMsg("保存失败！");
        }
        return ret;
    }
    
}
