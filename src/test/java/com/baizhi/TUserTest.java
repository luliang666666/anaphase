package com.baizhi;

import com.baizhi.dao.ArticleDao;
import com.baizhi.dao.TUserDao;
import com.baizhi.entity.Article;
import com.baizhi.entity.MapDto;
import com.baizhi.entity.TUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest(classes = AnaphaseApplication.class)
@RunWith(SpringRunner.class)
public class TUserTest {
    /*@Autowired
    private AlbumDao albumDao;
    @Test
    public void test(){
        List<Album> albums = albumDao.queryPage(0, 1);
        for (Album album : albums) {
            System.out.println(album);
        }
    }
    @Autowired
    private ChapterDao chapterDao;
    @Test
    public void test1(){
        List<Chapter> list = chapterDao.queryByPage(0, 2,"1");
        for (Chapter chapter : list) {
            System.out.println(chapter);
        }
    }*/
    /*@Test
    public void test2(){
        List<Chapter> list = chapterDao.queryById("1");
        for (Chapter chapter : list) {
            System.out.println(chapter);
        }
    }*/
    @Autowired
    private ArticleDao articleDao;
    @Test
    public void test3(){
        List<Article> list = articleDao.queryByPage(0, 2);
        for (Article dao : list) {
            System.out.println(dao);
        }
    }
    @Test
    public void test4(){
        Article article = new Article("1","sf","ag","fas","1",new Date(),"","");
        articleDao.insertArticle(article);
        System.out.println("添加成功");
    }
    @Test
    public void test5(){
        Article article = new Article();
        article.setId("1");
        article.setAuthor("dasdf");
        article.setContent("");
        article.setCreateDate(new Date());
        article.setGuruId("1");
        article.setOther("");
        article.setStatus("激活");
        article.setTitle("adsdf");
        articleDao.updateArticle(article);
        System.out.println("修改成功");
    }
    @Test
    public void test6(){
        Article article = articleDao.selectById("2");
        System.out.println(article);
    }
    @Autowired
    private TUserDao tUserDao;
    @Test
    public void test7(){
        List<MapDto> mapDtos = tUserDao.queryAll();
        for (MapDto mapDto : mapDtos) {
            System.out.println(mapDto);
        }
    }
    @Test
    public void test8(){
        List<MapDto> mapDtos = tUserDao.queryMonth();
        for (MapDto mapDto : mapDtos) {
            System.out.println(mapDto);
        }
    }
    @Test
    public void test9(){
        Integer integer = tUserDao.tuserDay(7);
        System.out.println(integer);
    }
    /*@Test
    public void test10(){
        GoEasy goEasy = new GoEasy("http(s)://<REST Host>”, "BC-ec77675926e44819a05bd4b01b02aa30");
    }*/
    @Test
    public void test10(){
        TUser tUser = tUserDao.selectByPhoneNumber("36985214712");
        System.out.println(tUser);
    }
}
