package com.acoes.backend.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.acoes.backend.services.MonitoramentoService;

@RestController
@RequestMapping("/monitoramento")
public class MonitoramentoController {

    private final MonitoramentoService service;

    public MonitoramentoController(MonitoramentoService service) {
        this.service = service;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadCsv(@RequestParam("file") MultipartFile file) throws IOException {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Arquivo vazio.");
        }

        // 🔥 cria arquivo temporário real
        Path tempFile = Files.createTempFile("monitoramento-", ".csv");

        // copia conteúdo para o arquivo físico
        file.transferTo(tempFile);

        // chama async passando o caminho
        service.importarCsvAsync(tempFile);

        return ResponseEntity.accepted()
                .body("Arquivo recebido e está sendo processado em background.");
    }
}
