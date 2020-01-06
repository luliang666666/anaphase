package com.baizhi;

import com.baizhi.dao.AdminDao;
import com.baizhi.realm.MyRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = AnaphaseApplication.class)
@RunWith(SpringRunner.class)
public class AnaphaseApplicationTests {
    @Autowired
    private AdminDao adminDao;

    /* @Test
    public void contextLoads() {
         List<Admin> admins = adminDao.queryAll();
         for (Admin admin : admins) {
             System.out.println(admin);
         }
     }
     @Test
     public void test(){
         Admin admin = adminDao.queryByUsername("饕鬄");
         System.out.println(admin);
     }*/
    @Test
    public void test() {
        MyRealm myRealm = new MyRealm();
        SecurityManager securityManager = new DefaultSecurityManager(myRealm);
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("1111", "123456");
        try {
            subject.login(usernamePasswordToken);
        } catch (UnknownAccountException e) {
            System.out.println("账号有误");
        } catch (IncorrectCredentialsException e) {
            System.out.println("密码有误");
        }
    }

}
