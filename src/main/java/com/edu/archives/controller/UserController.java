package com.edu.archives.controller;

import com.edu.archives.common.Result;
import com.edu.archives.dto.LoginDto;
import com.edu.archives.entity.User;
import com.edu.archives.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody LoginDto loginDto) {
        return userService.login(loginDto);
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        return userService.register(user);
    }

    @GetMapping("/{id}")
    public Result<User> getUserInfo(@PathVariable Integer id) {
        return userService.getUserInfo(id);
    }

    @PutMapping
    public Result updateUserInfo(@RequestBody User user) {
        return userService.updateUserInfo(user);
    }

    @PutMapping("/change-password")
    public Result changePassword(@RequestParam Integer userId,
                                 @RequestParam String oldPassword,
                                 @RequestParam String newPassword) {
        return userService.changePassword(userId, oldPassword, newPassword);
    }

    @GetMapping("/list")
    public Result listUsers(@RequestParam(required = false) Integer departmentId,
                            @RequestParam(required = false) Integer roleId,
                            @RequestParam(defaultValue = "1") Integer page,
                            @RequestParam(defaultValue = "10") Integer size) {
        return userService.listUsers(departmentId, roleId, page, size);
    }

    @DeleteMapping("/{id}")
    public Result deleteUser(@PathVariable Integer id) {
        return userService.deleteUser(id);
    }
}