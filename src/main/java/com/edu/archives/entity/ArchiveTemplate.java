package com.edu.archives.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

// 档案模板实体。
// 调用关系：TemplateMapper 进行数据库读写；TemplateServiceImpl 执行业务逻辑。
@Data
@TableName("t_archive_template")
public class ArchiveTemplate {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String templateName;    // 模板名称
    private String templateCode;    // 模板编码
    private String description;     // 模板描述
    private String fields;          // 字段配置(JSON格式)
    private String attachmentRules; // 附件规则(JSON格式)
    private Integer status;         // 状态 (0-停用, 1-启用)
    private Integer departmentId;   // 适用部门ID
    private Integer creatorId;      // 创建者ID
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer deleted;        // 逻辑删除标识
}