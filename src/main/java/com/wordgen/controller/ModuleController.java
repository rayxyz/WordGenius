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

import com.wordgen.appobj.ModuleAO;
import com.wordgen.dict.Resources;
import com.wordgen.service.ModuleService;
import com.wordgen.util.CommonUtil;
import com.wordgen.util.ReqResult;

@Controller
@RequestMapping("/module")
public class ModuleController {
    @Resource
    private CommonUtil commonUtil;
    @Resource
    private ModuleService moduleService;
    
    @RequestMapping("/index")
    public String index(Model model) {
        return Resources.Page.MODULE_MGMT;
    }
    
    @RequestMapping("/add_dlg")
    public String add_dlg(Model model) {
        return Resources.Page.MODULE_ADD_DLG;
    }
    
    @RequestMapping("/update_dlg/{module_id}")
    public String update_dlg(Model model, @PathVariable("module_id") String moduleId) {
        model.addAttribute("module", this.moduleService.getById(moduleId));
        return Resources.Page.MODULE_UPDATE_DLG;
    }
    
    @RequestMapping(value="/getList", method=RequestMethod.GET)
    public String getList(Model model, String queryParams) {
        Map<String, Object> queryMap = this.commonUtil.parse2Map(queryParams);
        List<ModuleAO> list = this.moduleService.getList(queryMap);
        model.addAttribute("moduleList", list);
        return Resources.Page.MODULE_LIST;
    }
    
    @RequestMapping(value="/save", method=RequestMethod.POST)
    @ResponseBody
    public Object save(String params) {
        ReqResult<Boolean> ret = new ReqResult<>();
        try {
            ModuleAO ma = (ModuleAO) this.commonUtil.string2Bean(params, ModuleAO.class);
            if (this.moduleService.save(ma)) {
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
    
    @RequestMapping(value="/update", method=RequestMethod.POST)
    @ResponseBody
    public Object update(String params) {
        ReqResult<Boolean> ret = new ReqResult<>();
        try {
            ModuleAO ma = (ModuleAO) this.commonUtil.string2Bean(params, ModuleAO.class);
            ret = this.moduleService.update(ma);
        } catch (Exception e) {
            ret.setSuccess(false);
            ret.setMsg("保存失败！");
        }
        return ret;
    }
    
    @RequestMapping(value="/delete", method=RequestMethod.POST)
    @ResponseBody
    public Object delete(String moduleId) {
        ReqResult<Boolean> ret = new ReqResult<>();
        try {
            ret = this.moduleService.delete(moduleId);
        } catch (Exception e) {
            ret.setSuccess(false);
            ret.setMsg("保存失败！");
        }
        return ret;
    }

}
