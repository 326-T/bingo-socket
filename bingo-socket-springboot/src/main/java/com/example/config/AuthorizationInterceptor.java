package com.example.config;

import com.example.annotation.NonAuth;
import com.example.exception.UnauthorizedException;
import com.example.persistence.entitiy.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Objects;

@Configuration
public class AuthorizationInterceptor implements HandlerInterceptor {

    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod))
            return true;
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        NonAuth nonAuthAnnotation = AnnotationUtils.findAnnotation(method, NonAuth.class);
        if (Objects.nonNull(nonAuthAnnotation))
            return true;
        User user = userService.currentUser(request);
        if (Objects.isNull(user))
            throw new UnauthorizedException("認証されていません。");
        request.setAttribute("currentUser", user);
        return true;
    }
}
