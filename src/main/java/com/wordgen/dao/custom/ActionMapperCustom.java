package com.wordgen.dao.custom;

import java.util.List;
import java.util.Map;

import com.wordgen.appobj.ActionAO;

public interface ActionMapperCustom {
    public List<ActionAO> selectList(Map<String, Object> queryMap);
    public List<ActionAO> selectListAuthed(Map<String, Object> queryMap);
    public int countList(Map<String, Object> queryMap);
}
