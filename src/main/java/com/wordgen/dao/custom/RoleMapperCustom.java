package com.wordgen.dao.custom;

import java.util.List;
import java.util.Map;

import com.wordgen.appobj.RoleAO;

public interface RoleMapperCustom {
    public List<RoleAO> selectList(Map<String, Object> queryMap);
    public int countList(Map<String, Object> queryMap);
}
