package com.asche.wetalk.service;

import com.asche.wetalk.entity.Happen;
import com.asche.wetalk.entity.Like;
import com.asche.wetalk.entity.Topicreply;

import java.util.List;

public interface HappenService {

    void publish(Happen topicreply);

    Happen findById(Integer id);

    List<Happen> findByAuthor(Integer authorId);

    List<Happen> getAll(Integer pageNum, Integer pageSize);

    void deleteById(Integer id);

    void update(Happen happen);

    int like(Like like);

    int unLike(Like like);
}
