package com.acoes.backend.models;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "monitoramento")
public class Monitoramento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "monitoramento_seq")
    @SequenceGenerator(name = "monitoramento_seq", sequenceName = "monitoramento_seq", allocationSize = 1000)
    private Long id;

    @Column(name = "codigo_ibge", nullable = false, length = 10)
    private String codigoIbge;

    @Column(name = "nome_municipio", length = 150)
    private String nomeMunicipio;

    @Column(name = "sigla_uf", length = 2)
    private String siglaUf;

    @Column(name = "data_inicial")
    private LocalDate dataInicial;

    @Column(name = "data_final")
    private LocalDate dataFinal;

    @Column(name = "cod_seq_eixo", nullable = false, length = 10)
    private String codSeqEixo;

    @Column(name = "nome_eixo", length = 150)
    private String nomeEixo;

    @Column(name = "cod_seq_acao", nullable = false, length = 10)
    private String codSeqAcao;

    @Column(name = "nome_acao", columnDefinition = "TEXT")
    private String nomeAcao;

    @Column(name = "data_extracao_dados")
    private LocalDate dataExtracaoDados;

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

    public String getNomeMunicipio() {
        return nomeMunicipio;
    }

    public void setNomeMunicipio(String nomeMunicipio) {
        this.nomeMunicipio = nomeMunicipio;
    }

    public String getSiglaUf() {
        return siglaUf;
    }

    public void setSiglaUf(String siglaUf) {
        this.siglaUf = siglaUf;
    }

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(LocalDate dataInicial) {
        this.dataInicial = dataInicial;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    public String getCodSeqEixo() {
        return codSeqEixo;
    }

    public void setCodSeqEixo(String codSeqEixo) {
        this.codSeqEixo = codSeqEixo;
    }

    public String getNomeEixo() {
        return nomeEixo;
    }

    public void setNomeEixo(String nomeEixo) {
        this.nomeEixo = nomeEixo;
    }

    public String getCodSeqAcao() {
        return codSeqAcao;
    }

    public void setCodSeqAcao(String codSeqAcao) {
        this.codSeqAcao = codSeqAcao;
    }

    public String getNomeAcao() {
        return nomeAcao;
    }

    public void setNomeAcao(String nomeAcao) {
        this.nomeAcao = nomeAcao;
    }

    public LocalDate getDataExtracaoDados() {
        return dataExtracaoDados;
    }

    public void setDataExtracaoDados(LocalDate dataExtracaoDados) {
        this.dataExtracaoDados = dataExtracaoDados;
    }
}
