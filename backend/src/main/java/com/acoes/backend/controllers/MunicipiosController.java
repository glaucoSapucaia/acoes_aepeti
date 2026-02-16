package com.acoes.backend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acoes.backend.models.Municipios;
import com.acoes.backend.services.MunicipiosService;

@RestController
@RequestMapping("/municipios")
public class MunicipiosController {

    private final MunicipiosService service;

    public MunicipiosController(MunicipiosService service) {
        this.service = service;
    }

    @GetMapping
    public List<String> listarMunicipios() {
        return service.listarMunicipios();
    }

    @GetMapping("/{codigoIbge}")
    public List<Municipios> listarPorCodigoIbge(@PathVariable String codigoIbge) {
        return service.listarPorCodigoIbge(codigoIbge);
    }
}
