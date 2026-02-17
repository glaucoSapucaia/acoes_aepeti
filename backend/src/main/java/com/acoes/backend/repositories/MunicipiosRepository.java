package com.acoes.backend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acoes.backend.models.Municipios;

@Repository
public interface MunicipiosRepository extends JpaRepository<Municipios, Long> {

    Page<Municipios> findByCodigoIbge(String codigoIbge, Pageable pageable);

}
