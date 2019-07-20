package com.asche.wetalk.controller;

import com.asche.wetalk.common.CommonResult;
import com.asche.wetalk.common.ResultCode;
import com.asche.wetalk.entity.*;
import com.asche.wetalk.service.HappenService;
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
@RequestMapping("/happen")
@Api(description = "点滴管理")
public class HappenController {

    @Autowired
    private HappenService happenService;

    @PostMapping("/publish")
    public CommonResult publish(Happen happen, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            happen.setId(null);
            happen.setAuthorid(user.getId());
            happen.setTime(TimeUtils.getCurrentTime());
            happen.setLikenum(0);
            happen.setCommentnum(0);
            happenService.publish(happen);
            return CommonResult.success("发布成功！", null);
        }
        return CommonResult.failed(ResultCode.VALIDATE_FAILED);
    }

    @RequestMapping("/find/id")
    public CommonResult findById(int id) {
        Happen article = happenService.findById(id);
        return CommonResult.success(article);
    }

    @RequestMapping("/find/author")
    public CommonResult findByAuthor(@RequestParam(name = "authorId") int authorId) {
        List<Happen> articles = happenService.findByAuthor(authorId);
        return CommonResult.success(articles);
    }

    @RequestMapping("/all")
    public PageInfo getAll(@RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
                           @RequestParam(name = "pageSize", defaultValue = "5") int pageSize) {
        List<Happen> allArticle = happenService.getAll(pageNum, pageSize);
        PageInfo pageInfo = new PageInfo<>(allArticle);
        return pageInfo;
    }

    @RequestMapping("/like")
    public CommonResult like(@RequestParam Integer id, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            Like like = new Like(id, user.getId(), BodyType.Happen);
            int code = happenService.like(like);
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
            Like like = new Like(id, user.getId(), BodyType.Happen);
            int code = happenService.unLike(like);
            if (code == 0)
                return CommonResult.failed("你尚未点赞！");
            else
                return CommonResult.success("取消成功！", null);
        }
        return CommonResult.failed(ResultCode.VALIDATE_FAILED);
    }

}
