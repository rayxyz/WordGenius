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

import com.wordgen.appobj.UserAO;
import com.wordgen.dict.Resources;
import com.wordgen.service.RoleService;
import com.wordgen.service.UserService;
import com.wordgen.util.CommonUtil;
import com.wordgen.util.Pager;
import com.wordgen.util.ReqResult;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private CommonUtil commonUtil;
    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;
    
    @RequestMapping("/index")
    public String index(Model model) {
        return Resources.Page.USER_MGMT;
    }
    
//    @RequestMapping("/getList")
//    public String getList(Model model, @RequestBody Map<String, Object> queryMap) {
////        UserAO userAO = new ObjectMapper().convertValue(queryMap, UserAO.class);
//        List<UserAO> list = this.userService.getList(queryMap);
//        model.addAttribute("userList", list);
//        return Resources.Page.USER_LIST;
//    }
    
    @RequestMapping(value="/getList", method=RequestMethod.GET)
    public String getList(Model model, String queryParams, String pagerParams) {
        Map<String, Object> queryMap = this.commonUtil.parse2Map(queryParams);
//        Pager pager = (Pager) JSONObject.toBean(JSONObject.fromObject(pagerParams), Pager.class);
//        Pager pager = new ObjectMapper().convertValue(this.commonUtil.parse2Map(pagerParams), Pager.class);
        Pager pager = (Pager) this.commonUtil.string2Bean(pagerParams, Pager.class);
        pager.setRows(this.userService.coutList(queryMap));
        pager.setPages(8);
        List<UserAO> list = this.userService.getList(queryMap);
        model.addAttribute("userList", list);
        model.addAttribute("pager", pager);
        return Resources.Page.USER_LIST;
    }
    
    @RequestMapping("/add_dlg")
    public String add_dlg(Model model) {
        model.addAttribute("roleList", this.roleService.getAll());
        return Resources.Page.USER_ADD_DLG;
    }
    
    @RequestMapping("/update_dlg/{uid}")
    public String update_dlg(Model model, @PathVariable("uid") String uid) {
        model.addAttribute("user", this.userService.getById(uid));
        model.addAttribute("roleList", this.roleService.getAll());
        return Resources.Page.USER_UPDATE_DLG;
    }
    
    @RequestMapping(value="/save", method={RequestMethod.POST})
    @ResponseBody
    public Object save(String params) {
        ReqResult<Boolean> ret = new ReqResult<>();
        try {
            UserAO ua = (UserAO) this.commonUtil.string2Bean(params, UserAO.class);
            ret = this.userService.save(ua);
        } catch (Exception e) {
            e.printStackTrace();
            ret.setData(false);
            ret.setMsg("保存失败！");
        }
        return ret;
    }
    
    @RequestMapping(value="/update", method={RequestMethod.POST})
    @ResponseBody
    public Object update(String params) {
        ReqResult<Boolean> ret = new ReqResult<>();
        try {
            UserAO ua = (UserAO) this.commonUtil.string2Bean(params, UserAO.class);
            ret = this.userService.update(ua);
        } catch (Exception e) {
            e.printStackTrace();
            ret.setData(false);
            ret.setMsg("保存失败！");
        }
        return ret;
    }
    
    @RequestMapping(value="delete/{uid}", method={RequestMethod.POST})
    @ResponseBody
    public Object delete(@PathVariable("uid") String uid) {
        ReqResult<Boolean> ret = new ReqResult<>();
        try {
            ret = this.userService.delete(uid);
        } catch (Exception e) {
            ret.setSuccess(false);
            ret.setMsg("删除失败！");
        }
        return ret;
    }
}
