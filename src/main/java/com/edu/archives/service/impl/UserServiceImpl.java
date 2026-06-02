package com.edu.archives.service.impl;

// 字符串工具类。
import cn.hutool.core.util.StrUtil;
// 查询条件构造器。
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
// 统一响应对象。
import com.edu.archives.common.Result;
// 登录参数 DTO。
import com.edu.archives.dto.LoginDto;
// 用户实体。
import com.edu.archives.entity.User;
// 用户 Mapper。
import com.edu.archives.mapper.UserMapper;
// 用户服务接口。
import com.edu.archives.service.UserService;
// JWT 工具类。
import com.edu.archives.utils.JwtUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

// 用户服务实现。
// 调用关系：UserController -> 本类 -> UserMapper / JwtUtil。
@Service
public class UserServiceImpl implements UserService {

    // 注入用户 Mapper。
    @Resource
    private UserMapper userMapper;

    // 登录。
    @Override
    public Result login(LoginDto loginDto) {
        // 参数非空校验。
        if (StrUtil.isBlank(loginDto.getUsername()) || StrUtil.isBlank(loginDto.getPassword())) {
            return Result.error("用户名或密码不能为空");
        }

        // 构建登录查询条件。
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", loginDto.getUsername())
                .eq("password", loginDto.getPassword())
                .eq("deleted", 0);

        // 查询用户。
        User user = userMapper.selectOne(wrapper);
        if (user == null) {
            return Result.error("用户名或密码错误");
        }

        // 状态校验：1 为启用。
        if (user.getStatus() != 1) {
            return Result.error("账户已被禁用");
        }

        // 生成JWT token
        String token = JwtUtil.generateToken(user.getId().toString(), user.getUsername());

        // 组装返回数据。
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("user", user);

        return Result.success(map);
    }

    // 注册。
    @Override
    public Result register(User user) {
        // 检查用户名是否已存在
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", user.getUsername()).eq("deleted", 0);
        User existingUser = userMapper.selectOne(wrapper);
        if (existingUser != null) {
            return Result.error("用户名已存在");
        }

        user.setPassword("123456"); // 默认密码
        user.setStatus(1);
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        user.setDeleted(0);

        userMapper.insert(user);
        return Result.success();
    }

    // 查询用户信息。
    @Override
    public Result<User> getUserInfo(Integer userId) {
        User user = userMapper.selectById(userId);
        if (user == null) {
            return Result.error("用户不存在");
        }
        return Result.success(user);
    }

    // 更新用户信息。
    @Override
    public Result updateUserInfo(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.updateById(user);
        return Result.success();
    }

    // 修改密码。
    @Override
    public Result changePassword(Integer userId, String oldPassword, String newPassword) {
        User user = userMapper.selectById(userId);
        // 校验旧密码。
        if (!user.getPassword().equals(oldPassword)) {
            return Result.error("原密码错误");
        }

        user.setPassword(newPassword);
        user.setUpdateTime(LocalDateTime.now());
        userMapper.updateById(user);
        return Result.success();
    }

    // 查询用户列表（当前实现为简化版，不做物理分页）。
    @Override
    public Result listUsers(Integer departmentId, Integer roleId, Integer page, Integer size) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq(departmentId != null, "department_id", departmentId)
                .eq(roleId != null, "role_id", roleId)
                .eq("deleted", 0)
                .orderByDesc("create_time");

        // 这里简化处理，实际应该使用分页插件
        return Result.success(userMapper.selectList(wrapper));
    }

    // 删除用户（逻辑删除）。
    @Override
    public Result deleteUser(Integer userId) {
        User user = new User();
        user.setId(userId);
        user.setDeleted(1); // 逻辑删除
        userMapper.updateById(user);
        return Result.success();
    }

    // 按用户名查询。
    // 调用方：可供后续鉴权拦截器或其他业务服务使用。
    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }
}