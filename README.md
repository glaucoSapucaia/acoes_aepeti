# acoes_aepeti
Projeto em java+spring+angular para a visualização de ações do programa de erradicação do trabalho infantil.

## Tools
- [start.spring](https://start.spring.io/)

## Dados
- [vis data 3](https://aplicacoes.cidadania.gov.br/vis/data3/data-explorer.php)
- [dados abertos gov](https://dados.gov.br/dados/conjuntos-dados)

## Indices DB
```sql
CREATE INDEX idx_monitoramento_uf ON monitoramento(sigla_uf);
CREATE INDEX idx_monitoramento_eixo ON monitoramento(cod_seq_eixo);
CREATE INDEX idx_monitoramento_data ON monitoramento(data_extracao_dados);
```

## Referências
- [codigo ibge municipios](https://www.ibge.gov.br/explica/codigos-dos-municipios.php)