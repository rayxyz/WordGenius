package com.wordgen.service;

import java.util.List;
import java.util.Map;

import com.wordgen.appobj.RoleAO;
import com.wordgen.util.ReqResult;

public interface RoleService {
    public List<RoleAO> getAll();
    public int countAll();
    public RoleAO getById(String roleId);
    public List<RoleAO> getList(Map<String, Object> queryMap);
    public int countList(Map<String, Object> queryMap);
    public boolean save(RoleAO role);
    public ReqResult<Boolean> update(RoleAO ra);
    public ReqResult<Boolean> delete(String roleId);
}
