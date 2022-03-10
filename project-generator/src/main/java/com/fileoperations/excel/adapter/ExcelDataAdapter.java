package com.fileoperations.excel.adapter;

import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;

public class ExcelDataAdapter {
	private static final DataFormatter DATA_FORMATTER = new DataFormatter();

	public static List<String> adapt(Row row) {
		final List<String> columns = StreamSupport.stream(Spliterators.spliteratorUnknownSize(row.cellIterator(), Spliterator.ORDERED), false)
                .map(DATA_FORMATTER::formatCellValue)
                .collect(Collectors.toList());

		return columns;
	}

}
