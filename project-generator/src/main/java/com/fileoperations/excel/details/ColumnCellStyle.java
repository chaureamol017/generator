package com.fileoperations.excel.details;

public class ColumnCellStyle {
	private boolean isBoldFont;
	private short fontHeight;
	private short colorIndex;

	private String dataFormat;

	public ColumnCellStyle(boolean isBoldFont, short fontHeight, short colorIndex, String dataFormat) {
		super();
		this.isBoldFont = isBoldFont;
		this.fontHeight = fontHeight;
		this.colorIndex = colorIndex;
		this.dataFormat = dataFormat;
	}

	public boolean isBoldFont() {
		return isBoldFont;
	}

	public short getFontHeight() {
		return fontHeight;
	}

	public short getColorIndex() {
		return colorIndex;
	}

	public String getDataFormat() {
		return dataFormat;
	}

	public void setBoldFont(boolean isBoldFont) {
		this.isBoldFont = isBoldFont;
	}

	public void setFontHeight(short fontHeight) {
		this.fontHeight = fontHeight;
	}

	public void setColorIndex(short colorIndex) {
		this.colorIndex = colorIndex;
	}

	public void setDataFormat(String dataFormat) {
		this.dataFormat = dataFormat;
	}
	
	
}
