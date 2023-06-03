package com.example.demo.config.log.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@Component
@Slf4j
class LogRequestFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        if(httpRequest.getRequestURI().contains("/actuator/health")){
            return;
        }
        CustomHttpRequestWrapper requestWrapper = new CustomHttpRequestWrapper(httpRequest);
        filterChain.doFilter(requestWrapper, servletResponse);
        return;

    }

}
