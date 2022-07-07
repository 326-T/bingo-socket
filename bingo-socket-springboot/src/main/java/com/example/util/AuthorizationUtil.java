package com.example.util;

import com.example.persistence.entitiy.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.stream.Stream;

public class AuthorizationUtil {

    public static String getHeader(HttpServletRequest request) {
        return Stream.of(request.getHeader("Authorization").split("Bearer ")).skip(1).reduce("", (result, str) -> result + str);
    }

    public static User getUser(HttpServletRequest request) {
        return (User) request.getAttribute("currentUser");
    }

    public static void setHeaderSuccess(HttpServletResponse response) {
        response.setHeader("WWW-Authenticate","Bearer realm=\"\"");
    }

    public static void setHeaderUnAuthorized(HttpServletResponse response) {
        response.setHeader("WWW-Authenticate","Bearer realm=\"token_required\"");
    }
}
