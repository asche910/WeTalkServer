package com.asche.wetalk.service;

import com.asche.wetalk.entity.Like;
import com.asche.wetalk.entity.Topic;

import java.util.List;

public interface TopicService {

    void publish(Topic topic);

    Topic findById(Integer id);

    List<Topic> findByTitle(String title);

    List<Topic> findByAuthor(Integer authorId);

    List<Topic> getAll(Integer pageNum, Integer pageSize);

    void deleteById(Integer id);

    void update(Topic topic);

    /**
     * 等同于关注
     * @param like
     * @return
     */
    int like(Like like);

    int unLike(Like like);
}
