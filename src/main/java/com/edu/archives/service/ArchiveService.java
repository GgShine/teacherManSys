package com.edu.archives.service;

import com.edu.archives.common.Result;
import com.edu.archives.dto.ArchiveSubmitDto;

public interface ArchiveService {
	Result submitArchive(Integer userId, ArchiveSubmitDto dto);

	Result saveDraft(Integer userId, ArchiveSubmitDto dto);

	Result listMyArchives(Integer userId);

	Result listPendingArchives(Integer level, Integer approverId);

	Result reviewArchive(Integer recordId, Integer reviewResult, String comments, Integer reviewerId);

	Result archiveRecord(Integer recordId);

	Result getArchiveDetail(Integer recordId);
}
