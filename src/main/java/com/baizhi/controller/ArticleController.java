package com.baizhi.controller;

import com.baizhi.entity.Article;
import com.baizhi.server.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {
    //依赖于ArticleService
    @Autowired
    private ArticleService articleService;
    @RequestMapping("queryByPage")
    public Map<String,Object> queryByPage(Integer page, Integer rows){
        Map<String, Object> map = articleService.queryByPage(page, rows);
        return map;
    }
    @RequestMapping("deleteById")
    public void deleteById(String[] id , String oper){
        if ("del".equals(oper)){
            articleService.deleteById(id);
        }
    }
    @RequestMapping("insertArticle")
    public void insertArticle(Article article){
        articleService.insertArticle(article);
    }
    @RequestMapping("updateArticle")
    public void updateArticle(Article article){
        articleService.updateArticle(article);
    }
    @RequestMapping("selectById")
    public Article selectById(String id){
        Article selectById = articleService.selectById(id);
        return selectById;
    }
}
