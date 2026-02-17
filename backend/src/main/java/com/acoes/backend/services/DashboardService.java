package com.acoes.backend.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.acoes.backend.dto.DashboardEixoDTO;
import com.acoes.backend.dto.DashboardPeriodoDTO;
import com.acoes.backend.dto.DashboardUfDTO;
import com.acoes.backend.repositories.MonitoramentoRepository;

@Service
public class DashboardService {

    private final MonitoramentoRepository repository;

    public DashboardService(MonitoramentoRepository repository) {
        this.repository = repository;
    }

    public List<DashboardUfDTO> getDashboardUf(
            String uf,
            String eixo,
            LocalDate dataInicio,
            LocalDate dataFim) {

        return repository.dashboardByUf(uf, eixo, dataInicio, dataFim);
    }

    public List<DashboardEixoDTO> getDashboardEixo(
            String uf,
            String eixo,
            LocalDate dataInicio,
            LocalDate dataFim) {

        return repository.dashboardByEixo(uf, eixo, dataInicio, dataFim);
    }

    public List<DashboardPeriodoDTO> getDashboardPeriodo(
            String uf,
            String eixo,
            LocalDate dataInicio,
            LocalDate dataFim) {

        return repository.dashboardByPeriodo(uf, eixo, dataInicio, dataFim);
    }
}
