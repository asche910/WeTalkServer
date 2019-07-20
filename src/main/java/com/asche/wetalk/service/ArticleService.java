package com.asche.wetalk.service;

import com.asche.wetalk.entity.Article;

import java.util.List;

public interface ArticleService {

    void publish(Article article);

    Article findById(Integer id);

    List<Article> findByTitle(String title);

    List<Article> findByAuthor(Integer authorId);

    List<Article> getAllArticle(Integer pageNum, Integer pageSize);

    void deleteById(Integer id);

    void updateArticle(Article article);
}
