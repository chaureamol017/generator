package com.fileoperations.excel.details;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelDetails {

	private static final String ALL_SHEETS = "ALL";

	private String filePath;
	private String fileName;
	private boolean applySameStyleForAllSheets;
	private Map<String, SheetStyleDetails> sheetStyleDetails;
	private Map<String, SheetDataDetails> sheetDataDetails;

	public ExcelDetails(String filePath, String fileName) {
		super();
		this.filePath = filePath;
		this.fileName = fileName;
		applySameStyleForAllSheets = false;
		sheetStyleDetails = new HashMap<>();
		sheetDataDetails = new HashMap<>();
	}

	public String getFilePath() {
		return filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public List<String> getAllSheetNames() {
		List<String> allSheetNames = new ArrayList<>(sheetDataDetails.keySet());
		return allSheetNames;
	}

	public boolean isApplySameStyleForAllSheets() {
		return applySameStyleForAllSheets;
	}

	public void setApplySameStyleForAllSheets(boolean applySameStyleForAllSheets) {
		if (!this.applySameStyleForAllSheets) {
			this.applySameStyleForAllSheets = applySameStyleForAllSheets;
			if (applySameStyleForAllSheets) {
				applyStyleForAllIfNotApplied();
			}
		}
	}

	public void addSheet(String sheetName) {
		SheetDataDetails sheetData = SheetDataDetails.newInstance();
		sheetDataDetails.put(sheetName, sheetData);

		if (getSheetStyleDetailsForAll() == null) {
			SheetStyleDetails sheetStyle = SheetStyleDetails.newInstance();
			addStyleToSheet(sheetName, sheetStyle);
		}
	}

	public SheetStyleDetails getSheetStyleDetailsForAll() {
		return getSheetStyleDetails(ALL_SHEETS);
	}

	public SheetStyleDetails getSheetStyleDetails(String sheetName) {
		if (applySameStyleForAllSheets) {
			return sheetStyleDetails.get(ALL_SHEETS);
		} else if (sheetStyleDetails.containsKey(sheetName)) {
			return sheetStyleDetails.get(sheetName);
		} else {
			return null;
		}
	}

	public boolean addStyleToSheetHeader(String sheetName, ColumnCellStyle style) {
		return addStyleToSheet(style, sheetName, true);
	}

	public boolean addStyleToSheetData(String sheetName, ColumnCellStyle style) {
		return addStyleToSheet(style, sheetName, false);
	}

	public SheetDataDetails getSheetDataDetails(String sheetName) {
		return sheetDataDetails.get(sheetName);
	}

	public List<String> getSheetHeaders(String sheetName) {
		SheetDataDetails sheetData = sheetDataDetails.get(sheetName);
		if (sheetData != null) {
			return sheetData.getHeaders();
		}
		return null;
	}

	public List<List<String>> getSheetData(String sheetName) {
		SheetDataDetails sheetData = sheetDataDetails.get(sheetName);
		if (sheetData != null) {
			return sheetData.getData();
		}
		return null;
	}

	public void addHeader(String sheetName, String header) {
		SheetDataDetails sheetData = sheetDataDetails.get(sheetName);
		if (sheetData != null) {
			sheetData.addHeader(header);
		}
	}

	public void addData(String sheetName, String cell) {
		addData(sheetName, new ArrayList<>(Arrays.asList(cell)));
	}

	public void addData(String sheetName, List<String> row) {
		SheetDataDetails sheetData = sheetDataDetails.get(sheetName);
		if (sheetData != null) {
			sheetData.addData(row);
		}
	}

	private boolean addStyleToSheet(ColumnCellStyle style, String sheetName, boolean isStyelForHeader) {
		SheetStyleDetails sheetStyle = getSheetStyleDetails(sheetName);
		if (sheetStyle != null) {
			return isStyelForHeader ? sheetStyle.addCellStyleForHeader(style) : sheetStyle.addCellStyleForData(style);
		} else {
			return false;
		}
	}

	private void applyStyleForAllIfNotApplied() {
		if (sheetStyleDetails.get(ALL_SHEETS) == null) {
			SheetStyleDetails sheetStyle = null;
			for (Map.Entry<String, SheetStyleDetails> entry : sheetStyleDetails.entrySet()) {
				sheetStyle = entry.getValue();
				if (sheetStyle != null) {
					break;
				}
			}
			addStyleToSheet(ALL_SHEETS, sheetStyle);
		}
	}

	private void addStyleToSheet(String sheetName, SheetStyleDetails sheetStyle) {
		sheetStyleDetails.put(sheetName, sheetStyle);
	}

	

	public void writeToConsole() {
		List<String> allSheetNames = getAllSheetNames();
		System.out.println("File Name: " + getFileName());
		System.out.println("Number of sheets: " + allSheetNames.size());
		for (String sheetName : allSheetNames) {
			List<String> headers = getSheetHeaders(sheetName);

			System.out.println("");
			System.out.println("Sheet Name: " + sheetName);
			System.out.println("Sheet headers");
			System.out.println(headers);
			System.out.println("Sheet data");
//			System.out.println(excelDetails.getSheetData(sheetName));
			for (List<String> row : getSheetData(sheetName)) {
				System.out.println(row);
			}
		}
	}
}
