package com.edu.archives.controller;

import com.edu.archives.common.Result;
import com.edu.archives.entity.ArchiveTemplate;
import com.edu.archives.service.TemplateService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/template")
public class TemplateController {

	@Resource
	private TemplateService templateService;

	@PostMapping
	public Result createTemplate(@RequestBody ArchiveTemplate template) {
		return templateService.saveTemplate(template);
	}

	@PutMapping
	public Result updateTemplate(@RequestBody ArchiveTemplate template) {
		return templateService.updateTemplate(template);
	}

	@DeleteMapping("/{id}")
	public Result deleteTemplate(@PathVariable Integer id) {
		return templateService.deleteTemplate(id);
	}

	@GetMapping("/{id}")
	public Result getTemplate(@PathVariable Integer id) {
		return templateService.getTemplateById(id);
	}

	@GetMapping("/list")
	public Result listTemplates(@RequestParam(required = false) Integer departmentId) {
		if (departmentId == null) {
			return templateService.listAllTemplates();
		}
		return templateService.listTemplatesByDepartment(departmentId);
	}

	@PutMapping("/{id}/enable")
	public Result enableTemplate(@PathVariable Integer id) {
		return templateService.enableTemplate(id);
	}

	@PutMapping("/{id}/disable")
	public Result disableTemplate(@PathVariable Integer id) {
		return templateService.disableTemplate(id);
	}
}
