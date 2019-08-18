package com.asche.wetalk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.asche.wetalk.util.PrintUtils.println;

@Controller
public class MainController {

    @GetMapping("/")
    @ResponseBody
    public String home(){
        return "Home Page!";
    }

    @GetMapping("/vip")
    public String vip(HttpServletRequest request){
        return "vip";
    }

    @GetMapping("/redirect")
    public void redirectTest(@RequestParam(name = "url", required = true) String url, HttpServletResponse response){
        response.setStatus(302);
        response.addHeader("Location", url);
        println(response.getClass().getCanonicalName());
    }
}
