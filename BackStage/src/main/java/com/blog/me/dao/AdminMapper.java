package com.blog.me.dao;

import com.blog.me.model.entity.t_admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMapper {
    List<t_admin> findByAccountAndPassword(@Param("account") String account , @Param("password") String password);
}
