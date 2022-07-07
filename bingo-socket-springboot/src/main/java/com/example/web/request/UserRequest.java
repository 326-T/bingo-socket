package com.example.web.request;

import com.example.persistence.entitiy.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserRequest {
    private String name;
    private String email;
    private String password;

    public User exportEntity() {
        return User.builder().name(name).email(email).password(password).build();
    }
}

