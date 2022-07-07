package com.example.persistence.entitiy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User {
    private Integer id;
    private String name;
    private String email;
    private String password;
    private String passwordDigest;
    private String token;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
