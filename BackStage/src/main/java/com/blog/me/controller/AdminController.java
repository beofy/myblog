package com.blog.me.controller;

import com.blog.me.common.Message;
import com.blog.me.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller()
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping(value = "/login")
    @ResponseBody
    public Message loggin(@RequestParam("account") String account,@RequestParam("password") String password){
        return adminService.login(account,password);
    }
}
