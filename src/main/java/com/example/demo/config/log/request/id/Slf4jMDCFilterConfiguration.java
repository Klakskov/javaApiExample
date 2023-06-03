package com.example.demo.config.log.request.id;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "config.slf4jfilter")
class Slf4jMDCFilterConfiguration {

    public static final String DEFAULT_RESPONSE_TOKEN_HEADER = "requestId";
    public static final String DEFAULT_MDC_UUID_TOKEN_KEY = "Slf4jMDCFilter.UUID";

    private String responseHeader = DEFAULT_RESPONSE_TOKEN_HEADER;
    private String requestHeader = DEFAULT_RESPONSE_TOKEN_HEADER;

    private String mdcTokenKey = DEFAULT_MDC_UUID_TOKEN_KEY;


    // probably, will not work with spring boot 3 due to paralel processing. Best to use spring sleuth ?
    @Bean
    public FilterRegistrationBean servletRegistrationBean() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        final Slf4jMDCFilter log4jMDCFilterFilter = new Slf4jMDCFilter(responseHeader, mdcTokenKey, requestHeader);
        registrationBean.setFilter(log4jMDCFilterFilter);
        registrationBean.setOrder(2);
        return registrationBean;
    }
}