package com.mycomp.generator.curd.writer;

import java.io.FileWriter;
import java.io.IOException;

import com.mycomp.generator.curd.model.ClassDetails;

public final class WriteFileSteps {
	public static final WriteFileSteps INSTANCE = new WriteFileSteps();
	
	private static final FileDataWriter WRITER = FileDataWriter.INSTANCE;
	private static final FileCreator CREATOR = FileCreator.INSTANCE;
	
	private WriteFileSteps() {
	}
	public void writeFile(ClassDetails classDetails) {
		final String fileNameWithPath = CREATOR.getFileNameWithPath(classDetails);

		write(classDetails, fileNameWithPath);

	}
	private void write(ClassDetails classDetails, final String fileNameWithPath) {
		try (final FileWriter fileWriter = new FileWriter(fileNameWithPath);) {
			

			WRITER.writeDataIfPresent(fileWriter, classDetails.getClassPackage());
			WRITER.writeDataIfPresent(fileWriter, classDetails.getClassImports());
			WRITER.writeDataIfPresent(fileWriter, classDetails.getClassStart());
			
			WRITER.writeData(fileWriter, classDetails.getClassFields());
			WRITER.writeData(fileWriter, classDetails.getClassFunctions());

			WRITER.writeDataIfPresent(fileWriter, classDetails.getClassEnd());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
