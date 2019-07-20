package com.asche.wetalk.service;

import com.asche.wetalk.entity.Article;
import com.asche.wetalk.entity.Like;

import java.util.List;

public interface ArticleService {

    void publish(Article article);

    Article findById(Integer id);

    List<Article> findByTitle(String title);

    List<Article> findByAuthor(Integer authorId);

    List<Article> getAll(Integer pageNum, Integer pageSize);

    void deleteById(Integer id);

    void update(Article article);

    int like(Like like);

    int unLike(Like like);
}
