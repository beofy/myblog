package com.blog.me.service;

import com.blog.me.common.Message;
import com.blog.me.model.entity.t_admin;

public interface AdminService {
    Message<t_admin> login(String account , String password);
}
