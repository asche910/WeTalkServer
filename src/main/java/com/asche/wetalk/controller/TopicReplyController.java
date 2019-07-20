package com.asche.wetalk.controller;

import com.asche.wetalk.common.CommonResult;
import com.asche.wetalk.common.ResultCode;
import com.asche.wetalk.entity.*;
import com.asche.wetalk.service.TopicReplyService;
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
@RequestMapping("/topicReply")
@Api(description = "话题回复管理")
public class TopicReplyController {

    @Autowired
    private TopicReplyService topicReplyService;

    @PostMapping("/publish")
    public CommonResult publish(Topicreply topicreply, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            topicreply.setId(null);
            topicreply.setAuthorid(user.getId());
            topicreply.setTime(TimeUtils.getCurrentTime());
            topicreply.setLikenum(0);
            topicreply.setCommentnum(0);
            topicReplyService.publish(topicreply);
            return CommonResult.success("发布成功！", null);
        }
        return CommonResult.failed(ResultCode.VALIDATE_FAILED);
    }

    @RequestMapping("/find/id")
    public CommonResult findById(int id) {
        Topicreply topicreply = topicReplyService.findById(id);
        return CommonResult.success(topicreply);
    }

    @RequestMapping("/find/topic")
    public CommonResult findByTopic(@RequestParam(name = "topic") Integer id) {
        List<Topicreply> topicreplies = topicReplyService.findByTopic(id);
        return CommonResult.success(topicreplies);
    }

    @RequestMapping("/find/author")
    public CommonResult findByAuthor(@RequestParam(name = "authorId") int authorId) {
        List<Topicreply> topicreplies = topicReplyService.findByAuthor(authorId);
        return CommonResult.success(topicreplies);
    }

    @RequestMapping("/all")
    public PageInfo getAll(@RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
                           @RequestParam(name = "pageSize", defaultValue = "5") int pageSize) {
        List<Topicreply> topicreplies = topicReplyService.getAll(pageNum, pageSize);
        PageInfo pageInfo = new PageInfo<>(topicreplies);
        return pageInfo;
    }

    @RequestMapping("/like")
    public CommonResult like(@RequestParam Integer id, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            Like like = new Like(id, user.getId(), BodyType.TopicReply);
            int code = topicReplyService.like(like);
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
            Like like = new Like(id, user.getId(), BodyType.TopicReply);
            int code = topicReplyService.unLike(like);
            if (code == 0)
                return CommonResult.failed("你尚未点赞！");
            else
                return CommonResult.success("取消成功！", null);
        }
        return CommonResult.failed(ResultCode.VALIDATE_FAILED);
    }

}

