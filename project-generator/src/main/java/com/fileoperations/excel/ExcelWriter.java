package com.fileoperations.excel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.fileoperations.excel.adapter.ExcelStyleAdapter;
import com.fileoperations.excel.details.ExcelDetails;
import com.fileoperations.excel.details.SheetStyleDetails;

public class ExcelWriter {
	private static final String FILE_EXTENSION = ".xlsx";
	// Create a Workbook
	private Workbook workbook;

	/*
	 * CreationHelper helps us create instances of various things like DataFormat,
	 * Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way
	 */
	private CreationHelper createHelper;
	private ExcelDetails excelDetails;

	public ExcelWriter(ExcelDetails excelDetails) {
		super();
		this.excelDetails = excelDetails;
		workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` fileå
		createHelper = workbook.getCreationHelper();
	}

	public boolean writeFile() {
		boolean isSuccess = true;
		try {
			createSheets();
			writeWorkbook();
			closeWorkBook();

		} catch (IOException e) {
			isSuccess = false;
			e.printStackTrace();
		}
		return isSuccess;
	}

	private void createSheets() {
		List<String> allSheetNames = excelDetails.getAllSheetNames();
		for (String sheetName : allSheetNames) {
			// Create a Sheet
			Sheet sheet = workbook.createSheet(sheetName);

			createHeaderRow(sheet);
			createDataRow(sheet);
			resizeSheetColumns(sheet);
		}
	}

	private void createHeaderRow(Sheet sheet) {
		String sheetName = sheet.getSheetName();
		List<String> sheetHeaders = excelDetails.getSheetHeaders(sheetName);
		SheetStyleDetails sheetStyle = excelDetails.getSheetStyleDetails(sheetName);
		boolean isDefaultStyle = sheetStyle.isapplySameStyleForAllHeaderColumns();
		List<CellStyle> cellStyles = ExcelStyleAdapter.adapt(workbook, createHelper, sheetStyle.getHeaderCellStyle());

		// Create a Row
		Row headerRow = sheet.createRow(0);
		// Create cells
		createCellsAndAddDataToRow(headerRow, sheetHeaders, cellStyles, isDefaultStyle);
	}

	private void createDataRow(Sheet sheet) {
		String sheetName = sheet.getSheetName();
		List<List<String>> sheetData = excelDetails.getSheetData(sheetName);
		SheetStyleDetails sheetStyle = excelDetails.getSheetStyleDetails(sheetName);
		boolean isDefaultStyle = sheetStyle.isapplySameStyleForAllDataColumns();
		List<CellStyle> cellStyles = ExcelStyleAdapter.adapt(workbook, createHelper, sheetStyle.getDataCellStyle());
//		List<CellStyle> cellStyles = new ArrayList<CellStyle>();

		for (int i = 0; i < sheetData.size(); i++) {
			Row row = sheet.createRow(i + 1);
			List<String> rowData = sheetData.get(i);

			createCellsAndAddDataToRow(row, rowData, cellStyles, isDefaultStyle);
		}
	}

	private void createCellsAndAddDataToRow(Row headerRow, List<String> dataArr, List<CellStyle> cellStyles,
			boolean isDefaultStyle) {
		CellStyle cellStyle = getCellStyle(cellStyles, null, isDefaultStyle, -1);
		for (int i = 0; i < dataArr.size(); i++) {
			cellStyle = getCellStyle(cellStyles, null, isDefaultStyle, i);
			Cell cell = createCellAndSetStyle(headerRow, cellStyle, i);
			cell.setCellValue(dataArr.get(i));
		}
	}

	private CellStyle getCellStyle(List<CellStyle> cellStyles, CellStyle cellStyle, boolean isDefaultStyle, int index) {
		if (index < 0) {
			cellStyle = isDefaultStyle ? getCellStyelIfExist(cellStyles, 0) : null;
		} else {
			cellStyle = isDefaultStyle ? cellStyle : getCellStyelIfExist(cellStyles, index);
		}
		return cellStyle;
	}

	private CellStyle getCellStyelIfExist(List<CellStyle> cellStyles, int index) {
		return (index >= 0 && cellStyles.size() > index) ? cellStyles.get(index) : null;
	}

	private Cell createCellAndSetStyle(Row row, CellStyle cellStyle, int cellIndex) {
		Cell cell = createCell(row, cellIndex);
		cell.setCellStyle(cellStyle);
		return cell;
	}

	private Cell createCell(Row row, int cellIndex) {
		Cell cell = row.createCell(cellIndex);
		return cell;
	}

	private void resizeSheetColumns(Sheet sheet) {
		String sheetName = sheet.getSheetName();
		List<String> headers = excelDetails.getSheetHeaders(sheetName);

		// Resize all columns to fit the content size
		for (int i = 0; i < headers.size(); i++) {
			sheet.autoSizeColumn(i);
		}
	}

	private void writeWorkbook() throws FileNotFoundException, IOException {
		// Write the output to a file
		String fileName = excelDetails.getFilePath() + excelDetails.getFileName() + FILE_EXTENSION;
		FileOutputStream fileOut = new FileOutputStream(fileName);
		workbook.write(fileOut);
		fileOut.close();
	}

	private void closeWorkBook() throws IOException {
		// Closing the workbook
		workbook.close();
	}
}
