package com.acoes.backend.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.acoes.backend.models.Estados;
import com.acoes.backend.repositories.EstadosRepository;

@Service
public class EstadosService {

    private final EstadosRepository repository;

    public EstadosService(EstadosRepository repository) {
        this.repository = repository;
    }

    public Page<Estados> listar(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<Estados> buscarPorCodigoIbge(String codigoIbge, Pageable pageable) {
        return repository.findByCodigoIbge(codigoIbge, pageable);
    }
}
