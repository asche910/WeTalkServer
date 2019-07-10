package com.asche.wetalk.mapper;

import com.asche.wetalk.entity.User;

import java.util.List;

//@Mapper
public interface UserMapper {

    User findUserById(Integer id);

    User findUserByUserName(String username);

    User findUserByEmail(String email);

    List<User> getAllUser();

    void addUser(User user);

    void updateUser(User user);

    void deleteUserById(Integer id);
}
