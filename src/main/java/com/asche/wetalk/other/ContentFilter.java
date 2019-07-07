package com.asche.wetalk.other;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Component
public class ContentFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(ContentFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info(request.getRemoteAddr());

        logger.info(request.getClass().getCanonicalName());
        chain.doFilter(request, response);
    }
}
