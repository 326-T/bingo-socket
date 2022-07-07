package com.example.persistence.mapper;

import com.example.persistence.entitiy.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM users WHERE token = #{token}")
    User findByToken(String token);

    @Select("SELECT * FROM users WHERE email = #{email}")
    User findByEmail(String email);

    @Update("UPDATE users SET token = #{token} WHERE id = #{id}")
    void updateToken(User user);

    @Insert("INSERT INTO users(name, email, password_digest, token)" +
            "VALUES(#{name}, #{email}, #{passwordDigest}, #{token})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void create(User user);
}
