package com.springsecurity.browser.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springsecurity.browser.support.SimpleResponse;
import com.springsecurity.core.properties.LoginType;
import com.springsecurity.core.properties.SecurityProperties;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义springsecurity认证失败返回数据
 */
//@Component
//public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Override
//    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
//            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
//            response.setContentType("application/json;charset=UTF-8");
//            response.getWriter().write(objectMapper.writeValueAsString(exception));
//
//}




/**
 * 自定义springsecurity认证失败返回类型 ：JSon与springsecurity页面
 */
@Component
public class MyAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if(LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setContentType("application/json;charset=UTF-8");
//            response.getWriter().write(objectMapper.writeValueAsString(exception));
            response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(exception.getMessage())));
        }else{
            super.onAuthenticationFailure(request,response,exception);
        }
    }
}
