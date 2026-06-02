package com.edu.archives.service.impl;

// 条件构造器。
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
// 统一响应对象。
import com.edu.archives.common.Result;
// 模板实体。
import com.edu.archives.entity.ArchiveTemplate;
// 模板数据访问对象。
import com.edu.archives.mapper.TemplateMapper;
// 模板服务接口。
import com.edu.archives.service.TemplateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

// 模板服务实现。
// 调用关系：TemplateController -> 本类 -> TemplateMapper。
@Service
public class TemplateServiceImpl implements TemplateService {

    // 注入模板 Mapper。
    @Resource
    private TemplateMapper templateMapper;

    // 保存模板。
    @Override
    public Result saveTemplate(ArchiveTemplate template) {
        // 若未传状态，默认启用。
        template.setStatus(template.getStatus() == null ? 1 : template.getStatus());
        // 逻辑删除标记默认 0。
        template.setDeleted(0);
        // 写入创建与更新时间。
        template.setCreateTime(LocalDateTime.now());
        template.setUpdateTime(LocalDateTime.now());
        // 入库。
        templateMapper.insert(template);
        return Result.success();
    }

    // 更新模板。
    @Override
    public Result updateTemplate(ArchiveTemplate template) {
        // 更新审计时间。
        template.setUpdateTime(LocalDateTime.now());
        templateMapper.updateById(template);
        return Result.success();
    }

    // 删除模板（逻辑删除）。
    @Override
    public Result deleteTemplate(Integer templateId) {
        // 构造只带关键字段的更新对象。
        ArchiveTemplate template = new ArchiveTemplate();
        template.setId(templateId);
        template.setDeleted(1);
        template.setUpdateTime(LocalDateTime.now());
        templateMapper.updateById(template);
        return Result.success();
    }

    // 查询模板详情。
    @Override
    public Result getTemplateById(Integer templateId) {
        return Result.success(templateMapper.selectById(templateId));
    }

    // 查询全部模板（排除已删除）。
    @Override
    public Result listAllTemplates() {
        QueryWrapper<ArchiveTemplate> wrapper = new QueryWrapper<>();
        wrapper.eq("deleted", 0).orderByDesc("create_time");
        return Result.success(templateMapper.selectList(wrapper));
    }

    // 按部门查询模板。
    @Override
    public Result listTemplatesByDepartment(Integer departmentId) {
        return Result.success(templateMapper.findTemplatesByDepartment(departmentId));
    }

    // 启用模板。
    @Override
    public Result enableTemplate(Integer templateId) {
        return setStatus(templateId, 1);
    }

    // 停用模板。
    @Override
    public Result disableTemplate(Integer templateId) {
        return setStatus(templateId, 0);
    }

    // 状态切换通用方法。
    // 调用方：enableTemplate / disableTemplate。
    private Result setStatus(Integer templateId, Integer status) {
        ArchiveTemplate template = new ArchiveTemplate();
        template.setId(templateId);
        template.setStatus(status);
        template.setUpdateTime(LocalDateTime.now());
        templateMapper.updateById(template);
        // 返回状态变更结果。
        Map<String, Object> result = new HashMap<>();
        result.put("templateId", templateId);
        result.put("status", status);
        return Result.success(result);
    }
}