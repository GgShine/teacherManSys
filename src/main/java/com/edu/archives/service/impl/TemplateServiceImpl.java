package com.edu.archives.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.edu.archives.common.Result;
import com.edu.archives.entity.ArchiveTemplate;
import com.edu.archives.mapper.TemplateMapper;
import com.edu.archives.service.TemplateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class TemplateServiceImpl implements TemplateService {

    @Resource
    private TemplateMapper templateMapper;

    @Override
    public Result saveTemplate(ArchiveTemplate template) {
        template.setStatus(template.getStatus() == null ? 1 : template.getStatus());
        template.setDeleted(0);
        template.setCreateTime(LocalDateTime.now());
        template.setUpdateTime(LocalDateTime.now());
        templateMapper.insert(template);
        return Result.success();
    }

    @Override
    public Result updateTemplate(ArchiveTemplate template) {
        template.setUpdateTime(LocalDateTime.now());
        templateMapper.updateById(template);
        return Result.success();
    }

    @Override
    public Result deleteTemplate(Integer templateId) {
        ArchiveTemplate template = new ArchiveTemplate();
        template.setId(templateId);
        template.setDeleted(1);
        template.setUpdateTime(LocalDateTime.now());
        templateMapper.updateById(template);
        return Result.success();
    }

    @Override
    public Result getTemplateById(Integer templateId) {
        return Result.success(templateMapper.selectById(templateId));
    }

    @Override
    public Result listAllTemplates() {
        QueryWrapper<ArchiveTemplate> wrapper = new QueryWrapper<>();
        wrapper.eq("deleted", 0).orderByDesc("create_time");
        return Result.success(templateMapper.selectList(wrapper));
    }

    @Override
    public Result listTemplatesByDepartment(Integer departmentId) {
        return Result.success(templateMapper.findTemplatesByDepartment(departmentId));
    }

    @Override
    public Result enableTemplate(Integer templateId) {
        return setStatus(templateId, 1);
    }

    @Override
    public Result disableTemplate(Integer templateId) {
        return setStatus(templateId, 0);
    }

    private Result setStatus(Integer templateId, Integer status) {
        ArchiveTemplate template = new ArchiveTemplate();
        template.setId(templateId);
        template.setStatus(status);
        template.setUpdateTime(LocalDateTime.now());
        templateMapper.updateById(template);
        Map<String, Object> result = new HashMap<>();
        result.put("templateId", templateId);
        result.put("status", status);
        return Result.success(result);
    }
}