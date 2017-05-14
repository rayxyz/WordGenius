package com.wordgen.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wordgen.dict.Resources;
import com.wordgen.service.AuthService;
import com.wordgen.service.ModuleService;
import com.wordgen.service.RoleActionService;
import com.wordgen.service.RoleService;
import com.wordgen.util.CommonUtil;
import com.wordgen.util.ReqResult;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Resource
    private AuthService authService;
    @Resource
    private RoleService roleService;
    @Resource
    private ModuleService moduleService;
    @Resource
    private RoleActionService roleActionService;
    @Resource
    private CommonUtil commonUtil;
    
    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("roleList", this.roleService.getList(null));
        model.addAttribute("moduleList", this.moduleService.getList(null));
        return Resources.Page.AUTH_MGMT;
    }
    
    @RequestMapping(value="/save", method={RequestMethod.POST})
    @ResponseBody
    public Object save(String params) {
        ReqResult<Boolean> rr = new ReqResult<>();
        try {
            Map<String, Object> paramMap = this.commonUtil.parse2Map(params);
            String roleId = (String) paramMap.get("roleId");
            @SuppressWarnings("unchecked")
            List<String> actionIdList = (List<String>) paramMap.get("actionIds");
            if (StringUtils.isBlank(roleId)) {
                rr.setSuccess(false);
            } else {
                rr.setSuccess(this.roleActionService.save(roleId, actionIdList));
            }
        } catch (Exception e) {
            e.printStackTrace();
            rr.setSuccess(false);
        }
        return rr;
    }
}
