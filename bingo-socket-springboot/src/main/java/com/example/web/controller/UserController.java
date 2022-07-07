package com.example.web.controller;

import com.example.annotation.NonAuth;
import com.example.exception.UnauthorizedException;
import com.example.persistence.entitiy.User;
import com.example.service.UserService;
import com.example.util.AuthorizationUtil;
import com.example.web.request.UserRequest;
import com.example.web.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api/v1/auth")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/current_user")
    public UserResponse currentUser(HttpServletRequest request) {
        User user = AuthorizationUtil.getUser(request);
        return UserResponse.builder().id(user.getId()).name(user.getName()).email(user.getEmail()).token(user.getToken()).build();
    }

    @NonAuth
    @PostMapping("/login")
    public UserResponse login(@RequestBody UserRequest userRequest) throws UnauthorizedException {
        User user = userService.login(userRequest.exportEntity());
        return UserResponse.builder().id(user.getId()).name(user.getName()).email(user.getEmail()).token(user.getToken()).build();
    }

    @PostMapping("/logout")
    public ResponseEntity logout(HttpServletRequest request) throws NoSuchAlgorithmException {
        User user = AuthorizationUtil.getUser(request);
        userService.updateToken(user);
        return ResponseEntity.ok().build();
    }

    @NonAuth
    @PutMapping("/signup")
    public UserResponse signup(@RequestBody UserRequest userRequest) throws NoSuchAlgorithmException {
        User user = userService.create(userRequest.exportEntity());
        return UserResponse.builder().id(user.getId()).name(user.getName()).email(user.getEmail()).token(user.getToken()).build();
    }

    @GetMapping
    public String index(HttpServletRequest request) {
        System.out.println("index");
        return "people";
    }
}
