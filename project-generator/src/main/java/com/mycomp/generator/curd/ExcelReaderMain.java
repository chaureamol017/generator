package com.mycomp.generator.curd;

import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.fileoperations.excel.ExcelReader;
import com.fileoperations.excel.details.ExcelDetails;

public class ExcelReaderMain {

	public static void main(String[] args) {
		String fileName = "testFile";
		try {
			ExcelReader excelReader = new ExcelReader("", fileName);
			ExcelDetails excelDetails = excelReader.readFile();
			
			excelDetails.writeToConsole();
		} catch (InvalidFormatException | IOException e) {
			e.printStackTrace();
		}
	}
}
