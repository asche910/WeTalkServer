package com.asche.wetalk.repository;

import com.asche.wetalk.entity.UserBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<UserBean, Integer> {

    @Query("SELECT user_bean from UserBean user_bean where user_bean.name like concat('%', :name, '%') ")
    List<UserBean> queryByName(String name);

    @Query("select user_bean.name from UserBean user_bean where user_bean.name like concat('%', :name, '%') ")
    List<String> queryName(String name);

    @Query("select user_bean from UserBean  user_bean where user_bean.name = lower(:username) ")
    UserBean login(@Param("username") String username);
}
