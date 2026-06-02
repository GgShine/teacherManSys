package com.edu.archives.service.impl;

import com.edu.archives.common.Constants;
import com.edu.archives.common.Result;
import com.edu.archives.dto.ArchiveSubmitDto;
import com.edu.archives.entity.ArchiveRecord;
import com.edu.archives.mapper.ArchiveMapper;
import com.edu.archives.service.ArchiveService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Service
public class ArchiveServiceImpl implements ArchiveService {

	@Resource
	private ArchiveMapper archiveMapper;

	@Override
	public Result submitArchive(Integer userId, ArchiveSubmitDto dto) {
		ArchiveRecord record = buildRecord(userId, dto, Constants.ArchiveStatus.SUBMITTED);
		record.setSubmitTime(LocalDateTime.now());
		archiveMapper.insert(record);
		return Result.success(record);
	}

	@Override
	public Result saveDraft(Integer userId, ArchiveSubmitDto dto) {
		ArchiveRecord record = buildRecord(userId, dto, Constants.ArchiveStatus.DRAFT);
		archiveMapper.insert(record);
		return Result.success(record);
	}

	@Override
	public Result listMyArchives(Integer userId) {
		return Result.success(archiveMapper.findRecordsByUserId(userId));
	}

	@Override
	public Result listPendingArchives(Integer level, Integer approverId) {
		if (approverId != null) {
			return Result.success(archiveMapper.findRecordsForApproval(approverId));
		}
		return Result.success(archiveMapper.findRecordsByLevel(level));
	}

	@Override
	public Result reviewArchive(Integer recordId, Integer reviewResult, String comments, Integer reviewerId) {
		ArchiveRecord record = archiveMapper.selectById(recordId);
		if (record == null || record.getDeleted() != null && record.getDeleted() == 1) {
			return Result.error("档案记录不存在");
		}

		record.setReviewComments(comments);
		record.setReviewTime(LocalDateTime.now());

		if (reviewResult != null && reviewResult == Constants.ReviewResult.REJECT) {
			record.setSubmitStatus(Constants.ArchiveStatus.REJECTED);
			record.setNextApproverId(null);
		} else {
			Integer currentLevel = record.getCurrentLevel() == null ? 1 : record.getCurrentLevel();
			if (currentLevel >= Constants.ReviewLevel.SCHOOL_LEVEL) {
				record.setSubmitStatus(Constants.ArchiveStatus.ARCHIVED);
				record.setArchiveTime(LocalDateTime.now());
			} else {
				record.setSubmitStatus(Constants.ArchiveStatus.REVIEWING);
				record.setCurrentLevel(currentLevel + 1);
			}
		}

		record.setUpdateTime(LocalDateTime.now());
		archiveMapper.updateById(record);
		return Result.success(record);
	}

	@Override
	public Result archiveRecord(Integer recordId) {
		ArchiveRecord record = archiveMapper.selectById(recordId);
		if (record == null) {
			return Result.error("档案记录不存在");
		}
		record.setSubmitStatus(Constants.ArchiveStatus.ARCHIVED);
		record.setArchiveTime(LocalDateTime.now());
		record.setUpdateTime(LocalDateTime.now());
		archiveMapper.updateById(record);
		return Result.success(record);
	}

	@Override
	public Result getArchiveDetail(Integer recordId) {
		return Result.success(archiveMapper.selectById(recordId));
	}

	private ArchiveRecord buildRecord(Integer userId, ArchiveSubmitDto dto, Integer status) {
		ArchiveRecord record = new ArchiveRecord();
		record.setUserId(userId);
		record.setTemplateId(dto.getTemplateId());
		record.setRecordTitle(dto.getRecordTitle());
		record.setContentData(dto.getContentData());
		record.setFilePaths(dto.getFilePaths());
		record.setSubmitStatus(status);
		record.setCurrentLevel(Constants.ReviewLevel.TEACHER_LEVEL);
		record.setIsPacked(0);
		record.setDeleted(0);
		record.setCreateTime(LocalDateTime.now());
		record.setUpdateTime(LocalDateTime.now());
		return record;
	}
}
