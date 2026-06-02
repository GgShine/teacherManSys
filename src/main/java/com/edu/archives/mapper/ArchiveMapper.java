package com.edu.archives.mapper;

// MyBatis-Plus 基础 Mapper。
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
// 档案实体。
import com.edu.archives.entity.ArchiveRecord;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

// 档案数据访问层。
// 调用关系：ArchiveServiceImpl -> 本 Mapper。
public interface ArchiveMapper extends BaseMapper<ArchiveRecord> {
    // 按提交用户查询档案。
    @Select("SELECT * FROM t_archive_record WHERE user_id = #{userId} AND deleted = 0 ORDER BY create_time DESC")
    List<ArchiveRecord> findRecordsByUserId(@Param("userId") Integer userId);

    // 按当前处理层级查询待处理档案。
    @Select("SELECT * FROM t_archive_record WHERE current_level = #{level} AND submit_status IN (1,2) AND deleted = 0 ORDER BY create_time DESC")
    List<ArchiveRecord> findRecordsByLevel(@Param("level") Integer level);

    // 按审批人查询待审核档案。
    @Select("SELECT * FROM t_archive_record WHERE next_approver_id = #{approverId} AND submit_status IN (2) AND deleted = 0 ORDER BY create_time DESC")
    List<ArchiveRecord> findRecordsForApproval(@Param("approverId") Integer approverId);

    // 查询近期已归档记录（最多 100 条）。
    @Select("SELECT * FROM t_archive_record WHERE submit_status = 4 AND deleted = 0 ORDER BY archive_time DESC LIMIT 100")
    List<ArchiveRecord> findRecentArchivedRecords();
}