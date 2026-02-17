package com.acoes.backend.dto;

import java.time.LocalDate;

public record DashboardPeriodoDTO(
        LocalDate data,
        Long total) {
}
