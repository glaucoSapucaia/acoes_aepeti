package com.acoes.backend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acoes.backend.models.Estados;
import com.acoes.backend.services.EstadosService;

@RestController
@RequestMapping("/estados")
public class EstadosController {

    private final EstadosService service;

    public EstadosController(EstadosService service) {
        this.service = service;
    }

    @GetMapping
    public List<String> listarEstados() {
        return service.listarEstados();
    }

    @GetMapping("/{codigoIbge}")
    public List<Estados> listarPorCodigoIbge(@PathVariable String codigoIbge) {
        return service.listarPorCodigoIbge(codigoIbge);
    }
}
