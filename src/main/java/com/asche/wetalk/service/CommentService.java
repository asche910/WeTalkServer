package com.asche.wetalk.service;

import com.asche.wetalk.entity.Article;
import com.asche.wetalk.entity.Comment;
import com.asche.wetalk.entity.Like;

import java.util.List;

public interface CommentService {

    void publish(Comment comment);

    Comment findById(Integer id);

    List<Comment> findByBodyId(Integer bodyId, Integer type);

    List<Comment> findByAuthor(Integer authorId);

    List<Comment> getAll(Integer pageNum, Integer pageSize);

    void deleteById(Integer id);

    void update(Comment article);

    int like(Like like);

    int unLike(Like like);
}
