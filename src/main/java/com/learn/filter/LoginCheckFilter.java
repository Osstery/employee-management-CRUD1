package com.learn.filter;

import com.alibaba.fastjson.JSONObject;
import com.learn.pojo.Result;
import com.learn.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        //1. 获取请求url
        String url = httpServletRequest.getRequestURL().toString();
        log.info("请求的url:{}", url);

        //2. 判断是否包含login
        if (url.contains("login")) {
            log.info("登录操作，放行...");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        //3. 获取请求头中的令牌Token
        String jwt = httpServletRequest.getHeader("token");

        //4. 判断令牌是否存在，如果不存在，返回错误结果（未登录）
        if (!StringUtils.hasLength(jwt)) {
            log.info("请求头中Token为空串，返回未登录信息");
            Result error = Result.error("NOT_LOGIN");
            //手动转换对象--json ----阿里巴巴Fastjson
            String notLogin = JSONObject.toJSONString(error);
            httpServletResponse.getWriter().write(notLogin);
            return;
        }

        //5. 解析token，如果解析失败，返回错误结果（未登录）
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("解析令牌失败，返回未登录错误信息");
            Result error = Result.error("NOT_LOGIN");
            //手动转换对象--json ----阿里巴巴Fastjson
            String notLogin = JSONObject.toJSONString(error);
            httpServletResponse.getWriter().write(notLogin);
            return;
        }

        //6. 放行
        log.info("令牌合法，放行");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
