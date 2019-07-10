package com.asche.wetalk.repository;

import com.asche.wetalk.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT user_bean from User user_bean where user_bean.name like concat('%', :name, '%') ")
    List<User> queryByName(String name);

    @Query("select user_bean.name from User user_bean where user_bean.name like concat('%', :name, '%') ")
    List<String> queryName(String name);

    @Query("select user_bean from User  user_bean where user_bean.name = lower(:username) ")
    User login(@Param("username") String username);
}
