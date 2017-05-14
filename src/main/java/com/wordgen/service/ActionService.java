package com.wordgen.service;

import java.util.List;
import java.util.Map;

import com.wordgen.appobj.ActionAO;
import com.wordgen.util.ReqResult;

public interface ActionService {
    public ActionAO getById(String id);
    public List<ActionAO> getList(Map<String, Object> queryMap);
    public int countList(Map<String, Object> queryMap);
    public List<ActionAO> getListAuthed(Map<String, Object> queryMap);
    public Map<String, Object> getListMixedWithAuthed(Map<String, Object> queryMap);
    public boolean save(ActionAO action);
    public ReqResult<Boolean> update(ActionAO action);
    public ReqResult<Boolean> delete(String actionId);
}
