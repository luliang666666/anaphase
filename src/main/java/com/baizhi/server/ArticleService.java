package com.baizhi.server;

import com.baizhi.entity.Article;

import java.util.List;
import java.util.Map;

public interface ArticleService {
    Map<String,Object> queryByPage(Integer page, Integer rows);
    void deleteById(String[] id);
    void insertArticle(Article article);
    void updateArticle(Article article);
    Article selectById(String id);
    List<Article> queryAll();
}
