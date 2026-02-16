package com.acoes.backend.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.acoes.backend.models.Municipios;
import com.acoes.backend.repositories.MunicipiosRepository;

@Service
public class MunicipiosService {

    private final MunicipiosRepository repository;

    public MunicipiosService(MunicipiosRepository repository) {
        this.repository = repository;
    }

    public List<String> listarMunicipios() {
        return repository.findAll()
                .stream()
                .map(Municipios::getCodigoIbge)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<Municipios> listarPorCodigoIbge(String codigoIbge) {
        return repository.findByCodigoIbge(codigoIbge);
    }
}
