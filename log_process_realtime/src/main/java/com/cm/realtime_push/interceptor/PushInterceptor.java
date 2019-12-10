package com.cm.realtime_push.interceptor;

import com.cm.config.ConfigBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PushInterceptor extends HandlerInterceptorAdapter {


    //拦截到请求之后，设置indexName业务线。统一放行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String header = request.getHeader("indexName");
        if(StringUtils.isNotBlank(header)){
            ConfigBean.setIndexName(header);
        }
        return true;
    }
}
