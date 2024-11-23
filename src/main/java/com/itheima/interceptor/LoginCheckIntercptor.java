package com.itheima.interceptor;


import com.alibaba.fastjson2.JSON;
import com.itheima.pojo.Result;
import com.itheima.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class LoginCheckIntercptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
        //1获取url
        String url=req.getRequestURI();
        //2url是否是登录的
        /*
        if (url.contains("login")) {
            log.info("登录");
            return true;
        }//拦截器的配置已经设置了
        */
        //3获取token
        String jwt = req.getHeader("token");
        //4有token是否为空
        if (!StringUtils.hasText(jwt)) {
            log.info("token为空");
            Result error = Result.error("NOT_LOGIN");
            String jsonString = JSON.toJSONString(error);
            res.getWriter().write(jsonString);
            return false;
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
            return true;
        }
        //6放行
        log.info("jwt校验成功,继续操作");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("afterCompletion");
    }
}
