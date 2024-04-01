package com.thec.handler;

import com.alibaba.fastjson.JSON;
import com.thec.entity.Result;
import com.thec.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author thec
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        Result responseResult = new Result(HttpStatus.FORBIDDEN.value(),"您的权限不足");
        String json = JSON.toJSONString(responseResult);
        WebUtils.renderString(httpServletResponse,json);
    }
}
