package com.edu.archives.service.impl;

// 常量定义（状态、层级、审核结果）。
import com.edu.archives.common.Constants;
// 统一响应对象。
import com.edu.archives.common.Result;
// 提交/草稿 DTO。
import com.edu.archives.dto.ArchiveSubmitDto;
// 档案实体。
import com.edu.archives.entity.ArchiveRecord;
// 档案 Mapper。
import com.edu.archives.mapper.ArchiveMapper;
// 档案服务接口。
import com.edu.archives.service.ArchiveService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

// 档案服务实现。
// 调用关系：ArchiveController -> 本类 -> ArchiveMapper。
@Service
public class ArchiveServiceImpl implements ArchiveService {

	// 注入档案数据访问对象。
	@Resource
	private ArchiveMapper archiveMapper;

	// 提交档案：创建记录并标记为已提交。
	@Override
	public Result submitArchive(Integer userId, ArchiveSubmitDto dto) {
		// 先构建基础记录对象。
		ArchiveRecord record = buildRecord(userId, dto, Constants.ArchiveStatus.SUBMITTED);
		// 设置提交时间。
		record.setSubmitTime(LocalDateTime.now());
		// 入库。
		archiveMapper.insert(record);
		// 返回创建后的记录。
		return Result.success(record);
	}

	// 保存草稿：状态是 DRAFT。
	@Override
	public Result saveDraft(Integer userId, ArchiveSubmitDto dto) {
		ArchiveRecord record = buildRecord(userId, dto, Constants.ArchiveStatus.DRAFT);
		archiveMapper.insert(record);
		return Result.success(record);
	}

	// 查询当前用户档案列表。
	@Override
	public Result listMyArchives(Integer userId) {
		return Result.success(archiveMapper.findRecordsByUserId(userId));
	}

	// 查询待处理档案。
	// approverId 有值时优先按审批人查询，否则按层级查询。
	@Override
	public Result listPendingArchives(Integer level, Integer approverId) {
		if (approverId != null) {
			return Result.success(archiveMapper.findRecordsForApproval(approverId));
		}
		return Result.success(archiveMapper.findRecordsByLevel(level));
	}

	// 审核档案：支持通过与驳回。
	@Override
	public Result reviewArchive(Integer recordId, Integer reviewResult, String comments, Integer reviewerId) {
		// 先查记录。
		ArchiveRecord record = archiveMapper.selectById(recordId);
		// 不存在或已删除则直接失败。
		if (record == null || record.getDeleted() != null && record.getDeleted() == 1) {
			return Result.error("档案记录不存在");
		}

		// 写入审核意见和时间。
		record.setReviewComments(comments);
		record.setReviewTime(LocalDateTime.now());

		// 驳回分支。
		if (reviewResult != null && reviewResult == Constants.ReviewResult.REJECT) {
			record.setSubmitStatus(Constants.ArchiveStatus.REJECTED);
			record.setNextApproverId(null);
		} else {
			// 通过分支：层级推进或直接归档。
			Integer currentLevel = record.getCurrentLevel() == null ? 1 : record.getCurrentLevel();
			if (currentLevel >= Constants.ReviewLevel.SCHOOL_LEVEL) {
				// 到最高层后归档。
				record.setSubmitStatus(Constants.ArchiveStatus.ARCHIVED);
				record.setArchiveTime(LocalDateTime.now());
			} else {
				// 进入下一层审核。
				record.setSubmitStatus(Constants.ArchiveStatus.REVIEWING);
				record.setCurrentLevel(currentLevel + 1);
			}
		}

		// 更新时间并落库。
		record.setUpdateTime(LocalDateTime.now());
		archiveMapper.updateById(record);
		return Result.success(record);
	}

	// 强制归档。
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

	// 查询详情。
	@Override
	public Result getArchiveDetail(Integer recordId) {
		return Result.success(archiveMapper.selectById(recordId));
	}

	// 私有构建方法：用于 submit/saveDraft 两条调用路径复用。
	private ArchiveRecord buildRecord(Integer userId, ArchiveSubmitDto dto, Integer status) {
		ArchiveRecord record = new ArchiveRecord();
		// 归属用户。
		record.setUserId(userId);
		// 模板信息。
		record.setTemplateId(dto.getTemplateId());
		// 基础内容。
		record.setRecordTitle(dto.getRecordTitle());
		record.setContentData(dto.getContentData());
		record.setFilePaths(dto.getFilePaths());
		// 状态与流程字段。
		record.setSubmitStatus(status);
		record.setCurrentLevel(Constants.ReviewLevel.TEACHER_LEVEL);
		record.setIsPacked(0);
		record.setDeleted(0);
		// 审计字段。
		record.setCreateTime(LocalDateTime.now());
		record.setUpdateTime(LocalDateTime.now());
		return record;
	}
}
