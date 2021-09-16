package com.fileoperations.excel.adapter;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;

import com.fileoperations.excel.details.ColumnCellStyle;

public class ExcelStyleAdapter {

	public static List<CellStyle> adapt(Workbook workbook, CreationHelper createHelper,
			List<ColumnCellStyle> columnCellStyles) {
		List<CellStyle> cellStyles = new ArrayList<>();

		if (columnCellStyles != null) {
			for (ColumnCellStyle columnCellStyle : columnCellStyles) {
				CellStyle cellStyle;

				if (columnCellStyle != null) {
					cellStyle = workbook.createCellStyle();
					boolean isBold = columnCellStyle.isBoldFont();
					short colourIndex = columnCellStyle.getColorIndex();
					short fontHeight = columnCellStyle.getFontHeight();
					String dataFormat = columnCellStyle.getDataFormat();

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

						cellStyle.setFont(headerFont);
					}
					if (dataFormat != null && dataFormat.length() > 0) {
						short format = createHelper.createDataFormat().getFormat(dataFormat);
						if (format > 0) {
							cellStyle.setDataFormat(format);
						}
					}
				} else {
					cellStyle = null;
				}
				cellStyles.add(cellStyle);
			}
		}

		return cellStyles;
	}

}
