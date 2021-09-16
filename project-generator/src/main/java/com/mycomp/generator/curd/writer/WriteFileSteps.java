package com.mycomp.generator.curd.writer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.mycomp.generator.curd.model.ClassDetails;

public final class WriteFileSteps {
	public static final WriteFileSteps INSTANCE = new WriteFileSteps();
	
	private WriteFileSteps() {
	}
	public void writeFile(ClassDetails classDetails) {
		try {
			String fileNameWithPath = createFileAndGetFilePathWithName(classDetails);
			final FileWriter writer = new FileWriter(fileNameWithPath);

			writeDataToFileIfPresent(writer, classDetails.getClassPackage());
			writeDataToFileIfPresent(writer, classDetails.getClassImports());
			writeDataToFileIfPresent(writer, classDetails.getClassStart());
			
			writeDataToFile(writer, classDetails.getClassFields());
			writeDataToFile(writer, classDetails.getClassFunctions());

			writeDataToFileIfPresent(writer, classDetails.getClassEnd());

			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private String createFileAndGetFilePathWithName(ClassDetails details) {
		String filePath = details.getFilePath();
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdirs();
		}

		String fileNameWithPath = filePath + ((filePath.endsWith(File.separator)) ? "" : File.separator)
				+ details.getFileNameWithFormat();
		return fileNameWithPath;
	}

	private void writeDataToFile(FileWriter writer, List<String> detailsList) throws IOException {
	for(String details : detailsList) {
		writeDataToFileIfPresent(writer, details);
	}
	}

	private void writeDataToFileIfPresent(FileWriter writer, String data) throws IOException {
		if (null != data)
			writer.write(data);
	}

}
