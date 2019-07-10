package com.asche.wetalk.controller;

import com.asche.wetalk.common.CommonResult;
import com.asche.wetalk.entity.User;
import com.asche.wetalk.mapper.UserMapper;
import com.asche.wetalk.repository.UserRepository;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.asche.wetalk.util.PrintUtils.println;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Value("${spring.logo.test}")
    String logo;

    @GetMapping("/add")
    @ResponseBody
    public String addUser(@RequestParam String name, @RequestParam String password, String email) {

        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setEmail(email);
        userMapper.addUser(user);
        return "Add success!";
    }

    @GetMapping(value = "/query", produces = "application/json;charset=utf-8")
    @ResponseBody
    public CommonResult query(@RequestParam int id){
        User user = userMapper.findUserById(id);
        if (user != null){
             return CommonResult.success(user);
        }else{
            return CommonResult.success(null);
        }
    }

    @GetMapping(value = "/query_name", produces = "application/json;charset=utf-8")
    @ResponseBody
    public CommonResult queryByName(@RequestParam String username){
        User user = userMapper.findUserByUserName(username);
        if (user != null){
            return CommonResult.success(user);
        }else{
            return CommonResult.success(null);
        }
    }

    @GetMapping("/update")
    @ResponseBody
    public CommonResult updateUser(User user){
        println(user.toString());
        userMapper.updateUser(user);
        return CommonResult.success(null);
    }

    @GetMapping(value = "/delete", produces = "application/json;charset=utf-8")
    @ResponseBody
    public CommonResult deleteById(@RequestParam int id){
        userMapper.deleteUserById(id);
        return CommonResult.success(null);
    }


    @GetMapping("/all")
    @ResponseBody
    public CommonResult getAllUser(){
        List<User> userList = userMapper.getAllUser();
        return CommonResult.success(userList);
    }

    @GetMapping("/test")
    @ResponseBody
    public PageInfo tset(){
        PageHelper.startPage(2, 5);
        List<User> userList = userMapper.getAllUser();

        List<User> list = new ArrayList<>();
        list.addAll(userList);
//        list.addAll(userRepository.findAll());


        PageInfo<User> pageInfo = new PageInfo<>(list);
        System.out.println(pageInfo.toString());
//        return CommonResult.success("test message!");
        return pageInfo;
    }


    @GetMapping("/redirect")
    public void redirectTest(HttpServletResponse response){
        response.setStatus(302);
        response.addHeader("Location", "http://asche.top");

        println(response.getClass().getCanonicalName());
    }
}
