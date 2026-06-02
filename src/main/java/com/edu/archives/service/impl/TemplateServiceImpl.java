package com.edu.archives.service.impl;

import com.edu.archives.entity.ArchiveTemplate;
import com.edu.archives.common.Result;

public interface TemplateServiceImpl {
    Result saveTemplate(ArchiveTemplate template);
    Result updateTemplate(ArchiveTemplate template);
    Result deleteTemplate(Integer templateId);
    Result getTemplateById(Integer templateId);
    Result listAllTemplates();
    Result listTemplatesByDepartment(Integer departmentId);
    Result enableTemplate(Integer templateId);
    Result disableTemplate(Integer templateId);
}