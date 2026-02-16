package com.acoes.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.acoes.backend.models.Monitoramento;

public interface MonitoramentoRepository extends JpaRepository<Monitoramento, Long> {

    @Modifying
    @Query(value = "TRUNCATE TABLE monitoramento RESTART IDENTITY", nativeQuery = true)
    void truncateTable();
}