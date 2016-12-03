package org.home.accounts.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthorizeInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller)
            throws Exception {

        String uri = request.getRequestURI();
        if(uri.endsWith("loginForm") || uri.endsWith("executeLogin") || uri.contains("resources")) {
            return true;
        }

        if(request.getSession().getAttribute("userauth")!=null) {
            return true;
        } else {
            response.sendRedirect("loginForm");
            return false;
        }

    }
}
