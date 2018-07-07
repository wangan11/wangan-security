package com.springsecurity.browser.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springsecurity.core.properties.LoginType;
import com.springsecurity.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义springsecurity认证成功返回数据
 */
//@Component
//public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
//
//    @Autowired
//    private ObjectMapper objectMapper;
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        response.setContentType("text/html; charset=utf-8");
//        response.getWriter().write(objectMapper.writeValueAsString(authentication));
//    }
//}
//

/**
 * 自定义springsecurity认证成功返回类型 ：JSon与springsecurity页面
 */
@Component
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if (LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())) {
            response.setContentType("text/html; charset=utf-8");
            response.getWriter().write(objectMapper.writeValueAsString(authentication));
        }else{
            super.onAuthenticationSuccess(request,response,authentication);
        }
    }
}
