package com.edu.archives.dto;

import lombok.Data;

// 档案提交数据对象。
// 调用关系：ArchiveController 的 submit/saveDraft 接口接收此对象。
@Data
public class ArchiveSubmitDto {
	// 模板 ID。
	private Integer templateId;
	// 档案标题。
	private String recordTitle;
	// 业务内容（通常是 JSON 文本）。
	private String contentData;
	// 附件路径集合（通常是 JSON 文本）。
	private String filePaths;
}
