package com.asche.wetalk.controller;

import com.asche.wetalk.common.CommonResult;
import com.asche.wetalk.entity.Article;
import com.asche.wetalk.mapper.ArticleMapper;
import com.asche.wetalk.service.ArticleService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/article")
@Api(description = "文章管理")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping("/publish")
    public void publish(Article article){
        articleService.publish(article);
    }

    @RequestMapping("/find/id")
    public CommonResult findById(int id){
        Article article = articleService.findById(id);
        return CommonResult.success(article);
    }

    @RequestMapping("/find/title")
    public CommonResult findByTitle(@RequestParam(name = "title") String title){
        List<Article> articles = articleService.findByTitle(title);
        return CommonResult.success(articles);
    }

    @RequestMapping("/find/author")
    public CommonResult findByAuthor(@RequestParam(name = "authorId") int authorId){
        List<Article> articles = articleService.findByAuthor(authorId);
        return CommonResult.success(articles);
    }

    @RequestMapping("/all")
    public PageInfo getAll(@RequestParam(name = "pageNum", required = false, defaultValue = "1")int pageNum,
                           @RequestParam(name = "pageSize", defaultValue = "5")int pageSize){
        List<Article> allArticle = articleService.getAllArticle(pageNum, pageSize);
        PageInfo pageInfo = new PageInfo<>(allArticle);
        return pageInfo;
    }


}
