package com.baizhi.dao;

import com.baizhi.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleDao {
    List<Article> queryByPage(@Param("start") Integer start, @Param("rows") Integer rows);
    Integer queryCount();
    void deleteById(String[] id);
    void insertArticle(Article article);
    void updateArticle(Article article);
    Article selectById(@Param("id") String id);
    List<Article> queryAll();
}
