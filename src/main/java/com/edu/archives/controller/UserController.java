package com.edu.archives.controller;

// 统一响应封装。
import com.edu.archives.common.Result;
// 登录请求 DTO。
import com.edu.archives.dto.LoginDto;
// 用户实体。
import com.edu.archives.entity.User;
// 用户服务接口。
import com.edu.archives.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

// 用户控制器。
// 调用关系：前端登录/用户管理请求 -> 本控制器 -> UserServiceImpl。
@RestController
@RequestMapping("/user")
public class UserController {

    // 注入用户服务。
    @Resource
    private UserService userService;

    // 登录接口。
    // 调用方：frontend/src/views/Login.vue。
    @PostMapping("/login")
    public Result login(@RequestBody LoginDto loginDto) {
        return userService.login(loginDto);
    }

    // 注册接口。
    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        return userService.register(user);
    }

    // 查询用户详情。
    @GetMapping("/{id}")
    public Result<User> getUserInfo(@PathVariable Integer id) {
        return userService.getUserInfo(id);
    }

    // 更新用户资料。
    @PutMapping
    public Result updateUserInfo(@RequestBody User user) {
        return userService.updateUserInfo(user);
    }

    // 修改密码。
    @PutMapping("/change-password")
    public Result changePassword(@RequestParam Integer userId,
                                 @RequestParam String oldPassword,
                                 @RequestParam String newPassword) {
        return userService.changePassword(userId, oldPassword, newPassword);
    }

    // 用户列表。
    @GetMapping("/list")
    public Result listUsers(@RequestParam(required = false) Integer departmentId,
                            @RequestParam(required = false) Integer roleId,
                            @RequestParam(defaultValue = "1") Integer page,
                            @RequestParam(defaultValue = "10") Integer size) {
        return userService.listUsers(departmentId, roleId, page, size);
    }

    // 删除用户（逻辑删除）。
    @DeleteMapping("/{id}")
    public Result deleteUser(@PathVariable Integer id) {
        return userService.deleteUser(id);
    }
}