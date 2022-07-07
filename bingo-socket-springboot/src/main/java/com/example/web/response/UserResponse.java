package com.example.web.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class UserResponse {
    private Integer id;
    private String name;
    private String email;
    private String token;
}
