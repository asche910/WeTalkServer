package com.asche.wetalk.service.impl;

import com.asche.wetalk.entity.Article;
import com.asche.wetalk.entity.ArticleExample;
import com.asche.wetalk.mapper.ArticleMapper;
import com.asche.wetalk.service.ArticleService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void publish(Article article) {
        articleMapper.insert(article);
    }

    @Override
    public Article findById(Integer id) {
        return articleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Article> findByTitle(String title) {
        ArticleExample articleExample = new ArticleExample();
        ArticleExample.Criteria criteria = articleExample.createCriteria();
        criteria.andTitleLike(title);
        List<Article> articles = articleMapper.selectByExample(articleExample);
        return articles;
    }

    @Override
    public List<Article> findByAuthor(Integer authorId) {
        ArticleExample articleExample = new ArticleExample();
        ArticleExample.Criteria criteria = articleExample.createCriteria();
        criteria.andAuthoridEqualTo(authorId);
        List<Article> articles = articleMapper.selectByExample(articleExample);
        return articles;
    }

    @Override
    public List<Article> getAllArticle(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Article> articles = articleMapper.selectByExample(new ArticleExample());
        return articles;
    }

    @Override
    public void deleteById(Integer id) {
        articleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateArticle(Article article) {
        articleMapper.updateByPrimaryKey(article);
    }
}
