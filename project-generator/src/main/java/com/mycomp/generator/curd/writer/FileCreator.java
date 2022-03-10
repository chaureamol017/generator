package com.mycomp.generator.curd.writer;

import java.io.File;

import com.mycomp.generator.curd.model.ClassDetails;

public class FileCreator {
public static final FileCreator INSTANCE = new FileCreator();
	
	private FileCreator() {
	}

	public String getFileNameWithPath(final ClassDetails details) {
		String filePath = details.getFilePath();
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdirs();
		}

		String fileNameWithPath = filePath + (filePath.endsWith(File.separator) ? "" : File.separator) + details.getFileNameWithFormat();
		return fileNameWithPath;
	}


}
