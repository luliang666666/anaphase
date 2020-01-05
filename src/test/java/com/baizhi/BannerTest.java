package com.baizhi;

import com.baizhi.dao.BannerDao;
import com.baizhi.dao.ChapterDao;
import com.baizhi.entity.Banner;
import com.baizhi.entity.Chapter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest(classes = AnaphaseApplication.class)
@RunWith(SpringRunner.class)
public class BannerTest {
    @Autowired
    private BannerDao bannerDao;
    @Test
    public void test(){
        Banner banner = new Banner("1","aa","dd",new Date(),"激活","");
        bannerDao.insert(banner);
        System.out.println("添加成功");
    }
    @Test
    public void test1(){
        Banner banner = new Banner();
        banner.setTitle("bb");
        banner.setImg("cc");
        banner.setCreate_date(new Date());
        banner.setStatus("激活");
        banner.setOther("");

        System.out.println("修改成功");
    }
    @Test
    public void test2(){
        List<Banner> list = bannerDao.queryPage(1, 2);
        for (Banner banner : list) {
            System.out.println(banner);
        }
    }
    @Autowired
    private ChapterDao chapterDao;
    @Test
    public void test11(){
        List<Chapter> chapters = chapterDao.queryById("1");
        System.out.println(chapters);
    }
}
