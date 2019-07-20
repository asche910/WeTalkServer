package com.asche.wetalk.service;

import com.asche.wetalk.entity.Like;
import com.asche.wetalk.entity.Requirement;

import java.util.List;

public interface RequirementService {

    void publish(Requirement requirement);

    Requirement findById(Integer id);

    List<Requirement> findByTitle(String title);

    List<Requirement> findByAuthor(Integer authorId);

    List<Requirement> getAll(Integer pageNum, Integer pageSize);

    void deleteById(Integer id);

    void update(Requirement requirement);

    int like(Like like);

    int unLike(Like like);
}
