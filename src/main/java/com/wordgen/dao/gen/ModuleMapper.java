package com.wordgen.dao.gen;

import com.wordgen.appobj.ModuleAO;
import com.wordgen.dao.BaseMapper;
import com.wordgen.model.gen.Module;
import com.wordgen.model.gen.ModuleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ModuleMapper extends BaseMapper<ModuleAO, String, ModuleExample> {
}