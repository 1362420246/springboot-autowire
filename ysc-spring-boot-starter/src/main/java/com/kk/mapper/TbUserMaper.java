package com.kk.mapper;

import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TbUserMaper {

    @Select("SELECT user_name FROM tab_user")
    List<String> getList();
}
