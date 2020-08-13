package com.lucianoortizsilva.download.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.poi.ss.usermodel.Workbook;

import lombok.experimental.UtilityClass;

@UtilityClass
public class FileUtil {

	
	public static File generateFile(final Workbook workbook, final String format) throws IOException {
		final String filename = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
		final File file = File.createTempFile(filename, format);
		final FileOutputStream fos = new FileOutputStream(file);
		workbook.write(fos);
		workbook.close();
		return file;
	}
	
}