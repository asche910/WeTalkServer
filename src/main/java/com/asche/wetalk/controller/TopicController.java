package com.asche.wetalk.controller;

import com.asche.wetalk.common.CommonResult;
import com.asche.wetalk.common.ResultCode;
import com.asche.wetalk.entity.*;
import com.asche.wetalk.service.TopicService;
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
@RequestMapping("/topic")
@Api(description = "话题管理")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @PostMapping("/publish")
    public CommonResult publish(Topic topic, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            topic.setId(null);
            topic.setAuthorid(user.getId());
            topic.setTime(TimeUtils.getCurrentTime());
            topic.setFollowernum(0);
            topic.setReplynum(0);
            topicService.publish(topic);
            return CommonResult.success("发布成功！", null);
        }
        return CommonResult.failed(ResultCode.VALIDATE_FAILED);
    }

    @RequestMapping("/find/id")
    public CommonResult findById(int id) {
        Topic article = topicService.findById(id);
        return CommonResult.success(article);
    }

    @RequestMapping("/find/title")
    public CommonResult findByTitle(@RequestParam(name = "title") String title) {
        List<Topic> topics = topicService.findByTitle(title);
        return CommonResult.success(topics);
    }

    @RequestMapping("/find/author")
    public CommonResult findByAuthor(@RequestParam(name = "authorId") int authorId) {
        List<Topic> topics = topicService.findByAuthor(authorId);
        return CommonResult.success(topics);
    }

    @RequestMapping("/all")
    public PageInfo getAll(@RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
                           @RequestParam(name = "pageSize", defaultValue = "5") int pageSize) {
        List<Topic> topics = topicService.getAll(pageNum, pageSize);
        PageInfo pageInfo = new PageInfo<>(topics);
        return pageInfo;
    }

    @RequestMapping("/like")
    public CommonResult like(@RequestParam Integer id, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            Like like = new Like(id, user.getId(), BodyType.Topic);
            int code = topicService.like(like);
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
            Like like = new Like(id, user.getId(), BodyType.Topic);
            int code = topicService.unLike(like);
            if (code == 0)
                return CommonResult.failed("你尚未点赞！");
            else
                return CommonResult.success("取消成功！", null);
        }
        return CommonResult.failed(ResultCode.VALIDATE_FAILED);
    }

}
