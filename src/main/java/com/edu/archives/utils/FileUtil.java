package com.edu.archives.utils;

import java.io.File;

public class FileUtil {

	public static boolean ensureDirectory(String directoryPath) {
		File directory = new File(directoryPath);
		if (directory.exists()) {
			return directory.isDirectory();
		}
		return directory.mkdirs();
	}
}
