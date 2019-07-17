package com.asche.wetalk.other;

import com.asche.wetalk.entity.Logger;
import com.asche.wetalk.mapper.LoggerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LoggerInterceptor implements HandlerInterceptor {

    @Autowired
    private LoggerMapper loggerMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(new Date());

        Logger logger = new Logger();
        logger.setIp(request.getRemoteAddr());
        logger.setTime(time);
        logger.setType(request.getMethod());
        logger.setUri(request.getRequestURI());
        logger.setArgs(request.getQueryString());
        loggerMapper.add(logger);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
