package com.fileoperations.excel.adapter;

import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;

import com.fileoperations.excel.details.ColumnCellStyle;

public class ExcelFontStyleAdapter {
	public static final ExcelFontStyleAdapter INSTANCE = new ExcelFontStyleAdapter();
	
	private ExcelFontStyleAdapter() {
	}

	public Font adapt(final Workbook workbook, final ColumnCellStyle columnCellStyle) {
		boolean isBold = columnCellStyle.isBoldFont();
		short colourIndex = columnCellStyle.getColorIndex();
		short fontHeight = columnCellStyle.getFontHeight();

		if (isBold || colourIndex > 0 || fontHeight > 0) {
			Font headerFont = workbook.createFont();

			if (isBold) {
				headerFont.setBold(isBold);
			}
			if (colourIndex > 0) {
				headerFont.setColor(colourIndex);
			}
			if (fontHeight > 0) {
				headerFont.setFontHeightInPoints(fontHeight);
			}

			return headerFont;
		}
		return null;
	}
}
