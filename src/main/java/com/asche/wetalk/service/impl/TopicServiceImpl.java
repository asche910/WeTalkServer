package com.asche.wetalk.service.impl;

import com.asche.wetalk.entity.*;
import com.asche.wetalk.mapper.LikeMapper;
import com.asche.wetalk.mapper.TopicMapper;
import com.asche.wetalk.service.TopicService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicMapper topicMapper;

    @Autowired
    private LikeMapper likeMapper;

    @Override
    public void publish(Topic article) {
        topicMapper.insert(article);
    }

    @Override
    public Topic findById(Integer id) {
        return topicMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Topic> findByTitle(String title) {
        TopicExample topicExample = new TopicExample();
        TopicExample.Criteria criteria = topicExample.createCriteria();
        criteria.andTitleLike(title);
        List<Topic> topics = topicMapper.selectByExample(topicExample);
        return topics;
    }

    @Override
    public List<Topic> findByAuthor(Integer authorId) {
        TopicExample topicExample = new TopicExample();
        TopicExample.Criteria criteria = topicExample.createCriteria();
        criteria.andAuthoridEqualTo(authorId);
        List<Topic> topics = topicMapper.selectByExample(topicExample);
        return topics;
    }

    @Override
    public List<Topic> getAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Topic> topics = topicMapper.selectByExample(new TopicExample());
        return topics;
    }

    @Override
    public void deleteById(Integer id) {
        topicMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Topic article) {
        topicMapper.updateByPrimaryKey(article);
    }

    @Override
    public int like(Like like) {
        try {
            likeMapper.insert(like);
            topicMapper.like(like.getBodyId());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    @Override
    public int unLike(Like like) {
        int code = likeMapper.delete(like);
        if (code == 1) {
            topicMapper.unLike(like.getBodyId());
        }
        return code;
    }
}
