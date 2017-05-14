package com.wordgen.service;

import java.util.List;
import java.util.Map;

import com.wordgen.appobj.ModuleAO;
import com.wordgen.util.ReqResult;

public interface ModuleService {
    public ModuleAO getById(String id);
    public List<ModuleAO> getList(Map<String, Object> queryMap);
    public int countList(Map<String, Object> queryMap);
    public boolean save(ModuleAO ma);
    public ReqResult<Boolean> update(ModuleAO module);
    public ReqResult<Boolean> delete(String moduleId);
}
