package com.example.service;

import com.example.exception.UnauthorizedException;
import com.example.persistence.entitiy.User;
import com.example.persistence.mapper.UserMapper;
import com.example.util.AuthorizationUtil;
import com.example.util.RandomTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public User currentUser(HttpServletRequest request) {
        String token = AuthorizationUtil.getHeader(request);
        return userMapper.findByToken(token);
    }

    public User login(User user) throws UnauthorizedException {
        User userFound = userMapper.findByEmail(user.getEmail());
        if (Objects.isNull(userFound) || !passwordEncoder.matches(user.getPassword(), userFound.getPasswordDigest())) {
            throw new UnauthorizedException("パスワードかユーザ名が間違っています。");
        }
        return userFound;
    }

    public void updateToken(User user) throws NoSuchAlgorithmException {
        user.setToken(RandomTokenUtil.generateToken());
        userMapper.updateToken(user);
    }

    public User create(User user) throws NoSuchAlgorithmException {
        user.setToken(RandomTokenUtil.generateToken());
        user.setPasswordDigest(passwordEncoder.encode(user.getPassword()));
        userMapper.create(user);
        return user;
    }
}
