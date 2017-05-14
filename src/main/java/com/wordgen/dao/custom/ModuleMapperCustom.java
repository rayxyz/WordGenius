package com.wordgen.dao.custom;

import java.util.List;
import java.util.Map;

import com.wordgen.appobj.ModuleAO;

public interface ModuleMapperCustom {
    public List<ModuleAO> selectList(Map<String, Object> queryMap);
    public int countList(Map<String, Object> queryMap);
}
