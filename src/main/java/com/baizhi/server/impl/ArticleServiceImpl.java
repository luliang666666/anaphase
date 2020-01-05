package com.baizhi.server.impl;

import com.baizhi.dao.ArticleDao;
import com.baizhi.entity.Article;
import com.baizhi.entity.Chapter;
import com.baizhi.server.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    //依赖于ArticleDao
    @Autowired
    private ArticleDao articleDao;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> queryByPage(Integer page, Integer rows) {
        HashMap<String, Object> map = new HashMap<>();
        Integer start = (page-1)*rows;
        List<Article> list = articleDao.queryByPage(start, rows);
        /*
         *  jqgrid 返回  page  rows  records total
         * */
        //records  总条数
        Integer records = articleDao.queryCount();
        //total 总页数
        Integer total = records%rows==0 ? records/rows:records/rows+1;
        map.put("page",page);
        map.put("rows",list);
        map.put("records",records);
        map.put("total",total);
        return map;
    }

    @Override
    public void deleteById(String[] id) {
        articleDao.deleteById(id);
    }

    @Override
    public void insertArticle(Article article) {
        article.setId(UUID.randomUUID().toString());
        article.setGuruId("1");
        article.setOther("");
        article.setCreateDate(new Date());
        articleDao.insertArticle(article);
    }

    @Override
    public void updateArticle(Article article) {
        articleDao.updateArticle(article);
    }

    @Override
    public Article selectById(String id) {
        Article article = articleDao.selectById(id);
        return article;
    }

    @Override
    public List<Article> queryAll() {
        List<Article> articles = articleDao.queryAll();
        return articles;
    }
}
