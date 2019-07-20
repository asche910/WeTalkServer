package com.asche.wetalk.controller;

import com.asche.wetalk.common.CommonResult;
import com.asche.wetalk.common.ResultCode;
import com.asche.wetalk.entity.User;
import com.asche.wetalk.entity.UserExample;
import com.asche.wetalk.mapper.UserMapper;
import com.asche.wetalk.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

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

     /*   UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<User> userList = userMapper.selectByExample(userExample);
*/
        User user = userService.findByUserName(username);

        if (user != null){
            if (user.getPassword().equals(password)){
                request.getSession().setAttribute("user", user);
                return CommonResult.success("Login success!", user);
            }
        }
        return CommonResult.failed("Login failed!");
    }
}
