package com.baizhi;

import com.baizhi.realm.MyRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md2Hash;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@SpringBootTest(classes = AnaphaseApplication.class)
@RunWith(SpringRunner.class)
public class AnaphaseApplicationTests {
    /*@Autowired
    private AdminDao adminDao;*/

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
        } finally {
            boolean authenticated = subject.isAuthenticated();
            if (authenticated) {
                //判断是否有该角色
                boolean superAdmin = subject.hasRole("superAdmin");
                ArrayList<String> list = new ArrayList<>();
                list.add("admin");
                list.add("superAdmin");
                //判断是否有所有角色
                boolean b = subject.hasAllRoles(list);
                //判断是否有某一角色
                boolean[] booleans = subject.hasRoles(list);
                //判断是否有该权限
                boolean permitted = subject.isPermitted("admin:add");
                //判断是否有某一个权限
                boolean[] permitted1 = subject.isPermitted("admin:delete", "admin:add", "admin:update");
                //判断是否有所有权限
                boolean permittedAll = subject.isPermittedAll("admin:add", "admin:delete", "admin:update");

            }
        }
    }


    @Test
    public void test1() {
        Md5Hash md5Hash = new Md5Hash("123456", "asd", 1024);
        //Md5加密   e10adc3949ba59abbe56e057f20f883e
        //Md5加密+盐值  1e55dbf412cb74d5e2c21fb6452408c7
        //Md5加密+盐值+散列值  fe2b6fed3111c59eebf448989e287acd
        System.out.println(md5Hash);
    }

    @Test
    public void test2() {
        Md2Hash md2Hash = new Md2Hash("123456");
        //md2:d4541250b586296fcce5dea4463ae17f
        System.out.println(md2Hash);
    }
}
