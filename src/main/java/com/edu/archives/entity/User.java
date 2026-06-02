package com.edu.archives.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("t_user")
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String username;     // 用户名
    private String password;     // 密码
    private String realName;     // 真实姓名
    private String email;        // 邮箱
    private String phone;        // 电话
    private Integer departmentId; // 部门ID
    private Integer roleId;      // 角色ID (1-教师, 2-教研室管理员, 3-学院管理员, 4-学校管理员)
    private Integer status;      // 状态 (0-禁用, 1-启用)
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer deleted;     // 逻辑删除标识
}