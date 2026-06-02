package com.edu.archives.service;

import com.edu.archives.entity.User;
import com.edu.archives.dto.LoginDto;
import com.edu.archives.common.Result;

public interface UserService {
    Result login(LoginDto loginDto);
    Result register(User user);
    Result<User> getUserInfo(Integer userId);
    Result updateUserInfo(User user);
    Result changePassword(Integer userId, String oldPassword, String newPassword);
    Result listUsers(Integer departmentId, Integer roleId, Integer page, Integer size);
    Result deleteUser(Integer userId);
    User findByUsername(String username);
}