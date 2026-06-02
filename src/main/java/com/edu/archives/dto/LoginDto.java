package com.edu.archives.dto;

import lombok.Data; // 如果你引入了 Lombok，可以用这个注解自动生成 getter/setter
// import java.io.Serializable; // 如果没引入 Lombok，建议实现序列化接口

/**
 * 登录数据传输对象 (DTO)
 * 用于接收前端传来的登录参数
 */
@Data // Lombok 注解，自动生成 get/set/toString 等方法
public class LoginDto {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}