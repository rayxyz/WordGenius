package com.wordgen.dao.gen;

import com.wordgen.appobj.ActionAO;
import com.wordgen.dao.BaseMapper;
import com.wordgen.model.gen.Action;
import com.wordgen.model.gen.ActionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActionMapper extends BaseMapper<ActionAO, String, ActionExample> {
}