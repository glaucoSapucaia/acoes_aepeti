package com.acoes.backend.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.acoes.backend.dto.DashboardEixoDTO;
import com.acoes.backend.dto.DashboardPeriodoDTO;
import com.acoes.backend.dto.DashboardUfDTO;
import com.acoes.backend.services.DashboardService;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final DashboardService service;

    public DashboardController(DashboardService service) {
        this.service = service;
    }

    @GetMapping("/uf")
    public List<DashboardUfDTO> porUf(
            @RequestParam(required = false) String uf,
            @RequestParam(required = false) String eixo,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {

        return service.getDashboardUf(uf, eixo, dataInicio, dataFim);
    }

    @GetMapping("/eixo")
    public List<DashboardEixoDTO> porEixo(
            @RequestParam(required = false) String uf,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {

        return service.getDashboardEixo(uf, dataInicio, dataFim);
    }

    @GetMapping("/periodo")
    public List<DashboardPeriodoDTO> porPeriodo(
            @RequestParam(required = false) String uf,
            @RequestParam(required = false) String eixo,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {

        return service.getDashboardPeriodo(uf, eixo, dataInicio, dataFim);
    }
}
