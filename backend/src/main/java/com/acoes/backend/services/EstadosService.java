package com.acoes.backend.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.acoes.backend.models.Estados;
import com.acoes.backend.repositories.EstadosRepository;

@Service
public class EstadosService {

    private final EstadosRepository repository;

    public EstadosService(EstadosRepository repository) {
        this.repository = repository;
    }

    public List<Integer> listarEstados() {
        return repository.findAll()
                .stream()
                .map(Estados::getCodigoIbge)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<Estados> listarPorCodigoIbge(Integer codigoIbge) {
        return repository.findByCodigoIbge(codigoIbge);
    }
}
