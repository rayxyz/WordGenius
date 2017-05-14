package com.wordgen.service;

import java.util.List;

public interface RoleActionService {
    public boolean save(String roleId, List<String> actionIdList);
}
