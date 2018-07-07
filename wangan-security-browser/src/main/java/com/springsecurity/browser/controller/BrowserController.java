package com.springsecurity.browser.controller;

import com.springsecurity.browser.support.SimpleResponse;
import com.springsecurity.core.properties.SecurityProperties;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class BrowserController {

    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 自定义登录调转，如果是.html请求,则继续进行springsecurity后续用户验证。
     * 否则直接提示 "请在正确登录页面进行登录"
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("/brower/login")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public SimpleResponse loginRedirect(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            String redirectUrl = savedRequest.getRedirectUrl();
            if (StringUtils.endsWithIgnoreCase(redirectUrl, ".html")) {
                redirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getLoginUrl());
            }
        }
        return new SimpleResponse("请在正确登录页面进行登录");
    }

}
