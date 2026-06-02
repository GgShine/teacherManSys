package com.edu.archives.service;

import com.edu.archives.common.Result;
import com.edu.archives.entity.ArchiveTemplate;

public interface TemplateService {
	Result saveTemplate(ArchiveTemplate template);

	Result updateTemplate(ArchiveTemplate template);

	Result deleteTemplate(Integer templateId);

	Result getTemplateById(Integer templateId);

	Result listAllTemplates();

	Result listTemplatesByDepartment(Integer departmentId);

	Result enableTemplate(Integer templateId);

	Result disableTemplate(Integer templateId);
}
