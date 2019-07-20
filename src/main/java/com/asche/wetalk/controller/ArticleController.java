package com.asche.wetalk.controller;

import com.asche.wetalk.common.CommonResult;
import com.asche.wetalk.common.ResultCode;
import com.asche.wetalk.entity.Article;
import com.asche.wetalk.entity.BodyType;
import com.asche.wetalk.entity.Like;
import com.asche.wetalk.entity.User;
import com.asche.wetalk.mapper.LikeMapper;
import com.asche.wetalk.service.ArticleService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping("/article")
@Api(description = "文章管理")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private LikeMapper likeMapper;

    @PostMapping("/publish")
    public void publish(Article article) {
        articleService.publish(article);
    }

    @RequestMapping("/find/id")
    public CommonResult findById(int id) {
        Article article = articleService.findById(id);
        return CommonResult.success(article);
    }

    @RequestMapping("/find/title")
    public CommonResult findByTitle(@RequestParam(name = "title") String title) {
        List<Article> articles = articleService.findByTitle(title);
        return CommonResult.success(articles);
    }

    @RequestMapping("/find/author")
    public CommonResult findByAuthor(@RequestParam(name = "authorId") int authorId) {
        List<Article> articles = articleService.findByAuthor(authorId);
        return CommonResult.success(articles);
    }

    @RequestMapping("/all")
    public PageInfo getAll(@RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
                           @RequestParam(name = "pageSize", defaultValue = "5") int pageSize) {
        List<Article> allArticle = articleService.getAllArticle(pageNum, pageSize);
        PageInfo pageInfo = new PageInfo<>(allArticle);
        return pageInfo;
    }

    @RequestMapping("/like")
    public CommonResult like(@RequestParam Integer id, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            Like like = new Like(id, user.getId(), BodyType.Article);
            int code = articleService.like(like);
            if (code == 0)
                return CommonResult.failed("重复点赞！");
            else
                return CommonResult.success("点赞成功！", null);
        }
        return CommonResult.failed(ResultCode.VALIDATE_FAILED);
    }

    @RequestMapping("/unLike")
    public CommonResult unLike(@RequestParam Integer id, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            Like like = new Like(id, user.getId(), BodyType.Article);
            int code = articleService.unLike(like);
            if (code == 0)
                return CommonResult.failed("你尚未点赞！");
            else
                return CommonResult.success("取消成功！", null);
        }
        return CommonResult.failed(ResultCode.VALIDATE_FAILED);
    }

}
