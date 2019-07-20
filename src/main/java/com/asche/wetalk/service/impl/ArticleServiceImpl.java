package com.asche.wetalk.service.impl;

import com.asche.wetalk.common.CommonResult;
import com.asche.wetalk.entity.Article;
import com.asche.wetalk.entity.ArticleExample;
import com.asche.wetalk.entity.Like;
import com.asche.wetalk.mapper.ArticleMapper;
import com.asche.wetalk.mapper.LikeMapper;
import com.asche.wetalk.service.ArticleService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.asche.wetalk.util.PrintUtils.println;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private LikeMapper likeMapper;

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
    public List<Article> getAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Article> articles = articleMapper.selectByExample(new ArticleExample());
        return articles;
    }

    @Override
    public void deleteById(Integer id) {
        articleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Article article) {
        articleMapper.updateByPrimaryKey(article);
    }

    @Override
    public int like(Like like) {
        try {
            likeMapper.insert(like);
            articleMapper.like(like.getBodyId());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    @Override
    public int unLike(Like like) {
        int code = likeMapper.delete(like);
        if (code == 1){
            articleMapper.unLike(like.getBodyId());
        }
        return code;
    }
}
