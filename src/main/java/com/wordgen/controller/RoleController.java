package com.wordgen.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wordgen.appobj.RoleAO;
import com.wordgen.dict.Resources;
import com.wordgen.service.RoleService;
import com.wordgen.util.CommonUtil;
import com.wordgen.util.ReqResult;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Resource
    private CommonUtil commonUtil;
    @Resource
    private RoleService roleService;
    
    @RequestMapping("/index")
    public String index(Model model) {
        return Resources.Page.ROLE_MGMT;
    }
    
    @RequestMapping("/add_dlg")
    public String add_dlg(Model model) {
        return Resources.Page.ROLE_ADD_DLG;
    }
    
    @RequestMapping("/update_dlg/{roleId}")
    public String update_dlg(Model model, @PathVariable("roleId") String roleId) {
        if (StringUtils.isBlank(roleId)) {
            throw new RuntimeException("角色id为空！");
        }
        RoleAO role = this.roleService.getById(roleId);
        model.addAttribute("role", role);
        return Resources.Page.ROLE_UPDATE_DLG;
    }
    
    @RequestMapping(value="/getList", method=RequestMethod.GET)
    public String getList(Model model, String queryParams) {
        Map<String, Object> queryMap = this.commonUtil.parse2Map(queryParams);
        List<RoleAO> list = this.roleService.getList(queryMap);
        model.addAttribute("roleList", list);
        return Resources.Page.ROLE_LIST;
    }
    
    @RequestMapping("/save")
    @ResponseBody
    public Object save(String params) {
        ReqResult<Boolean> ret = new ReqResult<>();
        try {
            RoleAO ra = (RoleAO) this.commonUtil.string2Bean(params, RoleAO.class);
            ret.setSuccess(this.roleService.save(ra));
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
            RoleAO ra = (RoleAO) this.commonUtil.string2Bean(params, RoleAO.class);
            ret = this.roleService.update(ra);
        } catch (Exception e) {
            e.printStackTrace();
            ret.setSuccess(false);
            ret.setMsg("保存失败！");
        }
        return ret;
    }
    
    @RequestMapping(value="/delete/{roleId}", method={RequestMethod.POST})
    @ResponseBody
    public Object delete(@PathVariable("roleId") String roleId) {
        ReqResult<Boolean> ret = new ReqResult<>();
        try {
            ret = this.roleService.delete(roleId);
        } catch (Exception e) {
            e.printStackTrace();
            ret.setSuccess(false);
            ret.setMsg("删除失败！");
        }
        return ret;
    }
}
