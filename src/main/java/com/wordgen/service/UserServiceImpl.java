package com.wordgen.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.wordgen.appobj.RoleAO;
import com.wordgen.appobj.UserAO;
import com.wordgen.dao.custom.UserMapperCustom;
import com.wordgen.dao.gen.RoleMapper;
import com.wordgen.dao.gen.UserMapper;
import com.wordgen.model.gen.RoleExample;
import com.wordgen.model.gen.UserExample;
import com.wordgen.util.ReqResult;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapperCustom userMapperCustom;
    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleMapper roleMapper;

    @Override
    public UserAO getById(String id) {
	if (StringUtils.isBlank(id)) {
	    throw new RuntimeException("用户id为空！");
	}
	UserExample example = new UserExample();
	example.createCriteria().andIdEqualTo(id).andValidEqualTo(true);
	List<UserAO> userList = this.userMapper.selectByExample(example);
	if (userList.isEmpty()) {
	    throw new RuntimeException("用户不存在或已被删除！");
	}
	UserAO user = userList.get(0);
	if (!StringUtils.isBlank(user.getRoleId())) {
	    RoleExample re = new RoleExample();
	    re.createCriteria().andIdEqualTo(user.getRoleId());
	    List<RoleAO> roleList = this.roleMapper.selectByExample(re);
	    if (!roleList.isEmpty()) {
		user.setRoleName(roleList.get(0).getName());
	    }
	}
	return user;
    }

    @Override
    public List<UserAO> getList(Map<String, Object> queryMap) {
	return this.userMapperCustom.selectList(queryMap);
    }

    @Override
    public int coutList(Map<String, Object> queryMap) {
	return this.userMapperCustom.countList(queryMap);
    }

    @Override
    public ReqResult<Boolean> save(UserAO ua) {
	ReqResult<Boolean> ret = new ReqResult<>();
	if (ua != null) {
	    UserExample example = new UserExample();
	    example.createCriteria().andEmailEqualTo(ua.getEmail()).andValidEqualTo(true);
	    List<UserAO> userList = this.userMapper.selectByExample(example);
	    if (!userList.isEmpty()) {
		ret.setSuccess(false);
		ret.setData(false);
		ret.setMsg("保存失败！邮箱账号已注册过，请使用其他邮箱账号！");
		return ret;
	    }
	    if (StringUtils.isBlank(ua.getPassword())) {
		ua.setPassword("123456");
	    }
	    ua.setCreateTime(new Date());
	    ua.setValid(true);
	    if (this.userMapper.insert(ua) < 0) {
		ret.setSuccess(false);
		ret.setMsg("保存失败！");
		return ret;
	    } else {
		ret.setSuccess(true);
	    }
	} else {
	    ret.setSuccess(false);
	    ret.setMsg("保存失败！");
	}
	return ret;
    }

    @Override
    public ReqResult<Boolean> update(UserAO ua) {
	ReqResult<Boolean> ret = new ReqResult<>();
	if (ua != null) {
	    UserExample example = new UserExample();
	    example.createCriteria().andEmailEqualTo(ua.getEmail()).andValidEqualTo(true);
	    List<UserAO> userList = this.userMapper.selectByExample(example);
	    if (!userList.isEmpty()) {
		for (UserAO user : userList) {
		    if (!user.getId().equals(ua.getId())) {
			ret.setSuccess(false);
			ret.setData(false);
			ret.setMsg("保存失败！邮箱账号已注册过，请使用其他邮箱账号！");
			return ret;
		    }
		}
	    } else {
		ret.setSuccess(false);
		ret.setMsg("保存失败！用户不存在或已被删除！");
		return ret;
	    }
	    ua.setUpdateTime(new Date());
	    if (this.userMapper.updateByPrimaryKeySelective(ua) < 0) {
		ret.setSuccess(false);
		ret.setMsg("保存失败！");
		return ret;
	    } else {
		ret.setSuccess(true);
	    }
	} else {
	    ret.setSuccess(false);
	    ret.setMsg("保存失败！");
	}
	return ret;
    }

    @Override
    public ReqResult<Boolean> delete(String uid) {
	ReqResult<Boolean> ret = new ReqResult<>();
	if (StringUtils.isBlank(uid)) {
	    ret.setSuccess(false);
	    ret.setMsg("删除失败！");
	    return ret;
	}
	UserExample example = new UserExample();
	example.createCriteria().andIdEqualTo(uid).andValidEqualTo(true);
	List<UserAO> userList = this.userMapper.selectByExample(example);
	if (userList.isEmpty()) {
	    ret.setSuccess(false);
	    ret.setMsg("用户不存在，删除失败！");
	    return ret;
	}
	UserAO user = userList.get(0);
	user.setUpdateTime(new Date());
	user.setValid(false);
	if (this.userMapper.updateByPrimaryKeySelective(user) < 0) {
	    ret.setSuccess(false);
	    ret.setMsg("删除失败！");
	    return ret;
	}
	ret.setSuccess(true);
	return ret;
    }

    @Override
    public ReqResult<UserAO> login(UserAO user) {
	ReqResult<UserAO> ret = new ReqResult<UserAO>();
	if (user == null) {
	    ret.setSuccess(false);
	    ret.setMsg("登录失败！");
	    return ret;
	}
	UserExample ue = new UserExample();
	ue.createCriteria().andEmailEqualTo(user.getEmail()).andPasswordEqualTo(user.getPassword());
	List<UserAO> userList = this.userMapper.selectByExample(ue);
	if (userList.isEmpty()) {
	    ret.setSuccess(false);
	    ret.setMsg("用户名或密码错误，登录失败！");
	    return ret;
	}
	ret.setData(userList.get(0));
	ret.setSuccess(true);
	return ret;
    }

}
