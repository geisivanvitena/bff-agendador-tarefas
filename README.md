# Microserviço - BFF Agendador de Tarefas

## Sobre o Projeto

O bff-agendador-tarefas é um microserviço Backend for Frontend (BFF) responsável por atuar como intermediário entre os clientes (Web e Mobile) e os microserviços do sistema de agendamento de tarefas.

O objetivo principal é agregar dados de diferentes serviços, reduzindo chamadas diretas do frontend para os microserviços, melhorando a performance e facilitando a manutenção da arquitetura.

O sistema possui autenticação centralizada e documentação das APIs com Swagger.

##

## Arquitetura do Sistema

### O BFF se comunica com os seguintes microserviços:

• Usuário — gerenciamento de usuários e autenticação

• Agendador de tarefas — controle de tarefas e status

• Notificação — envio de e-mails e alertas

• Arquitetura baseada em microserviços com comunicação REST e agregação de dados via BFF Pattern.

### Fluxo da arquitetura:
• Frontend → BFF → Microserviços → Banco de Dados

## 

## Autenticação

O sistema possui autenticação implementada no microserviço de usuário utilizando Spring Security e JWT.

O BFF atua como intermediário, validando e repassando as credenciais para os serviços apropriados.

##

## Documentação da API (Swagger)

### A documentação das APIs pode ser acessada via Swagger nos microserviços:

•	Usuário API → http://localhost:8080/swagger-ui.html

•	Agendador API → http://localhost:8081/swagger-ui.html

•	Notificação API → http://localhost:8082/swagger-ui.html

##

## Tecnologias Utilizadas

•	Java 17

•	Spring Boot 3

•	Maven

•	OpenFeign (Comunicação entre microserviços)

•	Docker

##

## Pré-requisitos

### Antes de executar o projeto você precisa ter instalado:

•	Docker

•	Docker Compose

•	Java 17

•	Maven (opcional se usar Docker)

##

## Variáveis de Ambiente

### Criar arquivo .env com as variáveis:

• DB_USER=usuario

• DB_PASSWORD=senha

• BFF_API_PORT=8083

• USUARIO_API_PORT=8080

• AGENDADOR_API_PORT=8081

• NOTIFICACAO_API_PORT=8082

##

## Como Executar o Projeto

• git clone URL_DO_REPOSITORIO

• cd bff-agendador-api

• docker compose down

• docker compose up --build

##

### Endpoints Expostos

• Serviço	Porta

• BFF	8083

• Usuário API	8080

• Agendador API	8081

• Notificação API	8082

##

## Benefícios da Arquitetura

•	Redução de chamadas do frontend

•	Melhor organização da comunicação

•	Escalabilidade

•	Segurança centralizada

•	Separação de responsabilidades

##

## Melhorias Futuras

•	Implementar Circuit Breaker

•	Melhorar monitoramento com Actuator + Prometheus

•	Adicionar mensageria (RabbitMQ ou Kafka)

•	Deploy em Cloud

##

## Autor

Desenvolvido por **Geisivan Vitena**

LinkedIn:  
https://www.linkedin.com/in/geisivan-vitena-a46168246/