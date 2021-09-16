package com.fileoperations.excel.details;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SheetStyleDetails {

	private static final int SHEET_STYLE_KEY_FOR_HEADER = 0;
	private static final int SHEET_STYLE_KEY_FOR_DATA = 1;

	private boolean applySameStyleForAllHeaderColumns;
	private boolean applySameStyleForAllDataColumns;
	private Map<Integer, List<ColumnCellStyle>> sheetStyle;

	private SheetStyleDetails() {
		super();
		applySameStyleForAllHeaderColumns = false;
		applySameStyleForAllDataColumns = false;
		sheetStyle = new HashMap<>();
	}

	public static SheetStyleDetails newInstance() {
		return new SheetStyleDetails();
	}

	public boolean isapplySameStyleForAllHeaderColumns() {
		return applySameStyleForAllHeaderColumns;
	}

	public void setapplySameStyleForAllHeaderColumns(boolean applySameStyleForAllHeaderColumns) {
		if (!this.applySameStyleForAllHeaderColumns) {
			this.applySameStyleForAllHeaderColumns = applySameStyleForAllHeaderColumns;
			if (applySameStyleForAllHeaderColumns) {
				trimStyleForAllColumns(SHEET_STYLE_KEY_FOR_HEADER);
			}
		}
	}

	public boolean isapplySameStyleForAllDataColumns() {
		return applySameStyleForAllDataColumns;
	}

	public void setapplySameStyleForAllDataColumns(boolean applySameStyleForAllDataColumns) {
		if (!this.applySameStyleForAllDataColumns) {
			this.applySameStyleForAllDataColumns = applySameStyleForAllDataColumns;
			if (applySameStyleForAllDataColumns) {
				trimStyleForAllColumns(SHEET_STYLE_KEY_FOR_DATA);
			}
		}
	}

	public List<ColumnCellStyle> getHeaderCellStyle() {
		return getCellStyle(SHEET_STYLE_KEY_FOR_HEADER);
	}

	public ColumnCellStyle getHeaderCellStyleAt(int columnIndex) {
		List<ColumnCellStyle> styles = getHeaderCellStyle();
		if (applySameStyleForAllHeaderColumns) {
			return getHeaderCellStyleAt(styles, 0);
		} else {
			return getHeaderCellStyleAt(styles, columnIndex);
		}
	}

	public List<ColumnCellStyle> getDataCellStyle() {
		return getCellStyle(SHEET_STYLE_KEY_FOR_DATA);
	}

	public ColumnCellStyle getDataCellStyleAt(int columnIndex) {
		List<ColumnCellStyle> styles = getDataCellStyle();
		if (applySameStyleForAllHeaderColumns) {
			return getHeaderCellStyleAt(styles, 0);
		} else {
			return getHeaderCellStyleAt(styles, columnIndex);
		}
	}
	
	private ColumnCellStyle getHeaderCellStyleAt(List<ColumnCellStyle> styles, int columnIndex) {
		if (styles != null && styles.size() < columnIndex + 1) {
			return styles.get(columnIndex);
		}
		return null;
	}

	public boolean addCellStyleForHeader(ColumnCellStyle style) {
		return addCellStyle(SHEET_STYLE_KEY_FOR_HEADER, style);
	}

	public boolean addCellStyleForData(ColumnCellStyle style) {
		return addCellStyle(SHEET_STYLE_KEY_FOR_DATA, style);
	}

	private void trimStyleForAllColumns(int sheetStyleKey) {
		List<ColumnCellStyle> styles = getCellStyle(sheetStyleKey);
		if (styles != null && styles.size() > 1) {
			ColumnCellStyle style = styles.get(0);
			styles.clear();

			addCellStyle(sheetStyleKey, style);
		}
	}

	private boolean addCellStyle(int sheetStyleKey, ColumnCellStyle style) {
		List<ColumnCellStyle> styles = getCellStyle(sheetStyleKey);

		switch (sheetStyleKey) {
		case SHEET_STYLE_KEY_FOR_HEADER:
			if (applySameStyleForAllHeaderColumns && styles != null && styles.size() > 1) {
				return false;
			}
			break;
		case SHEET_STYLE_KEY_FOR_DATA:
			if (applySameStyleForAllDataColumns && styles != null && styles.size() > 1) {
				return false;
			}
			break;
		}

		if (styles == null) {
			styles = new ArrayList<ColumnCellStyle>();
			sheetStyle.put(sheetStyleKey, styles);
		}
		styles.add(style);

		return true;
	}

	private List<ColumnCellStyle> getCellStyle(int sheetStyleKey) {
		return sheetStyle.get(sheetStyleKey);
	}
}