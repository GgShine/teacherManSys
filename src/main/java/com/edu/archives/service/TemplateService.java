package com.edu.archives.service;

import com.edu.archives.common.Result;
import com.edu.archives.entity.ArchiveTemplate;

// 模板业务服务接口。
// 调用关系：TemplateController -> TemplateServiceImpl。
public interface TemplateService {
	// 创建模板。
	Result saveTemplate(ArchiveTemplate template);

	// 更新模板。
	Result updateTemplate(ArchiveTemplate template);

	// 删除模板（逻辑删除）。
	Result deleteTemplate(Integer templateId);

	// 查询模板详情。
	Result getTemplateById(Integer templateId);

	// 查询全部模板。
	Result listAllTemplates();

	// 按部门查询模板。
	Result listTemplatesByDepartment(Integer departmentId);

	// 启用模板。
	Result enableTemplate(Integer templateId);

	// 停用模板。
	Result disableTemplate(Integer templateId);
}
