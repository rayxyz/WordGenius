package com.wordgen.service;

import java.util.List;
import java.util.Map;

import com.wordgen.appobj.UserAO;
import com.wordgen.util.ReqResult;

public interface UserService {
    public UserAO getById(String id);
    public List<UserAO> getList(Map<String, Object> queryMap);
    public int coutList(Map<String, Object> queryMap);
    public ReqResult<Boolean> save(UserAO ua);
    public ReqResult<Boolean> update(UserAO ua);
    public ReqResult<Boolean> delete(String uid);
    public ReqResult<UserAO> login(UserAO user);
}
