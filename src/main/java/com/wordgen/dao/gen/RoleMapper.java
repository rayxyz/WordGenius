package com.wordgen.dao.gen;

import com.wordgen.appobj.RoleAO;
import com.wordgen.dao.BaseMapper;
import com.wordgen.model.gen.Role;
import com.wordgen.model.gen.RoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleMapper extends BaseMapper<RoleAO, String, RoleExample> {
}