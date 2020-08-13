package com.lucianoortizsilva.download.excel;



import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Excel {
	
	
	
	public static Workbook createWorkbook(final String format) {
		if (".xls".equalsIgnoreCase(format)) {
			return new HSSFWorkbook();
		} else {
			return new XSSFWorkbook();
		}
	}
	
	
	
	public static Sheet createSheet(final Workbook workbook, final String name) {
		final Sheet sheet = workbook.createSheet();
		final int indexAba = 0;
		workbook.setSheetName(indexAba, name);
		return sheet;
	}
	
	
	
	public static void createHeaders(final Sheet sheet, final String[] headers) {
		final int indiceRow = 0;
		final Row row = sheet.createRow(indiceRow);
		final CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
		cellStyle.setBorderBottom(BorderStyle.THIN);
		cellStyle.setBorderLeft(BorderStyle.THIN);
		cellStyle.setBorderRight(BorderStyle.THIN);
		cellStyle.setBorderTop(BorderStyle.THIN);
		cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		final Font font = sheet.getWorkbook().createFont();
		font.setBold(Boolean.TRUE);
		for (int i = 0; i < headers.length; i++) {
			final Cell cell = row.createCell(i);
			cellStyle.setFont(font);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(headers[i]);
			sheet.autoSizeColumn(i);
		}
	}
	
	
	
	public static void createData(final Sheet sheet, final List<Line> lines) {
		int countLine = 1;
		for (final Line line : lines) {
			final Row row = sheet.createRow(countLine);
			for (final Column column : line.getColumns()) {
				final Cell cell = row.createCell(column.getIndice());
				column.getFormat().formatCell(sheet, cell, column);
			}
			countLine++;
		}
	}
	
}