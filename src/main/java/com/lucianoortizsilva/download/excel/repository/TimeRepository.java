package com.lucianoortizsilva.download.excel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucianoortizsilva.download.excel.model.Time;

public interface TimeRepository extends JpaRepository<Time, Long> {}