package com.wordgen.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wordgen.appobj.ModuleAO;
import com.wordgen.appobj.UserAO;
import com.wordgen.dict.Resources;
import com.wordgen.service.ModuleService;
import com.wordgen.service.UserService;
import com.wordgen.util.CommonUtil;
import com.wordgen.util.ReqResult;

@Controller
@RequestMapping("/")
public class MainController {
    @Resource
    private ModuleService moduleService;

    @Resource
    private UserService userService;
    
    @Resource
    private CommonUtil commonUti;

    @RequestMapping("/sayHello")
    @ResponseBody
    public String sayHello() {
	return "Hello!";
    }

    @RequestMapping("")
    public String index(Model model) {
	model.addAttribute("msg", "Welcome!");
	return Resources.Page.INDEX;
    }

    @RequestMapping("/sysindex")
    public String sys_index(Model model) {
	model.addAttribute("msg", "I dont know if it works!");
	Map<String, Object> queryMap = new HashMap<String, Object>();
	queryMap.put("state", true);
	List<ModuleAO> moduleList = this.moduleService.getList(queryMap);
	model.addAttribute("moduleList", moduleList);
	return Resources.Page.SYS_INDEX;
    }

    @RequestMapping("/login-view")
    public String login_view(Model model) {
	return Resources.Page.LOGIN;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object login(HttpServletRequest request, String params) {
	ReqResult<UserAO> ret = new ReqResult<UserAO>();
	try {
	    HttpSession session = request.getSession(true);
	    UserAO user = (UserAO) this.commonUti.string2Bean(params, UserAO.class);
	    ret = this.userService.login(user);
	    if (ret.isSuccess()) {
		session.setAttribute("user", ret.getData());
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    ret.setSuccess(false);
	    ret.setMsg("登录异常！");
	}
	return ret;
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
	HttpSession session = request.getSession(false);
	if (session != null) {
	    session.setAttribute("user", null);
	    session.invalidate();
	}
	return Resources.Page.INDEX;
    }

    @RequestMapping("/signup-view")
    public String signup_view(Model model) {
	return Resources.Page.SIGNUP;

    }

    @RequestMapping(value="/signup", method={RequestMethod.POST})
    @ResponseBody
    public Object signup(String params) {
	ReqResult<Boolean> ret = new ReqResult<Boolean>();
	try {
	    UserAO ua = (UserAO) this.commonUti.string2Bean(params, UserAO.class);
	    ret = this.userService.save(ua);
	} catch (Exception e) {
	    e.printStackTrace();
	    ret.setSuccess(false);
	    ret.setMsg("保存出错！");
	}
	return ret;
    }

}
