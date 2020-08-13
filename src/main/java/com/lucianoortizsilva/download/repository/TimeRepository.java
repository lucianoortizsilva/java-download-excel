package com.lucianoortizsilva.download.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucianoortizsilva.download.model.Time;

public interface TimeRepository extends JpaRepository<Time, Long> {}