package com.asche.wetalk.controller;

import com.asche.wetalk.common.CommonResult;
import com.asche.wetalk.entity.User;
import com.asche.wetalk.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static com.asche.wetalk.util.PrintUtils.println;

@Api(description = "登录管理")
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping(value = "/loginCheck")
    @ResponseBody
    public CommonResult loginCheck(@RequestParam String username, @RequestParam String password, HttpServletRequest request){
        println("Login: " + username + " --- " + password);

        User user = userService.findByUserName(username);

        if (user != null){
            if (user.getPassword().equals(password)){
                // request.getSession().setAttribute("user", user);
                return CommonResult.success("Login success!", null);
            }
        }
        return CommonResult.failed("Login failed!");
    }

    @RequestMapping(value = "/logout")
    @ResponseBody
    public CommonResult logout(HttpServletRequest request){
        request.getSession().invalidate();
        return CommonResult.success();
    }
}
