package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.entity.Article;
import com.baizhi.entity.Banner;
import com.baizhi.entity.Chapter;
import com.baizhi.server.AlbumService;
import com.baizhi.server.ArticleService;
import com.baizhi.server.BannerService;
import com.baizhi.server.ChapterService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/first_page")
public class FrontController {
    @Resource
    private BannerService bannerService;
    @Resource
    private AlbumService albumService;
    @Resource
    private ArticleService articleService;
    @Resource
    private ChapterService chapterService;

    @ResponseBody
    @RequestMapping("/queryAll")
    public Map<String,Object> queryAll(String uid,String type){
        if ("1".equals(uid)){
            HashMap<String, Object> map = new HashMap<>();
            if ("all".equals(type)){
                List<Banner> banners = bannerService.query();
                map.put("header",banners);
                List<Album> albums = albumService.queryAll();
                map.put("album",albums);
                List<Article> articles = articleService.queryAll();
                map.put("article",articles);
            } else if ("wen".equals(type)){
                List<Album> albums = albumService.queryAll();
                map.put("album",albums);
            } else if ("si".equals(type)){
                List<Article> articles = articleService.queryAll();
                map.put("article",articles);
            }
            return map;
        } else {
            return null;
        }
    }

    @ResponseBody
    @RequestMapping("/wen")
    public Map<String,Object> queryWen(String id, String uid){
        if ("1".equals(uid)){
            HashMap<String, Object> map = new HashMap<>();
            Album album = albumService.queryById(id);
            map.put("introduction",album);
            List<Chapter> chapters = chapterService.queryByAlbum_id(album.getId());
            map.put("list",chapters);
            return map;
        } else {
            return null;
        }
    }
}

