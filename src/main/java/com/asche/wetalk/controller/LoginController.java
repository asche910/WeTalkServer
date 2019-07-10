package com.asche.wetalk.controller;

import com.asche.wetalk.entity.User;
import com.asche.wetalk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import static com.asche.wetalk.util.PrintUtils.println;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping(value = "/loginCheck")
    @ResponseBody
    public String loginCheck(@RequestParam String username, @RequestParam String password, HttpServletRequest request){
        println("Login: " + username + " --- " + password);
        User user = userRepository.login(username);
        if (user != null){
            if (user.getPassword().equals(password)){
                request.getSession().setAttribute("user", username);
                return "Login success!";
            }
        }
        return "Login failed!";
    }
}
