package com.asche.wetalk.service.impl;

import com.asche.wetalk.entity.Like;
import com.asche.wetalk.entity.Topicreply;
import com.asche.wetalk.entity.TopicreplyExample;
import com.asche.wetalk.mapper.LikeMapper;
import com.asche.wetalk.mapper.TopicreplyMapper;
import com.asche.wetalk.service.TopicReplyService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TopicReplyServiceImpl implements TopicReplyService {

    @Autowired
    private TopicreplyMapper topicreplyMapper;

    @Autowired
    private LikeMapper likeMapper;

    @Override
    public void publish(Topicreply topicreply) {
        topicreplyMapper.insert(topicreply);
    }

    @Override
    public Topicreply findById(Integer id) {
        return topicreplyMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Topicreply> findByTopic(Integer id) {
        TopicreplyExample topicreplyExample = new TopicreplyExample();
        TopicreplyExample.Criteria criteria = topicreplyExample.createCriteria();
        criteria.andTopicidEqualTo(id);
        List<Topicreply> topicreplies = topicreplyMapper.selectByExample(topicreplyExample);
        return topicreplies;
    }

    @Override
    public List<Topicreply> findByAuthor(Integer authorId) {
        TopicreplyExample topicreplyExample = new TopicreplyExample();
        TopicreplyExample.Criteria criteria = topicreplyExample.createCriteria();
        criteria.andAuthoridEqualTo(authorId);
        List<Topicreply> topicreplies = topicreplyMapper.selectByExample(topicreplyExample);
        return topicreplies;
    }

    @Override
    public List<Topicreply> getAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Topicreply> articles = topicreplyMapper.selectByExample(new TopicreplyExample());
        return articles;
    }

    @Override
    public void deleteById(Integer id) {
        topicreplyMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Topicreply topicreply) {
        topicreplyMapper.updateByPrimaryKey(topicreply);
    }

    @Override
    public int like(Like like) {
        try {
            likeMapper.insert(like);
            topicreplyMapper.like(like.getBodyId());
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
            topicreplyMapper.unLike(like.getBodyId());
        }
        return code;
    }
}
