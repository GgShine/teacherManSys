package com.edu.archives.dto;

// Lombok：自动生成 getter/setter/toString。
import lombok.Data;

/**
 * 登录请求数据对象。
 * 调用关系：UserController#login(@RequestBody LoginDto) 接收此对象。
 */
@Data
public class LoginDto {

    // 用户名：对应前端 loginForm.username。
    private String username;

    // 密码：对应前端 loginForm.password。
    private String password;
}