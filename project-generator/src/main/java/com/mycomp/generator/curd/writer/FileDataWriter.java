package com.mycomp.generator.curd.writer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileDataWriter {
public static final FileDataWriter INSTANCE = new FileDataWriter();
	
	private FileDataWriter() {
	}

	public void writeData(FileWriter writer, List<String> detailsList) throws IOException {
		for(String details : detailsList) {
			writeDataIfPresent(writer, details);
		}
	}

	public void writeDataIfPresent(FileWriter writer, String data) throws IOException {
		if (null != data)
			writer.write(data);
	}
}
