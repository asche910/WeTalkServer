package com.asche.wetalk.service.impl;

import com.asche.wetalk.entity.Happen;
import com.asche.wetalk.entity.HappenExample;
import com.asche.wetalk.entity.Like;
import com.asche.wetalk.mapper.HappenMapper;
import com.asche.wetalk.mapper.LikeMapper;
import com.asche.wetalk.service.HappenService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class HappenServiceImpl implements HappenService {

    @Autowired
    private HappenMapper happenMapper;

    @Autowired
    private LikeMapper likeMapper;

    @Override
    public void publish(Happen topicreply) {
        happenMapper.insert(topicreply);
    }

    @Override
    public Happen findById(Integer id) {
        return happenMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Happen> findByAuthor(Integer authorId) {
        HappenExample happenExample = new HappenExample();
        HappenExample.Criteria criteria = happenExample.createCriteria();
        criteria.andAuthoridEqualTo(authorId);
        List<Happen> happens = happenMapper.selectByExample(happenExample);
        return happens;
    }

    @Override
    public List<Happen> getAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Happen> happens = happenMapper.selectByExample(new HappenExample());
        return happens;
    }

    @Override
    public void deleteById(Integer id) {
        happenMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Happen topicreply) {
        happenMapper.updateByPrimaryKey(topicreply);
    }

    @Override
    public int like(Like like) {
        try {
            likeMapper.insert(like);
            happenMapper.like(like.getBodyId());
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
            happenMapper.unLike(like.getBodyId());
        }
        return code;
    }
}
