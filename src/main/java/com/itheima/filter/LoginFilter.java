package com.itheima.filter;

import com.alibaba.fastjson2.JSON;
import com.itheima.pojo.Result;
import com.itheima.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j
//@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        //1获取url
        String url=req.getRequestURI();
        //2url是否是登录的
        if (url.contains("login")) {
            log.info("登录");
            chain.doFilter(request, response);
            return;
        }
        //3获取token
        String jwt = req.getHeader("token");
        //4有token是否为空
        if (!StringUtils.hasText(jwt)) {
            log.info("token为空");
            Result error = Result.error("NOT_LOGIN");
            String jsonString = JSON.toJSONString(error);
            res.getWriter().write(jsonString);
            return;
        }
        //5token不为空时校验
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("jwt令牌出错");
            Result error = Result.error("NOT_LOGIN");
            String jsonString = JSON.toJSONString(error);
            res.getWriter().write(jsonString);
            return;
        }
        //6放行
        log.info("jwt校验成功,继续操作");
        chain.doFilter(request, response);
    }
}
