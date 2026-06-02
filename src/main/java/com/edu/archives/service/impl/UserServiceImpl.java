package com.edu.archives.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.edu.archives.common.Result;
import com.edu.archives.dto.LoginDto;
import com.edu.archives.entity.User;
import com.edu.archives.mapper.UserMapper;
import com.edu.archives.service.UserService;
import com.edu.archives.utils.JwtUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public Result login(LoginDto loginDto) {
        if (StrUtil.isBlank(loginDto.getUsername()) || StrUtil.isBlank(loginDto.getPassword())) {
            return Result.error("用户名或密码不能为空");
        }

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", loginDto.getUsername())
                .eq("password", loginDto.getPassword())
                .eq("deleted", 0);

        User user = userMapper.selectOne(wrapper);
        if (user == null) {
            return Result.error("用户名或密码错误");
        }

        if (user.getStatus() != 1) {
            return Result.error("账户已被禁用");
        }

        // 生成JWT token
        String token = JwtUtil.generateToken(user.getId().toString(), user.getUsername());

        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("user", user);

        return Result.success(map);
    }

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

    @Override
    public Result<User> getUserInfo(Integer userId) {
        User user = userMapper.selectById(userId);
        if (user == null) {
            return Result.error("用户不存在");
        }
        return Result.success(user);
    }

    @Override
    public Result updateUserInfo(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.updateById(user);
        return Result.success();
    }

    @Override
    public Result changePassword(Integer userId, String oldPassword, String newPassword) {
        User user = userMapper.selectById(userId);
        if (!user.getPassword().equals(oldPassword)) {
            return Result.error("原密码错误");
        }

        user.setPassword(newPassword);
        user.setUpdateTime(LocalDateTime.now());
        userMapper.updateById(user);
        return Result.success();
    }

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

    @Override
    public Result deleteUser(Integer userId) {
        User user = new User();
        user.setId(userId);
        user.setDeleted(1); // 逻辑删除
        userMapper.updateById(user);
        return Result.success();
    }

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }
}