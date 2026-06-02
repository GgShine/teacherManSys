package com.edu.archives.dto;

import lombok.Data;

@Data
public class ArchiveSubmitDto {
	private Integer templateId;
	private String recordTitle;
	private String contentData;
	private String filePaths;
}
