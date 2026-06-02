package com.edu.archives.common;

public interface Constants {
    // 用户角色常量
    interface UserRole {
        int TEACHER = 1;           // 教师
        int DEPT_ADMIN = 2;        // 教研室管理员
        int COLLEGE_ADMIN = 3;     // 学院管理员
        int SCHOOL_ADMIN = 4;      // 学校管理员
    }

    // 档案状态常量
    interface ArchiveStatus {
        int DRAFT = 0;             // 草稿
        int SUBMITTED = 1;         // 已提交
        int REVIEWING = 2;         // 审核中
        int REJECTED = 3;          // 已驳回
        int ARCHIVED = 4;          // 已归档
    }

    // 审核层级常量
    interface ReviewLevel {
        int TEACHER_LEVEL = 1;     // 教师层级
        int DEPT_LEVEL = 2;        // 教研室层级
        int COLLEGE_LEVEL = 3;     // 学院层级
        int SCHOOL_LEVEL = 4;      // 学校层级
    }

    // 审核结果常量
    interface ReviewResult {
        int PASS = 1;              // 通过
        int REJECT = 2;            // 驳回
    }

    // 部门层级常量
    interface DeptLevel {
        int SCHOOL = 1;            // 学校
        int COLLEGE = 2;           // 学院
        int DEPT = 3;              // 教研室
    }
}