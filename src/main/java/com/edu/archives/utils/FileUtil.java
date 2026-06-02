package com.edu.archives.utils;

import java.io.File;

// 文件工具类。
// 调用关系：后续上传/打包功能可调用 ensureDirectory 创建目录。
public class FileUtil {

	// 确保目录存在。
	// 若目录不存在则创建，返回创建或存在校验结果。
	public static boolean ensureDirectory(String directoryPath) {
		// 根据路径创建 File 对象。
		File directory = new File(directoryPath);
		// 已存在时只需确认是目录。
		if (directory.exists()) {
			return directory.isDirectory();
		}
		// 不存在时递归创建目录。
		return directory.mkdirs();
	}
}
