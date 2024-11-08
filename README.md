# Projeto Web Crawler

Este projeto implementa um **web crawler** em Groovy, com o objetivo de acessar o site do Governo na página da Agência Nacional de Saúde (ANS) e coletar informações específicas de determinadas seções. 


## Funcionalidades

- **Coleta de dados do histórico de versões**: O web crawler extrai dados de tabela Padrão TISS - Histórico das versões dos Componentes do Padrão TISS
- **Download de arquivos**: Baixa aquivos da documentação do padrão TISS (Troca de Informações na Saúde Suplementar) e Tabela de erros no envio para a ANS. 


## Tecnologias Utilizadas

- **Linguagem**: Groovy
- **Framework de Parsing**: Jsoup (para manipulação e extração de dados de HTML)
- **HTTP Requests**: HTTPBuilder (para realizar requisições HTTP)


### Pré-requisitos

- Ter o **Java** e **Groovy** instalados.
- Utilizar o **Gradle** como gerenciador de dependências.
- Acesso à internet para que o crawler possa acessar a página da ANS.

Desenvolvido por Paloma Eduarda
