package com.acoes.backend.models;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "estados", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "codigo_ibge", "anomes" })
})
public class Estados implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // código IBGE como string (VARCHAR2)
    @Column(name = "codigo_ibge", nullable = false, length = 10)
    private String codigoIbge;

    // ano-mês como DATE (YYYYMM)
    @Column(name = "anomes", nullable = false)
    private String anomes;

    @Column(name = "qtd_acoes_eixo_01")
    private Integer qtdAcoesEixo01;

    @Column(name = "qtd_acoes_eixo_02")
    private Integer qtdAcoesEixo02;

    @Column(name = "qtd_acoes_eixo_03")
    private Integer qtdAcoesEixo03;

    @Column(name = "qtd_acoes_eixo_04")
    private Integer qtdAcoesEixo04;

    @Column(name = "qtd_acoes_eixo_05")
    private Integer qtdAcoesEixo05;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoIbge() {
        return codigoIbge;
    }

    public void setCodigoIbge(String codigoIbge) {
        this.codigoIbge = codigoIbge;
    }

    public String getAnomes() {
        return anomes;
    }

    public void setAnomes(String anomes) {
        this.anomes = anomes;
    }

    public Integer getQtdAcoesEixo01() {
        return qtdAcoesEixo01;
    }

    public void setQtdAcoesEixo01(Integer qtdAcoesEixo01) {
        this.qtdAcoesEixo01 = qtdAcoesEixo01;
    }

    public Integer getQtdAcoesEixo02() {
        return qtdAcoesEixo02;
    }

    public void setQtdAcoesEixo02(Integer qtdAcoesEixo02) {
        this.qtdAcoesEixo02 = qtdAcoesEixo02;
    }

    public Integer getQtdAcoesEixo03() {
        return qtdAcoesEixo03;
    }

    public void setQtdAcoesEixo03(Integer qtdAcoesEixo03) {
        this.qtdAcoesEixo03 = qtdAcoesEixo03;
    }

    public Integer getQtdAcoesEixo04() {
        return qtdAcoesEixo04;
    }

    public void setQtdAcoesEixo04(Integer qtdAcoesEixo04) {
        this.qtdAcoesEixo04 = qtdAcoesEixo04;
    }

    public Integer getQtdAcoesEixo05() {
        return qtdAcoesEixo05;
    }

    public void setQtdAcoesEixo05(Integer qtdAcoesEixo05) {
        this.qtdAcoesEixo05 = qtdAcoesEixo05;
    }
}
