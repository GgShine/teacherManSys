package com.edu.archives.controller;

// 通用返回对象。
import com.edu.archives.common.Result;
// 模板实体。
import com.edu.archives.entity.ArchiveTemplate;
// 模板服务接口。
import com.edu.archives.service.TemplateService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

// 模板管理控制器。
// 调用关系：前端模板管理页面 -> 本控制器 -> TemplateServiceImpl。
@RestController
@RequestMapping("/template")
public class TemplateController {

	// 注入模板服务。
	@Resource
	private TemplateService templateService;

	// 新建模板。
	@PostMapping
	public Result createTemplate(@RequestBody ArchiveTemplate template) {
		return templateService.saveTemplate(template);
	}

	// 更新模板。
	@PutMapping
	public Result updateTemplate(@RequestBody ArchiveTemplate template) {
		return templateService.updateTemplate(template);
	}

	// 删除模板（逻辑删除）。
	@DeleteMapping("/{id}")
	public Result deleteTemplate(@PathVariable Integer id) {
		return templateService.deleteTemplate(id);
	}

	// 查询模板详情。
	@GetMapping("/{id}")
	public Result getTemplate(@PathVariable Integer id) {
		return templateService.getTemplateById(id);
	}

	// 查询模板列表：可按部门筛选。
	@GetMapping("/list")
	public Result listTemplates(@RequestParam(required = false) Integer departmentId) {
		// 无部门参数时返回全部模板。
		if (departmentId == null) {
			return templateService.listAllTemplates();
		}
		// 有部门参数时按部门过滤。
		return templateService.listTemplatesByDepartment(departmentId);
	}

	// 启用模板。
	@PutMapping("/{id}/enable")
	public Result enableTemplate(@PathVariable Integer id) {
		return templateService.enableTemplate(id);
	}

	// 停用模板。
	@PutMapping("/{id}/disable")
	public Result disableTemplate(@PathVariable Integer id) {
		return templateService.disableTemplate(id);
	}
}
