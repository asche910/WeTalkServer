package com.asche.wetalk.controller;

import com.asche.wetalk.common.CommonResult;
import com.asche.wetalk.common.ResultCode;
import com.asche.wetalk.entity.*;
import com.asche.wetalk.service.CommentService;
import com.asche.wetalk.util.TimeUtils;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@SuppressWarnings("Duplicates")
@RestController
@RequestMapping("/comment")
@Api(description = "评论管理")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/publish")
    public CommonResult publish(Comment comment, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            comment.setId(null);
            comment.setAuthorid(user.getId());
            comment.setTime(TimeUtils.getCurrentTime());
            comment.setLikenum(0);
            commentService.publish(comment);
            return CommonResult.success("发布成功！", null);
        }
        return CommonResult.failed(ResultCode.VALIDATE_FAILED);
    }

    @RequestMapping("/find/id")
    public CommonResult findById(int id) {
        Comment article = commentService.findById(id);
        return CommonResult.success(article);
    }

    @RequestMapping("/find/bodyId")
    public CommonResult findByBodyId(@RequestParam(name = "bodyId") Integer bodyId, @RequestParam Integer type) {
        List<Comment> articles = commentService.findByBodyId(bodyId, type);
        return CommonResult.success(articles);
    }

    @RequestMapping("/find/author")
    public CommonResult findByAuthor(@RequestParam(name = "authorId") int authorId) {
        List<Comment> articles = commentService.findByAuthor(authorId);
        return CommonResult.success(articles);
    }

    @RequestMapping("/all")
    public PageInfo getAll(@RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
                           @RequestParam(name = "pageSize", defaultValue = "5") int pageSize) {
        List<Comment> allArticle = commentService.getAll(pageNum, pageSize);
        PageInfo pageInfo = new PageInfo<>(allArticle);
        return pageInfo;
    }

    @RequestMapping("/like")
    public CommonResult like(@RequestParam Integer id, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            Like like = new Like(id, user.getId(), BodyType.Comment);
            int code = commentService.like(like);
            if (code == 0)
                return CommonResult.failed("重复点赞！");
            else
                return CommonResult.success("点赞成功！", null);
        }
        return CommonResult.failed(ResultCode.VALIDATE_FAILED);
    }

    @RequestMapping("/unLike")
    public CommonResult unLike(@RequestParam Integer id, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            Like like = new Like(id, user.getId(), BodyType.Comment);
            int code = commentService.unLike(like);
            if (code == 0)
                return CommonResult.failed("你尚未点赞！");
            else
                return CommonResult.success("取消成功！", null);
        }
        return CommonResult.failed(ResultCode.VALIDATE_FAILED);
    }
}
