package com.fileoperations.excel.adapter;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;

import com.fileoperations.excel.details.ColumnCellStyle;
import com.google.common.collect.Lists;

public class ExcelStyleAdapter {

	public static List<CellStyle> adapt(Workbook workbook, CreationHelper createHelper, List<ColumnCellStyle> columnCellStyles) {

		if (columnCellStyles != null) {
			List<CellStyle> cellStyles = columnCellStyles.stream()
					.map(columnCellStyle -> columnCellStyle == null ? null : getCellStyle(workbook, createHelper, columnCellStyle))
					.collect(Collectors.toList());
			return cellStyles;
		}

		return Lists.newArrayList();
	}

	private static CellStyle getCellStyle(Workbook workbook, CreationHelper createHelper, ColumnCellStyle columnCellStyle) {
		CellStyle cellStyle = workbook.createCellStyle();
		setFont(workbook, columnCellStyle, cellStyle);
		setDataFormat(createHelper, columnCellStyle, cellStyle);
		return cellStyle;
	}

	private static void setFont(Workbook workbook, ColumnCellStyle columnCellStyle, CellStyle cellStyle) {
		Font font = ExcelFontStyleAdapter.INSTANCE.adapt(workbook, columnCellStyle);
		if (font != null) {
			cellStyle.setFont(font);
		}
	}

	private static void setDataFormat(CreationHelper createHelper, ColumnCellStyle columnCellStyle, CellStyle cellStyle) {
		String dataFormat = columnCellStyle.getDataFormat();

		if (dataFormat != null && dataFormat.length() > 0) {
			short format = createHelper.createDataFormat().getFormat(dataFormat);
			if (format > 0) {
				cellStyle.setDataFormat(format);
			}
		}
	}

}
