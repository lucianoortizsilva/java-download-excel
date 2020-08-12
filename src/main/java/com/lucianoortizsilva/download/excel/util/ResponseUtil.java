package com.lucianoortizsilva.download.excel.util;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class ResponseUtil {
	
	
	
	public static ResponseEntity<Resource> ok(final Resource resource, final String filename) {
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType("text/xlsx"))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + filename + "\"")
				.build();
	}
	
	
	
	public ResponseEntity<Resource> cathException(final Exception e) {
		log.error(e.getMessage(), e);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
}