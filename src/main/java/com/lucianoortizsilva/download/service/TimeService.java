package com.lucianoortizsilva.download.service;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import com.lucianoortizsilva.download.excel.Column;
import com.lucianoortizsilva.download.excel.Excel;
import com.lucianoortizsilva.download.excel.Format;
import com.lucianoortizsilva.download.excel.Line;
import com.lucianoortizsilva.download.model.Time;
import com.lucianoortizsilva.download.repository.TimeRepository;
import com.lucianoortizsilva.download.util.FileUtil;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TimeService {

	private static final String[] HEADERS = { "NOME", "MASCOTE", "ESTÁDIO", "ESTADUAIS", "FUNDAÇÃO", "PATRIMONIO" };
	private static final String ABA_NAME = "Times Do Brasil";
	private static final String FORMAT = ".xlsx";
	private TimeRepository repository;
	
	
	
	public File createReport() throws IOException {
		File file = null;
		final List<Line> lines = this.generateLines();
		if (CollectionUtils.isNotEmpty(lines)) {
			file = this.generateFile(lines);
		}
		return file;
	}

	
	
	private File generateFile(final List<Line> lines) throws IOException {
		final Workbook workbook = Excel.createWorkbook(FORMAT);
		final Sheet sheet = Excel.createSheet(workbook, ABA_NAME);
		Excel.createHeaders(sheet, HEADERS);
		Excel.createData(sheet, lines);
		return FileUtil.generateFile(workbook, FORMAT);
	}

	
	
	private List<Line> generateLines() {
		final List<Time> times = this.repository.findAll();
		final List<Line> lines = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(times)) {
			for (final Time time : times) {
				final Column column0 = new Column(0, 7000, time.getNome(), Format.TEXT, HorizontalAlignment.LEFT);
				final Column column1 = new Column(1, 4000, time.getMascote(), Format.TEXT, HorizontalAlignment.LEFT);
				final Column column2 = new Column(2, 4000, time.getEstadio(), Format.TEXT, HorizontalAlignment.CENTER);
				final Column column3 = new Column(3, 4000, time.getTitulos().toString(), Format.NUMERIC, HorizontalAlignment.CENTER);
				final Column column4 = new Column(4, 5000, time.getFundacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), Format.DATE, HorizontalAlignment.CENTER);
				final Column column5 = new Column(5, 5000, time.getPatrimonio().toString(), Format.MONETARY, HorizontalAlignment.RIGHT);
				final Line line = new Line(column0, column1, column2, column3, column4, column5);
				lines.add(line);
			}
		}
		return lines;
	}
	
}