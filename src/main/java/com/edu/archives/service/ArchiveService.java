package com.edu.archives.service;

import com.edu.archives.common.Result;
import com.edu.archives.dto.ArchiveSubmitDto;

// 档案业务服务接口。
// 调用关系：ArchiveController 调用本接口，具体由 ArchiveServiceImpl 实现。
public interface ArchiveService {
	// 提交档案（进入提交流程）。
	Result submitArchive(Integer userId, ArchiveSubmitDto dto);

	// 保存草稿（不进入审核流程）。
	Result saveDraft(Integer userId, ArchiveSubmitDto dto);

	// 查询某个用户自己的档案。
	Result listMyArchives(Integer userId);

	// 查询待审核档案。
	Result listPendingArchives(Integer level, Integer approverId);

	// 审核档案。
	Result reviewArchive(Integer recordId, Integer reviewResult, String comments, Integer reviewerId);

	// 强制归档。
	Result archiveRecord(Integer recordId);

	// 查询档案详情。
	Result getArchiveDetail(Integer recordId);
}
