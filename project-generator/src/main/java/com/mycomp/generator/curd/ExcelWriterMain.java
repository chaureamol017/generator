package com.mycomp.generator.curd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fileoperations.excel.ExcelWriter;
import com.fileoperations.excel.details.ExcelDetails;

public class ExcelWriterMain {
	private static final String PATH = "/Users/chaurea/Downloads/";

	public static void main(String[] args) {

		ExcelDetails excelDetails = getExcelDetails();
		ExcelWriter excelWriter = new ExcelWriter(excelDetails);
		excelWriter.writeFile();
	}

	static ExcelDetails getExcelDetails() {
		String fileName = "testFile";
//		ExcelDetails excelDetails = new ExcelDetails("", fileName);
		ExcelDetails excelDetails = new ExcelDetails(PATH, fileName);
		createAndAddSheetDetails(excelDetails, fileName);
		createAndAddSheetDetails(excelDetails, (fileName + "1"));

		return excelDetails;
	}

	private static void createAndAddSheetDetails(ExcelDetails excelDetails, String sheetName) {
		excelDetails.addSheet(sheetName);

		excelDetails.addHeader(sheetName, "Name");
		excelDetails.addHeader(sheetName, "Email");
		excelDetails.addHeader(sheetName, "Date Of Birth");
		excelDetails.addHeader(sheetName, "Salary");
		List<String> ll = new ArrayList<>(
				Arrays.asList("Rajeev Singh", "rajeev@example.com", "1992, 7, 21", "1200000"));
		excelDetails.addData(sheetName, ll);
		ll = new ArrayList<>(Arrays.asList("Thomas cook", "thomas@example.com", "1965, 10, 15", "1500000"));
		excelDetails.addData(sheetName, ll);
		ll = new ArrayList<>(Arrays.asList("Steve Maiden", "steve@example.com", "1987, 4, 18", "1800000"));
		excelDetails.addData(sheetName, ll);
	}

}
