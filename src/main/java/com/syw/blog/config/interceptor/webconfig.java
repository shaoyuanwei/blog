package com.syw.blog.config.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@EnableWebMvc
@Configuration
public class webconfig implements WebMvcConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(webconfig.class);

    //token 在header的拦截器
    @Bean
    public HeaderTokenInterceptor headerTokenInterceptor() {
        return new HeaderTokenInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        logger.warn("进入拦截器配置器------------------->>>>>>>>>>>>");

//        registry.addInterceptor(getTokenHeader()).addPathPatterns("/*/*").excludePathPatterns("/robots.txt");
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(headerTokenInterceptor());
        interceptorRegistration.addPathPatterns("/**").excludePathPatterns("/api/user/login");
    }

}
