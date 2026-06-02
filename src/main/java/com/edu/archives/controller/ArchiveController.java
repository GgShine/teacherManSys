package com.edu.archives.controller;

// 统一返回体类型。
import com.edu.archives.common.Result;
// 档案提交请求 DTO。
import com.edu.archives.dto.ArchiveSubmitDto;
// 档案业务服务接口。
import com.edu.archives.service.ArchiveService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

// 档案业务控制器。
// 调用关系：前端 /archive* 请求 -> 本控制器 -> ArchiveServiceImpl。
@RestController
@RequestMapping("/archive")
public class ArchiveController {

	// 注入档案服务实现。
	@Resource
	private ArchiveService archiveService;

	// 提交档案接口。
	// 调用方：前端提交页（后续接入）将调用 POST /archive/submit。
	@PostMapping("/submit")
	public Result submit(@RequestParam Integer userId, @RequestBody ArchiveSubmitDto dto) {
		// 委派给服务层执行业务逻辑。
		return archiveService.submitArchive(userId, dto);
	}

	// 保存草稿接口。
	@PostMapping("/draft")
	public Result saveDraft(@RequestParam Integer userId, @RequestBody ArchiveSubmitDto dto) {
		return archiveService.saveDraft(userId, dto);
	}

	// 查询当前用户档案列表。
	@GetMapping("/my")
	public Result listMyArchives(@RequestParam Integer userId) {
		return archiveService.listMyArchives(userId);
	}

	// 查询待审核档案列表。
	@GetMapping("/pending")
	public Result listPendingArchives(@RequestParam Integer level,
									  @RequestParam(required = false) Integer approverId) {
		return archiveService.listPendingArchives(level, approverId);
	}

	// 审核档案接口。
	@PutMapping("/{id}/review")
	public Result reviewArchive(@PathVariable Integer id,
								@RequestParam Integer result,
								@RequestParam(required = false) String comments,
								@RequestParam(required = false) Integer reviewerId) {
		return archiveService.reviewArchive(id, result, comments, reviewerId);
	}

	// 手动归档接口。
	@PutMapping("/{id}/archive")
	public Result archiveRecord(@PathVariable Integer id) {
		return archiveService.archiveRecord(id);
	}

	// 查询档案详情。
	@GetMapping("/{id}")
	public Result getArchiveDetail(@PathVariable Integer id) {
		return archiveService.getArchiveDetail(id);
	}
}
