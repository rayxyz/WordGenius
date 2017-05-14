package com.wordgen.dao.gen;

import com.wordgen.appobj.RoleActionAO;
import com.wordgen.dao.BaseMapper;
import com.wordgen.model.gen.RoleAction;
import com.wordgen.model.gen.RoleActionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleActionMapper extends BaseMapper<RoleActionAO, String, RoleActionExample> {
}