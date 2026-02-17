package com.acoes.backend.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.acoes.backend.dto.DashboardEixoDTO;
import com.acoes.backend.dto.DashboardPeriodoDTO;
import com.acoes.backend.dto.DashboardUfDTO;
import com.acoes.backend.models.Monitoramento;

public interface MonitoramentoRepository extends JpaRepository<Monitoramento, Long> {

    @Modifying
    @Query(value = "TRUNCATE TABLE monitoramento RESTART IDENTITY", nativeQuery = true)
    void truncateTable();

    @Query("""
                SELECT new com.acoes.backend.dto.DashboardUfDTO(
                    m.siglaUf,
                    COUNT(m)
                )
                FROM Monitoramento m
                WHERE (:uf IS NULL OR m.siglaUf = :uf)
                  AND (:eixo IS NULL OR m.codSeqEixo = :eixo)
                  AND m.dataInicial >= COALESCE(:dataInicio, m.dataInicial)
                  AND m.dataInicial <= COALESCE(:dataFim, m.dataInicial)
                GROUP BY m.siglaUf
                ORDER BY COUNT(m) DESC
            """)
    List<DashboardUfDTO> dashboardByUf(
            @Param("uf") String uf,
            @Param("eixo") String eixo,
            @Param("dataInicio") LocalDate dataInicio,
            @Param("dataFim") LocalDate dataFim);

    @Query("""
                SELECT new com.acoes.backend.dto.DashboardEixoDTO(
                    m.nomeEixo,
                    COUNT(m)
                )
                FROM Monitoramento m
                WHERE (:uf IS NULL OR m.siglaUf = :uf)
                  AND m.dataInicial >= COALESCE(:dataInicio, m.dataInicial)
                  AND m.dataInicial <= COALESCE(:dataFim, m.dataInicial)
                GROUP BY m.nomeEixo
                ORDER BY COUNT(m) DESC
            """)
    List<DashboardEixoDTO> dashboardByEixo(
            @Param("uf") String uf,
            @Param("dataInicio") LocalDate dataInicio,
            @Param("dataFim") LocalDate dataFim);

    @Query("""
                SELECT new com.acoes.backend.dto.DashboardPeriodoDTO(
                    m.dataInicial,
                    COUNT(m)
                )
                FROM Monitoramento m
                WHERE (:uf IS NULL OR m.siglaUf = :uf)
                  AND (:eixo IS NULL OR m.codSeqEixo = :eixo)
                  AND m.dataInicial >= COALESCE(:dataInicio, m.dataInicial)
                  AND m.dataInicial <= COALESCE(:dataFim, m.dataInicial)
                GROUP BY m.dataInicial
                ORDER BY m.dataInicial
            """)
    List<DashboardPeriodoDTO> dashboardByPeriodo(
            @Param("uf") String uf,
            @Param("eixo") String eixo,
            @Param("dataInicio") LocalDate dataInicio,
            @Param("dataFim") LocalDate dataFim);
}
