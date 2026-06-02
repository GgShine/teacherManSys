package com.edu.archives.service;

import com.edu.archives.entity.User;
import com.edu.archives.dto.LoginDto;
import com.edu.archives.common.Result;

// 用户业务服务接口。
// 调用关系：UserController -> UserServiceImpl。
public interface UserService {
    // 登录校验并生成 token。
    Result login(LoginDto loginDto);
    // 注册新用户。
    Result register(User user);
    // 按用户 ID 查询信息。
    Result<User> getUserInfo(Integer userId);
    // 更新用户资料。
    Result updateUserInfo(User user);
    // 修改密码。
    Result changePassword(Integer userId, String oldPassword, String newPassword);
    // 查询用户列表（可按部门和角色筛选）。
    Result listUsers(Integer departmentId, Integer roleId, Integer page, Integer size);
    // 删除用户（逻辑删除）。
    Result deleteUser(Integer userId);
    // 按用户名查用户（供内部业务调用）。
    User findByUsername(String username);
}