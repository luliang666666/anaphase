package com.baizhi.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {
    //依赖于AdminServer
    /*@Autowired
    private AdminService adminService;
*/
    @ResponseBody
    @RequestMapping("login")
    public Map<String, Object> login(String username, String password, String enCode, HttpSession session) {
        //通过session作用域获取验证码
        String code = (String) session.getAttribute("code");
        HashMap<String, Object> map = new HashMap<>();
        //判断验证码是否一致
        if (code.equals(enCode)) {
            Subject subject = SecurityUtils.getSubject();
            AuthenticationToken authenticationToken = new UsernamePasswordToken(username, password);
            //调用业务
            //Admin admin = adminService.queryByUsername(username);
            try {
                subject.login(authenticationToken);
                map.put("msg", "ok");
            } catch (UnknownAccountException e) {
                map.put("msg", "账号有误");

            } catch (IncorrectCredentialsException e) {
                map.put("msg", "密码有误");
            } finally {
                return map;
            }
        } else {
            map.put("msg", "验证码有误");
            return map;
        }
    }

    @RequestMapping("logOut")
    private void logOut() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }
}
