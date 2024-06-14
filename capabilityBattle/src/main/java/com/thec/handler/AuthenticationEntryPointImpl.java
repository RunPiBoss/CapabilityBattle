package com.thec.handler;

import com.alibaba.fastjson.JSON;
import com.thec.entity.Result;
import com.thec.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author thec
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        Result responseResult = new Result(HttpStatus.UNAUTHORIZED.value(),"用户名认证失败");
        String json = JSON.toJSONString(responseResult);
        WebUtils.renderString(httpServletResponse,json);
    }
}
