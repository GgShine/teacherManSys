package com.edu.archives.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

// 审核记录实体。
// 调用场景：档案审核流程可把每次审批行为落到该表（当前业务尚未完全接入）。
@Data
@TableName("t_review_record")
public class ReviewRecord {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer recordId;           // 档案记录ID
    private Integer reviewerId;         // 审核人ID
    private Integer reviewLevel;        // 审核层级 (1-教研室, 2-学院, 3-学校)
    private Integer reviewResult;       // 审核结果 (1-通过, 2-驳回)
    private String comments;            // 审核意见
    private LocalDateTime reviewTime;   // 审核时间
    private String filePath;            // 审核后的文件路径
    private Integer isArchived;         // 是否已归档 (0-否, 1-是)
    private LocalDateTime createTime;
    private Integer deleted;            // 逻辑删除标识
}