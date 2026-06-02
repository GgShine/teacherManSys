package com.edu.archives.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("t_archive_record")
public class ArchiveRecord {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer userId;              // 提交用户ID
    private Integer templateId;          // 使用的模板ID
    private String recordTitle;          // 记录标题
    private String contentData;          // 内容数据(JSON格式)
    private String filePaths;            // 附件路径(JSON格式)
    private Integer submitStatus;        // 提交状态 (0-草稿, 1-已提交, 2-审核中, 3-已驳回, 4-已归档)
    private Integer currentLevel;        // 当前处理层级 (1-教师, 2-教研室, 3-学院, 4-学校)
    private Integer nextApproverId;      // 下一级审批人ID
    private String reviewComments;       // 审核意见
    private String packagePath;          // 打包文件路径
    private Integer isPacked;            // 是否已打包 (0-否, 1-是)
    private LocalDateTime submitTime;    // 提交时间
    private LocalDateTime reviewTime;    // 审核时间
    private LocalDateTime archiveTime;   // 归档时间
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer deleted;             // 逻辑删除标识
}