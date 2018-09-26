package com.syw.blog.config.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class webconfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getTokenHeader()).addPathPatterns("/*/*").excludePathPatterns("/robots.txt");
    }

    //token 在header的拦截器
    @Bean
    public HandlerInterceptor getTokenHeader() {
        return new HeaderTokenInterceptor();
    }

}
