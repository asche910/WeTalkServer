package com.asche.wetalk.controller;

import com.asche.wetalk.bean.UserBean;
import com.asche.wetalk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static com.asche.wetalk.util.PrintUtils.println;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Value("${spring.logo.test}")
    String logo;

    @GetMapping("/add")
    @ResponseBody
    public String addUser(@RequestParam String name, @RequestParam String password, String email) {

        UserBean userBean = new UserBean();
        userBean.setName(name);
        userBean.setPassword(password);
        userBean.setEmail(email);
        userRepository.save(userBean);
        return "Add success!";
    }

    @GetMapping(value = "/query", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String query(@RequestParam int id){
        Optional<UserBean> optional = userRepository.findById(id);
        if (optional.isPresent()){
            UserBean userBean = optional.get();
            println(userBean.toString());
            return userBean.toString();
        }
        return "{\"code\":-1, \"status\":\"fail\"}";
    }

    @GetMapping(value = "/query_name", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String queryByName(@RequestParam String name){
        List<UserBean> users = userRepository.queryByName(name);
        String goal = Arrays.toString(users.toArray());
        println(goal);
        return goal;
    }

    @GetMapping("/update")
    @ResponseBody
    public String updateUser(UserBean userBean){
        println(userBean.toString());
        userRepository.save(userBean);
        return "OK";
    }

    @GetMapping(value = "/delete", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String deleteById(@RequestParam int id){
        userRepository.deleteById(id);
        return "{\"code\":0, \"status\":\"success\"}";
    }


    @GetMapping("/all")
    @ResponseBody
    public Iterable<UserBean> getAllUser(){
        return userRepository.findAll();
    }

    @GetMapping("/redirect")
    public void redirectTest(HttpServletResponse response){
        response.setStatus(302);
        response.addHeader("Location", "https://asche.top");

        println(response.getClass().getCanonicalName());
    }
}
