package com.wordgen.dao.gen;

import com.wordgen.appobj.UserAO;
import com.wordgen.dao.BaseMapper;
import com.wordgen.model.gen.User;
import com.wordgen.model.gen.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<UserAO, String, UserExample> {
}