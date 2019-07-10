package com.asche.wetalk.mapper;

import com.asche.wetalk.entity.UserBean;

import java.util.List;

//@Mapper
public interface UserMapper {

//    @Select("select * from user_bean")
    List<UserBean> getAllUser();

    UserBean findUserById(Integer id);

    UserBean findUserByEmail(String email);

    void addUser(UserBean user);
}
