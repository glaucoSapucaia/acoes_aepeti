# acoes_aepeti
Projeto em java+spring+angular para a visualização de ações do programa de erradicação do trabalho infantil.

## Para usar
- [instale docker](https://www.docker.com/)
- [instale dbeaver](https://dbeaver.io/download/)
```sh
git clone https://github.com/glaucoSapucaia/acoes_aepeti.git .
```
- configure .env
```docker
docker compose up --build
```
- acesso backend - http://localhost:8080/
- acesso frontend - http://localhost:4200/
- importe o csv das tabelas municipios e estados pelo dbeaver
- para dados da tabela de monitoramento, use a rota: http://localhost:8080/monitoramento/upload

## Tools
- [start.spring](https://start.spring.io/)
- [apex.charts](https://apexcharts.com/docs/installation/)

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