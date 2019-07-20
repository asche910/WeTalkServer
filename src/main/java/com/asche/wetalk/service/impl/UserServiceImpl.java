package com.asche.wetalk.service.impl;

import com.asche.wetalk.entity.User;
import com.asche.wetalk.entity.UserExample;
import com.asche.wetalk.mapper.UserMapper;
import com.asche.wetalk.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void addUser(User user) {
        userMapper.insert(user);
    }

    @Override
    public User findById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User findByUserName(String username) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<User> userList = userMapper.selectByExample(userExample);
        return userList.size() == 0 ? null : userList.get(0);
    }

    @Override
    public List<User> findLikeUserName(String username) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameLike(username);
        List<User> userList = userMapper.selectByExample(userExample);
        return userList;
    }

    @Override
    public List<User> getAllUser(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = userMapper.selectByExample(new UserExample());
        return userList;
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    public void deleteById(Integer id) {
        userMapper.deleteByPrimaryKey(id);
    }
}
