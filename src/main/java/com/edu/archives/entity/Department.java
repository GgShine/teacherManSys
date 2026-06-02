// src/main/java/com/edu/archives/entity/Department.java
package com.edu.archives.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("t_department")
public class Department {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;         // 部门名称
    private Integer parentId;    // 父部门ID (0表示顶级部门)
    private Integer level;       // 部门层级 (1-学校, 2-学院, 3-教研室)
    private String description;  // 描述
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer deleted;     // 逻辑删除标识
}