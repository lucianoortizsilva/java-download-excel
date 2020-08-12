package com.lucianoortizsilva.download.excel.controller;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

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

	@RequestMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<Resource> download() {
		String filename = null;
		Resource resource = null;
		try {
			final File file = this.service.criarArquivo();
			if (Objects.isNull(file)) {
				return ResponseEntity.notFound().build();
			}
			Path path = Paths.get(file.getPath());
			resource = new UrlResource(path.toUri());
			filename = resource.getFilename();
		} catch (final Exception e) {
			return ResponseUtil.cathException(e);
		}
		return ResponseUtil.ok(resource, filename);
	}

}