package com.fileoperations.excel.details;

import java.util.ArrayList;
import java.util.List;

public class SheetDataDetails {
	private List<String> headers;
	private List<List<String>> data;

	private SheetDataDetails() {
		super();
		headers = new ArrayList<>();
		data = new ArrayList<>();
	}
	
	public static SheetDataDetails newInstance() {
		return new SheetDataDetails();
	}

	public List<String> getHeaders() {
		return headers;
	}

	public String getHeaders(int index) {
		return (index <= headers.size()) ? headers.get(index) : null;
	}

	public int getHeaderCount() {
		return headers.size();
	}
	
	public List<List<String>> getData() {
		return data;
	}

	public List<String> getData(int index) {
		return (index <= data.size()) ? data.get(index) : null;
	}

	public int getDataCount() {
		return data.size();
	}

	public void addHeader(String header) {
		headers.add(header);
	}

	public void addData(List<String> row) {
		data.add(row);
	}

}
