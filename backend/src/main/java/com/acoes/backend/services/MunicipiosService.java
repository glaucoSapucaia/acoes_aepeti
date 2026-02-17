package com.acoes.backend.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.acoes.backend.models.Municipios;
import com.acoes.backend.repositories.MunicipiosRepository;

@Service
public class MunicipiosService {

    private final MunicipiosRepository repository;

    public MunicipiosService(MunicipiosRepository repository) {
        this.repository = repository;
    }

    public Page<Municipios> listar(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<Municipios> buscarPorCodigoIbge(String codigoIbge, Pageable pageable) {
        return repository.findByCodigoIbge(codigoIbge, pageable);
    }
}
