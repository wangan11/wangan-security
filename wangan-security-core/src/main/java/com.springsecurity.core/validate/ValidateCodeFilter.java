package com.springsecurity.core.validate;

import com.springsecurity.core.validate.generate.ImageCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 如果是验证码请求则需要校验验证码，否则直接通过
 * 获取请求code
 * 从缓存中取出code
 * 校验code
 */
@Component("validateCodeFilter")
public class ValidateCodeFilter extends OncePerRequestFilter {

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    private String imageSesson = "IMAGESESSION";
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (StringUtils.endsWithIgnoreCase(request.getRequestURI(), "/authention/form") && StringUtils.endsWithIgnoreCase(request.getMethod(), "post")) {

            try {
                validate(new ServletWebRequest(request));
            } catch (ValidateCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(request,response,e);
                return;
            }
        }
        doFilter(request, response, filterChain);

    }

    private void validate(ServletWebRequest servletWebRequest) throws ServletRequestBindingException {
        String imageCode = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(), "imageCode");
        ImageCode img = (ImageCode) sessionStrategy.getAttribute(servletWebRequest, imageSesson);
        if(StringUtils.isBlank(imageCode)){
            throw new ValidateCodeException("验证码不能为空");
        }
        if(img==null){
            throw new ValidateCodeException("验证码不存在");
        }
        if(img.isExpried()){
            sessionStrategy.removeAttribute(servletWebRequest,imageSesson);
            throw new ValidateCodeException("验证码已过期");
        }
        if(!StringUtils.endsWithIgnoreCase(img.getCode(),imageCode)){
            throw new ValidateCodeException("验证码不匹配");
        }
        sessionStrategy.removeAttribute(servletWebRequest,imageSesson);
    }
}
