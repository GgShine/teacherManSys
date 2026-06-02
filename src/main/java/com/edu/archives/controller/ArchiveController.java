package com.edu.archives.controller;

import com.edu.archives.common.Result;
import com.edu.archives.dto.ArchiveSubmitDto;
import com.edu.archives.service.ArchiveService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/archive")
public class ArchiveController {

	@Resource
	private ArchiveService archiveService;

	@PostMapping("/submit")
	public Result submit(@RequestParam Integer userId, @RequestBody ArchiveSubmitDto dto) {
		return archiveService.submitArchive(userId, dto);
	}

	@PostMapping("/draft")
	public Result saveDraft(@RequestParam Integer userId, @RequestBody ArchiveSubmitDto dto) {
		return archiveService.saveDraft(userId, dto);
	}

	@GetMapping("/my")
	public Result listMyArchives(@RequestParam Integer userId) {
		return archiveService.listMyArchives(userId);
	}

	@GetMapping("/pending")
	public Result listPendingArchives(@RequestParam Integer level,
									  @RequestParam(required = false) Integer approverId) {
		return archiveService.listPendingArchives(level, approverId);
	}

	@PutMapping("/{id}/review")
	public Result reviewArchive(@PathVariable Integer id,
								@RequestParam Integer result,
								@RequestParam(required = false) String comments,
								@RequestParam(required = false) Integer reviewerId) {
		return archiveService.reviewArchive(id, result, comments, reviewerId);
	}

	@PutMapping("/{id}/archive")
	public Result archiveRecord(@PathVariable Integer id) {
		return archiveService.archiveRecord(id);
	}

	@GetMapping("/{id}")
	public Result getArchiveDetail(@PathVariable Integer id) {
		return archiveService.getArchiveDetail(id);
	}
}
