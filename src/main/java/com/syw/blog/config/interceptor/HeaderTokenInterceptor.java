package com.syw.blog.config.interceptor;

import com.syw.blog.ptool.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HeaderTokenInterceptor implements HandlerInterceptor {

    private final static Logger logger = LoggerFactory.getLogger(HeaderTokenInterceptor.class);

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();
        String tokenStr = request.getParameter("token");
        String token = "";

        if (requestURI.contains("/*/")) {
            token = request.getHeader("token");
            if (token == null && tokenStr == null) {
                logger.info("real token : =================is null");
                String str = "{'result' : 801, 'message':'缺少token，无法验证'.'data':null}";
                dealErrorReturn(request, response, str);
                return false;
            }
            if (tokenStr != null) {
                token = tokenStr;
            }
            token = jwtUtil.updateToken(token);
            logger.info("real token:==============={}", token);
            logger.info("real other:==============={}", request.getHeader("Cookie"));
        }

        response.setHeader("token", token);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    public void dealErrorReturn(HttpServletRequest request, HttpServletResponse response, Object object) {
        String json = (String) object;
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(json);
        } catch (IOException ex) {
            logger.error("response error", ex);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
