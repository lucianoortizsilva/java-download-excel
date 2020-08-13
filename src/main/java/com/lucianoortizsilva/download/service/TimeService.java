package com.lucianoortizsilva.download.service;

import java.io.File;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import com.lucianoortizsilva.download.excel.Excel;
import com.lucianoortizsilva.download.util.FileUtil;

@Service
public class TimeService {

	private static final String[] HEADERS = { "NOME", "APELIDO", "MASCOTE", "ESTÁDIO", "FUNDAÇÃO" };
	private static final String ABA_NAME = "Times Do Brasil";
	private static final String FORMAT = ".xlsx";

	public File createReport() throws IOException {
		File file = null;
		final Workbook workbook = Excel.createWorkbook(FORMAT);
		final Sheet sheet = Excel.createSheet(workbook, ABA_NAME);
		Excel.createHeaders(sheet, HEADERS);
		file = FileUtil.generateFile(workbook, FORMAT);
		return file;
	}

}