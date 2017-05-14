package com.wordgen.dao.custom;

import java.util.List;
import java.util.Map;

import com.wordgen.appobj.UserAO;

public interface UserMapperCustom {
    public List<UserAO> selectList(Map<String, Object> queryMap);
    public int countList(Map<String, Object> queryMap);
}
