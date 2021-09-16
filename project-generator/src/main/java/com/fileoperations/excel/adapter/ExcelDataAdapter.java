package com.fileoperations.excel.adapter;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;

public class ExcelDataAdapter {
	private static DataFormatter dataFormatter = new DataFormatter();

	public static List<String> adapt(Row row) {
		List<String> dataRow = new ArrayList<>();

		row.forEach(cell -> {
			String cellValue = dataFormatter.formatCellValue(cell);
			dataRow.add(cellValue);
		});
		return dataRow;
	}

}
