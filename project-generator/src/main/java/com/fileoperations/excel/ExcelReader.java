package com.fileoperations.excel;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.fileoperations.excel.adapter.ExcelDataAdapter;
import com.fileoperations.excel.details.ExcelDetails;

public class ExcelReader {
	private static final String FILE_EXTENSION = ".xlsx";

	private Workbook workbook;
	private String filePath;
	private String fileName;

	public ExcelReader(String filePath, String fileName) {
		super();
		this.filePath = filePath;
		this.fileName = fileName;
	}

	public ExcelDetails readFile() throws IOException, InvalidFormatException {
		ExcelDetails excelDetails = new ExcelDetails(filePath, fileName);
		createWorkBook();

		int numberOfSheets = workbook.getNumberOfSheets();
		for (int i = 0; i < numberOfSheets; i++) {
			Sheet sheet = workbook.getSheetAt(i);

			excelDetails.addSheet(sheet.getSheetName());
			readSheetHeader(excelDetails, sheet);
			readSheetData(excelDetails, sheet);
		}

		return excelDetails;
	}

	private void createWorkBook() throws IOException, InvalidFormatException {
		workbook = WorkbookFactory.create(new File(filePath + fileName + FILE_EXTENSION));
	}

	private void readSheetHeader(final ExcelDetails excelDetails, Sheet sheet) {
		int firstRowNum = sheet.getFirstRowNum();
		String sheetName = sheet.getSheetName();
		List<String> headers = ExcelDataAdapter.adapt(sheet.getRow(firstRowNum));
		
		headers.forEach(cellValue -> {
			excelDetails.addHeader(sheetName, cellValue);
		});
	}
	private void readSheetData(ExcelDetails excelDetails, Sheet sheet) {
		String sheetName = sheet.getSheetName();
		int firstRowNum = sheet.getFirstRowNum();
		int lastRowNum = sheet.getLastRowNum();
		for (int j = firstRowNum + 1; j < lastRowNum; j++) {
			List<String> dataRow = ExcelDataAdapter.adapt(sheet.getRow(j));
			excelDetails.addData(sheetName, dataRow);
			
		}
	}
}