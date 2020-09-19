package com.lucianoortizsilva.download.excel.controller;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lucianoortizsilva.download.excel.service.TimeService;
import com.lucianoortizsilva.download.excel.util.ResponseUtil;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping(value = "times")
public class TimeController {

	private TimeService service;
	
	
	
	@RequestMapping(value = "/download/excel", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<Resource> download() {
		String filename = null;
		Resource resource = null;
		try {
			final File file = this.service.generateFile();
			if (Objects.isNull(file)) {
				return ResponseEntity.notFound().build();
			}
			final Path path = Paths.get(file.getPath());
			resource = new UrlResource(path.toUri());
			filename = resource.getFilename();
		} catch (final Exception e) {
			return ResponseUtil.cathException(e);
		}
		return ResponseUtil.ok(resource, filename);
	}
	
	
	
	@RequestMapping(value = "/download/base64", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> base64() {
		String base64 = null;
		try {
			base64 = this.service.generateFileInBase64();
			if (StringUtils.isEmpty(base64)) {
				return ResponseEntity.notFound().build();
			}
		} catch (final Exception e) {
			ResponseUtil.cathException(e);
		}
		return ResponseEntity.ok(base64);
	}

}