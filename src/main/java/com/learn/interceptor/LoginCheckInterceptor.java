package com.learn.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.learn.pojo.Result;
import com.learn.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override //目标资源执行前调用，返回true放行，返回false不放行
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        //1. 获取请求url
        String url = httpServletRequest.getRequestURL().toString();
        log.info("请求的url:{}", url);

        //2. 判断是否包含login
        if (url.contains("login")) {
            log.info("登录操作，放行...");
            return true;
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
            return false;
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
            return false;
        }

        //6. 放行
        log.info("令牌合法，放行");
        return true;

    }

    @Override //目标资源执行后调用
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle ...");
    }

    @Override //视图渲染完成后，最后调用
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion ...");
    }
}
