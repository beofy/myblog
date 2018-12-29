package com.blog.me.service.Impl;

import com.blog.me.common.Message;
import com.blog.me.dao.AdminMapper;
import com.blog.me.model.entity.t_admin;
import com.blog.me.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminDao;

    public Message<t_admin> login(String account , String password) {
        System.out.println(adminDao.getClass().getSimpleName());
        List<t_admin> adminList = adminDao.findByAccountAndPassword(account,password);
        if(adminList==null||adminList.isEmpty()){
            return Message.error();
        }
        return Message.ok(adminList.get(0));
    }
}
