package com.acoes.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acoes.backend.models.Municipios;

@Repository
public interface MunicipiosRepository extends JpaRepository<Municipios, Long> {

    List<Municipios> findByCodigoIbge(String codigoIbge);

}
