package com.asche.wetalk.service;

import com.asche.wetalk.entity.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserService {

    void addUser(User user);

    User findById(Integer id);

    User findByUserName(String username);

    List<User> findLikeUserName(String username);

    List<User> getAllUser(Integer pageNum, Integer pageSize);

    void updateUser(User user);

    void deleteById(Integer id);

}
