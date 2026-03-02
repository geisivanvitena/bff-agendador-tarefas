# Microserviço BFF - Agendador de Tarefas (bff-agendador)

### Contexto do Projeto

O bff-agendador é uma API REST desenvolvida em Java com Spring Boot e faz parte do projeto Agendador de Tarefas, construído com base em arquitetura de microserviços.

Este microserviço implementa o padrão Backend for Frontend (BFF), atuando como camada intermediária entre os clientes (Web e Mobile) e os microserviços internos do sistema.

Seu principal objetivo é orquestrar chamadas, agregar dados de múltiplos serviços e reduzir o acoplamento direto entre o frontend e os microserviços de domínio.

##

### Papel na Arquitetura de Microserviços

Dentro da arquitetura do Agendador de Tarefas, o BFF é responsável por:

• Orquestrar chamadas entre microserviços

• Agregar dados de múltiplas fontes

• Reduzir a complexidade no frontend

• Centralizar regras específicas de apresentação

• Padronizar respostas para os clientes

A comunicação ocorre via HTTP, utilizando padrão REST e troca de dados em formato JSON.

O BFF se comunica com os seguintes microserviços:

• ms-usuarios — gerenciamento de usuários e autenticação

• ms-agendador — gerenciamento de tarefas e status

• ms-notificacao — envio de notificações e alertas

Fluxo arquitetural:

• Frontend → BFF → Microserviços → Banco de Dados

Essa abordagem promove:

Separação clara de responsabilidades

• Desacoplamento entre frontend e serviços de domínio

• Evolução independente dos microserviços

• Maior controle da comunicação externa

##

### API REST

O bff-agendador expõe endpoints REST stateless, utilizando:

• Métodos HTTP (GET, POST, PUT, DELETE)

• Representação de recursos em JSON

• Comunicação síncrona entre serviços via REST

• Integração entre microserviços utilizando OpenFeign

##

### Autenticação

A autenticação é centralizada no ms-usuarios, utilizando:

• Spring Security

• JWT (JSON Web Token)

O BFF atua como intermediário, validando e repassando o token JWT para os serviços apropriados, garantindo que apenas requisições autenticadas sejam processadas.

##

### Observabilidade

O microserviço utiliza Spring Boot Actuator para monitoramento e exposição de métricas operacionais.

Os endpoints de gerenciamento permitem:

•  Healthcheck da aplicação

•  Monitoramento de disponibilidade

•  Exposição de métricas de desempenho

•  Informações do ambiente

Exemplo de endpoint:

http://localhost:8083/actuator/health

A utilização do Actuator no BFF é especialmente relevante, pois ele atua como ponto central de entrada do sistema, permitindo monitorar a disponibilidade da camada de orquestração.

##

### Tecnologias Utilizadas

•	Java 17

•	Spring Boot 

•	Maven

•	OpenFeign (Comunicação entre microserviços)

• Spring Boot Actuator

•	Docker

##

### Documentação da API

A documentação das APIs está disponível via Swagger nos respectivos microserviços:

Usuário API
http://localhost:8080/swagger-ui.html

Agendador API
http://localhost:8081/swagger-ui.html

Notificação API
http://localhost:8082/swagger-ui.html

BFF API
http://localhost:8083/swagger-ui.html

##

### Execução do Projeto

• Execução com Docker

1. git clone URL_DO_REPOSITORIO

2. cd bff-agendador-api
   
3. docker compose down
   
4. docker compose up --build

##

### Variáveis de Ambiente

Criar um arquivo .env na raiz do projeto contendo:

•	DB_USER=usuario

•	DB_PASSWORD=senha

•	BFF_API_PORT=8083

•	USUARIO_API_PORT=8080

•	AGENDADOR_API_PORT=8081

•	NOTIFICACAO_API_PORT=8082 

##

### Endpoints Expostos

| Serviço	               |   Porta   |
|------------------------|-----------| 
| BFF	                   |    8083   |
| Usuário API	           |    8080   |
| Agendador API	         |    8081   |
| Notificação API	       |    8082   |

##

## Benefícios Arquiteturais

• Redução de chamadas diretas do frontend para múltiplos serviços

• Orquestração centralizada

• Melhor organização da comunicação externa

• Segurança centralizada via JWT

• Escalabilidade independente

• Aplicação do padrão BFF

##

## Melhorias Futuras

•	Implementação de Circuit Breaker (Resiliência)

• Integração com Prometheus para métricas

• Implementação de mensageria (RabbitMQ ou Kafka)

• Deploy em ambiente Cloud

##

### Autor

**Geisivan Vitena**

LinkedIn:  
https://www.linkedin.com/in/geisivan-vitena-a46168246/
