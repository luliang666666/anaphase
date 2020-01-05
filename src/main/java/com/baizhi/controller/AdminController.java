package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.server.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {
    //依赖于AdminServer
    @Autowired
    private AdminService adminService;

    @ResponseBody
    @RequestMapping("login")
    public String login(String username, String password, String enCode, HttpSession session){
        //通过session作用域获取验证码
        String code = (String) session.getAttribute("code");
        //判断验证码是否一致
        if (!code.equals(enCode)){
            return "验证码不正确,请重新输入！";
        }else {
            //调用业务
            Admin admin = adminService.queryByUsername(username);
            //判断用户是否为空
            if (admin != null){
                //判断密码是否一致
                if (admin.getPassword().equals(password)){
                    return "ok";
                }else {
                    return "密码错误";
                }
            }else {
                return "密码或用户名不能为空";
            }
        }
    }

}
