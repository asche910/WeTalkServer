package com.asche.wetalk.controller;

import com.asche.wetalk.common.CommonResult;
import com.asche.wetalk.common.ResultCode;
import com.asche.wetalk.entity.User;
import com.asche.wetalk.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.asche.wetalk.util.PrintUtils.println;

@RestController
@RequestMapping("/user")
@Api( description = "用户管理")
public class UserController {

    @Autowired
    private UserService userService;

    @Value("${spring.logo.test}")
    String logo;

    @GetMapping("/info")
    public CommonResult currentUser(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if (user != null){
            return CommonResult.success(user);
        }
        return CommonResult.failed(ResultCode.VALIDATE_FAILED);
    }

    @GetMapping("/add")
    @ApiOperation("添加用户")
    public CommonResult addUser(@Validated User user) {
        try {
            userService.addUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("Add failed!");
        }
        return CommonResult.success("Add success!");
    }

    @GetMapping(value = "/queryById", produces = "application/json;charset=utf-8")
    @ApiOperation("按id查询用户")
    public CommonResult query(@RequestParam int id){
        User user = userService.findById(id);
        if (user != null){
             return CommonResult.success(user);
        }else{
            return CommonResult.success(null);
        }
    }

    @GetMapping(value = "/queryByUsername", produces = "application/json;charset=utf-8")
    public CommonResult queryByName(@RequestParam String username){
        List<User> userList = userService.findLikeUserName(username);
        if (userList != null){
            return CommonResult.success(userList);
        }else{
            return CommonResult.success(null);
        }
    }

    @GetMapping("/all")
    public PageInfo getAllUser(@RequestParam(name = "pageNum", required = false, defaultValue = "1")int pageNum,
                               @RequestParam(name = "pageSize", defaultValue = "5")int pageSize){
        List<User> userList = userService.getAllUser(pageNum, pageSize);
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        return pageInfo;
    }

    @GetMapping("/update")
    public CommonResult updateUser(User user){
        println(user.toString());
        userService.updateUser(user);
        return CommonResult.success(user);
    }

    @GetMapping(value = "/delete", produces = "application/json;charset=utf-8")
    public CommonResult deleteById(@RequestParam int id){
        userService.deleteById(id);
        return CommonResult.success("delete success!", null);
    }

    @GetMapping("/test")
    public PageInfo tset(){
        PageHelper.startPage(1, 5);
        List<User> userList = userService.getAllUser(1, 10);

        PageInfo<User> pageInfo = new PageInfo<>(userList);
        System.out.println(pageInfo.toString());
//        return CommonResult.success("test message!");
        return pageInfo;
    }

}
