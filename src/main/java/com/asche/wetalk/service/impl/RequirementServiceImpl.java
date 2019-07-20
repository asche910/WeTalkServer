package com.asche.wetalk.service.impl;

import com.asche.wetalk.entity.*;
import com.asche.wetalk.mapper.LikeMapper;
import com.asche.wetalk.mapper.RequirementMapper;
import com.asche.wetalk.service.RequirementService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequirementServiceImpl implements RequirementService {

    @Autowired
    private RequirementMapper requirementMapper;

    @Autowired
    private LikeMapper likeMapper;

    @Override
    public void publish(Requirement requirement) {
        requirementMapper.insert(requirement);
    }

    @Override
    public Requirement findById(Integer id) {
        return requirementMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Requirement> findByTitle(String title) {
        RequirementExample requirementExample = new RequirementExample();
        RequirementExample.Criteria criteria = requirementExample.createCriteria();
        criteria.andTitleLike(title);
        List<Requirement> requirements = requirementMapper.selectByExample(requirementExample);
        return requirements;
    }

    @Override
    public List<Requirement> findByAuthor(Integer authorId) {
        RequirementExample requirementExample = new RequirementExample();
        RequirementExample.Criteria criteria = requirementExample.createCriteria();
        criteria.andAuthoridEqualTo(authorId);
        List<Requirement> requirements = requirementMapper.selectByExample(requirementExample);
        return requirements;
    }

    @Override
    public List<Requirement> getAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Requirement> requirements = requirementMapper.selectByExample(new RequirementExample());
        return requirements;
    }

    @Override
    public void deleteById(Integer id) {
        requirementMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Requirement requirements) {
        requirementMapper.updateByPrimaryKey(requirements);
    }

    @Override
    public int like(Like like) {
        try {
            likeMapper.insert(like);
            requirementMapper.like(like.getBodyId());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    @Override
    public int unLike(Like like) {
        int code = likeMapper.delete(like);
        if (code == 1){
            requirementMapper.unLike(like.getBodyId());
        }
        return code;
    }
}
