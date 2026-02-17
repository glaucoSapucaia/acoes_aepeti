package com.acoes.backend.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public Page<Estados> listar(
            @RequestParam(required = false) String codigoIbge,
            @PageableDefault(size = 10) Pageable pageable) {

        if (codigoIbge != null && !codigoIbge.isEmpty()) {
            return service.buscarPorCodigoIbge(codigoIbge, pageable);
        }

        return service.listar(pageable);
    }
}
