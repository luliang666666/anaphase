package com.baizhi;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = AnaphaseApplication.class)
@RunWith(SpringRunner.class)
public class AnaphaseApplicationTests {
    @Autowired
    private AdminDao adminDao;
    @Test
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
    }

}
