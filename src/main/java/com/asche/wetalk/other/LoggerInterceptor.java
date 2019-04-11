package com.asche.wetalk.other;

import com.asche.wetalk.bean.LoggerBean;
import com.asche.wetalk.repository.LoggerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.asche.wetalk.util.PrintUtils.println;

public class LoggerInterceptor implements HandlerInterceptor {

    @Autowired
    private LoggerRepository loggerRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(new Date());

        LoggerBean loggerBean = new LoggerBean();
        loggerBean.setIp(request.getRemoteAddr());
        loggerBean.setTime(time);
        loggerBean.setType(request.getMethod());
        loggerBean.setUri(request.getRequestURI());
        loggerBean.setArgs(request.getQueryString());
        loggerRepository.save(loggerBean);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
