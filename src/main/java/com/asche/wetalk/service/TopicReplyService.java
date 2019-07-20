package com.asche.wetalk.service;

import com.asche.wetalk.entity.Like;
import com.asche.wetalk.entity.Requirement;
import com.asche.wetalk.entity.Topicreply;

import java.util.List;

public interface TopicReplyService {

    void publish(Topicreply topicreply);

    Topicreply findById(Integer id);

    List<Topicreply> findByTopic(Integer id);

    List<Topicreply> findByAuthor(Integer authorId);

    List<Topicreply> getAll(Integer pageNum, Integer pageSize);

    void deleteById(Integer id);

    void update(Topicreply topicreply);

    int like(Like like);

    int unLike(Like like);
}
