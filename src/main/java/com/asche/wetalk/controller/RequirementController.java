package com.asche.wetalk.controller;

import com.asche.wetalk.common.CommonResult;
import com.asche.wetalk.common.ResultCode;
import com.asche.wetalk.entity.*;
import com.asche.wetalk.service.RequirementService;
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
@RequestMapping("/requirement")
@Api(description = "需求管理")
public class RequirementController {

    @Autowired
    private RequirementService requirementService;

    @PostMapping("/publish")
    public CommonResult publish(Requirement requirement, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            requirement.setId(null);
            requirement.setAuthorid(user.getId());
            requirement.setTime(TimeUtils.getCurrentTime());
            requirement.setLikenum(0);
            requirement.setCommentnum(0);
            requirementService.publish(requirement);
            return CommonResult.success("发布成功！", null);
        }
        return CommonResult.failed(ResultCode.VALIDATE_FAILED);
    }

    @RequestMapping("/find/id")
    public CommonResult findById(int id) {
        Requirement requirement = requirementService.findById(id);
        return CommonResult.success(requirement);
    }

    @RequestMapping("/find/title")
    public CommonResult findByTitle(@RequestParam(name = "title") String title) {
        List<Requirement> requirements = requirementService.findByTitle(title);
        return CommonResult.success(requirements);
    }

    @RequestMapping("/find/author")
    public CommonResult findByAuthor(@RequestParam(name = "authorId") int authorId) {
        List<Requirement> requirements = requirementService.findByAuthor(authorId);
        return CommonResult.success(requirements);
    }

    @RequestMapping("/all")
    public PageInfo getAll(@RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
                           @RequestParam(name = "pageSize", defaultValue = "5") int pageSize) {
        List<Requirement> requirements = requirementService.getAll(pageNum, pageSize);
        PageInfo pageInfo = new PageInfo<>(requirements);
        return pageInfo;
    }

    @RequestMapping("/like")
    public CommonResult like(@RequestParam Integer id, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            Like like = new Like(id, user.getId(), BodyType.Requirement);
            int code = requirementService.like(like);
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
            Like like = new Like(id, user.getId(), BodyType.Requirement);
            int code = requirementService.unLike(like);
            if (code == 0)
                return CommonResult.failed("你尚未点赞！");
            else
                return CommonResult.success("取消成功！", null);
        }
        return CommonResult.failed(ResultCode.VALIDATE_FAILED);
    }

}
