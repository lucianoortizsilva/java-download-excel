package com.lucianoortizsilva.download.excel.service;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springframework.stereotype.Service;

import com.lucianoortizsilva.download.excel.api.ExcelApi;
import com.lucianoortizsilva.download.excel.api.dto.Column;
import com.lucianoortizsilva.download.excel.api.dto.Format;
import com.lucianoortizsilva.download.excel.api.dto.Line;
import com.lucianoortizsilva.download.excel.model.Time;
import com.lucianoortizsilva.download.excel.repository.TimeRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TimeService {

	private static final String[] HEADERS = { "NOME", "MASCOTE", "ESTÁDIO", "ESTADUAIS", "FUNDAÇÃO", "PATRIMÔNIO" };
	private static final String ABA_NAME = "Times Do Brasil";
	private static final String FORMAT = ".xlsx";
	
	private TimeRepository repository;
	
	
	
	public File generateFile() throws IOException {
		File file = null;
		final List<Line> lines = this.generateLines();
		if (CollectionUtils.isNotEmpty(lines)) {
			file = ExcelApi.create(lines, HEADERS, FORMAT, ABA_NAME);
		}
		return file;
	}

	
	
	public String generateFileInBase64() throws IOException {
		String base64 = null;
		final List<Line> lines = this.generateLines();
		if (CollectionUtils.isNotEmpty(lines)) {
			base64 = ExcelApi.createBase64(lines, HEADERS, FORMAT, ABA_NAME);
		}
		return base64;
	}
	
	
	private List<Line> generateLines() {
		final List<Time> times = this.repository.findAll();
		final List<Line> lines = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(times)) {
			for (final Time time : times) {
				final Column column0 = new Column(0, 7000, time.getNome(),  Format.TEXT, HorizontalAlignment.LEFT);
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