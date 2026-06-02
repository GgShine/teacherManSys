package com.edu.archives.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.edu.archives.entity.ArchiveTemplate;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

// 模板数据访问层。
// 调用关系：TemplateServiceImpl -> 本 Mapper。
public interface TemplateMapper extends BaseMapper<ArchiveTemplate> {
    // 查询全部启用且未删除模板。
    @Select("SELECT * FROM t_archive_template WHERE status = 1 AND deleted = 0 ORDER BY create_time DESC")
    List<ArchiveTemplate> findAllActiveTemplates();

    // 按部门查询启用模板。
    @Select("SELECT * FROM t_archive_template WHERE department_id = #{departmentId} AND status = 1 AND deleted = 0 ORDER BY create_time DESC")
    List<ArchiveTemplate> findTemplatesByDepartment(@Param("departmentId") Integer departmentId);
}