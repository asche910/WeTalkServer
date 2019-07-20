package com.asche.wetalk.service.impl;

import com.asche.wetalk.entity.*;
import com.asche.wetalk.mapper.CommentMapper;
import com.asche.wetalk.mapper.LikeMapper;
import com.asche.wetalk.service.ArticleService;
import com.asche.wetalk.service.CommentService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private LikeMapper likeMapper;

    @Override
    public void publish(Comment article) {
        commentMapper.insert(article);
    }

    @Override
    public Comment findById(Integer id) {
        return commentMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Comment> findByBodyId(Integer bodyId, Integer type) {
        CommentExample commentExample = new CommentExample();
        CommentExample.Criteria criteria = commentExample.createCriteria();
        criteria.andBodyidEqualTo(bodyId);
        criteria.andTypeEqualTo(type);
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        return comments;
    }

    @Override
    public List<Comment> findByAuthor(Integer authorId) {
        CommentExample commentExample = new CommentExample();
        CommentExample.Criteria criteria = commentExample.createCriteria();
        criteria.andAuthoridEqualTo(authorId);
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        return comments;
    }

    @Override
    public List<Comment> getAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Comment> comments = commentMapper.selectByExample(new CommentExample());
        return comments;
    }

    @Override
    public void deleteById(Integer id) {
        commentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Comment article) {
        commentMapper.updateByPrimaryKey(article);
    }

    @Override
    public int like(Like like) {
        try {
            likeMapper.insert(like);
            commentMapper.like(like.getBodyId());
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
            commentMapper.unLike(like.getBodyId());
        }
        return code;
    }
}
