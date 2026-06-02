package com.edu.archives.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.edu.archives.entity.ArchiveTemplate;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TemplateMapper extends BaseMapper<ArchiveTemplate> {
    @Select("SELECT * FROM t_archive_template WHERE status = 1 AND deleted = 0 ORDER BY create_time DESC")
    List<ArchiveTemplate> findAllActiveTemplates();

    @Select("SELECT * FROM t_archive_template WHERE department_id = #{departmentId} AND status = 1 AND deleted = 0 ORDER BY create_time DESC")
    List<ArchiveTemplate> findTemplatesByDepartment(@Param("departmentId") Integer departmentId);
}