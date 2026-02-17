package com.acoes.backend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acoes.backend.models.Estados;

@Repository
public interface EstadosRepository extends JpaRepository<Estados, Long> {

    Page<Estados> findByCodigoIbge(String codigoIbge, Pageable pageable);

}
