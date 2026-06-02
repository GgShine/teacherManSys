package com.edu.archives.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.edu.archives.entity.ArchiveRecord;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ArchiveMapper extends BaseMapper<ArchiveRecord> {
    @Select("SELECT * FROM t_archive_record WHERE user_id = #{userId} AND deleted = 0 ORDER BY create_time DESC")
    List<ArchiveRecord> findRecordsByUserId(@Param("userId") Integer userId);

    @Select("SELECT * FROM t_archive_record WHERE current_level = #{level} AND submit_status IN (1,2) AND deleted = 0 ORDER BY create_time DESC")
    List<ArchiveRecord> findRecordsByLevel(@Param("level") Integer level);

    @Select("SELECT * FROM t_archive_record WHERE next_approver_id = #{approverId} AND submit_status IN (2) AND deleted = 0 ORDER BY create_time DESC")
    List<ArchiveRecord> findRecordsForApproval(@Param("approverId") Integer approverId);

    @Select("SELECT * FROM t_archive_record WHERE submit_status = 4 AND deleted = 0 ORDER BY archive_time DESC LIMIT 100")
    List<ArchiveRecord> findRecentArchivedRecords();
}