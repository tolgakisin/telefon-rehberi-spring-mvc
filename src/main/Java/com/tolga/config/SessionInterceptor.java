package com.tolga.config;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionInterceptor extends HandlerInterceptorAdapter {

    private static Logger logger = LoggerFactory.getLogger(SessionFactory.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if (session != null) {
            String username = (String) session.getAttribute("username");
            logger.info("Username = " + session.getAttribute("username"));
            if (username == null) {
                logger.error("Session içindeki username null geldi");
                return false;
            }
        } else {
            logger.error("Session boş");
            return false;

        }
        logger.info("GİRİŞ YAPILDI");
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
