package com.acoes.backend.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.acoes.backend.models.Monitoramento;
import com.acoes.backend.repositories.MonitoramentoRepository;

import jakarta.transaction.Transactional;

@Service
public class MonitoramentoService {

    private final MonitoramentoRepository repository;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public MonitoramentoService(MonitoramentoRepository repository) {
        this.repository = repository;
    }

    @Async
    @Transactional
    public void importarCsvAsync(Path filePath) {

        try {
            importarCsv(filePath);
        } finally {
            try {
                // 🔥 apaga o arquivo ao final
                Files.deleteIfExists(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void importarCsv(Path filePath) {

        repository.truncateTable();

        int batchSize = 1000;
        List<Monitoramento> lista = new ArrayList<>(batchSize);

        try (
                BufferedReader reader = Files.newBufferedReader(filePath, StandardCharsets.UTF_8);

                CSVParser csvParser = new CSVParser(
                        reader,
                        CSVFormat.DEFAULT.builder()
                                .setDelimiter(',')
                                .setHeader()
                                .setSkipHeaderRecord(true)
                                .setTrim(true)
                                .build())) {

            for (CSVRecord record : csvParser) {

                Monitoramento m = new Monitoramento();

                m.setCodigoIbge(record.get("codigo_ibge"));
                m.setNomeMunicipio(record.get("nome_municipio"));
                m.setSiglaUf(record.get("sigla_uf"));

                m.setDataInicial(LocalDate.parse(record.get("data_inicial"), formatter));
                m.setDataFinal(LocalDate.parse(record.get("data_final"), formatter));

                m.setCodSeqEixo(record.get("cod_seq_eixo"));
                m.setNomeEixo(record.get("nome_eixo"));
                m.setCodSeqAcao(record.get("cod_seq_acao"));
                m.setNomeAcao(record.get("nome_acao"));

                m.setDataExtracaoDados(
                        LocalDate.parse(record.get("data_extracao_dados"), formatter));

                lista.add(m);

                if (lista.size() == batchSize) {
                    repository.saveAll(lista);
                    repository.flush();
                    lista.clear();
                }
            }

            if (!lista.isEmpty()) {
                repository.saveAll(lista);
                repository.flush();
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao importar CSV: " + e.getMessage(), e);
        }
    }
}
