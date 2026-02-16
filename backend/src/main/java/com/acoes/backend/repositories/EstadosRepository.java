package com.acoes.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acoes.backend.models.Estados;

@Repository
public interface EstadosRepository extends JpaRepository<Estados, Long> {
    List<Estados> findByCodigoIbge(String codigoIbge);
}
