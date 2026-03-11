# Microserviço BFF - Agendador de Tarefas (bff-agendador)

![Build Status](https://img.shields.io/badge/build-passing-brightgreen)

![Java 17](https://img.shields.io/badge/java-17-brightgreen)

![Spring Boot 3.3.5](https://img.shields.io/badge/spring_boot-3.3.5-brightgreen)

![Maven 3.9.6](https://img.shields.io/badge/Maven-3.9.6-blue)

![Dependências Seguras](https://img.shields.io/badge/dependencies-secure-brightgreen)

![GitHub Actions](https://github.com/geisivanvitena/bff-agendador-tarefas/actions/workflows/maven.yml/badge.svg)

---

### Contexto do Projeto

O **bff-agendador** é uma API REST desenvolvida em **Java com Spring Boot** e faz parte do projeto **Agendador de Tarefas**, construído com base em arquitetura de microserviços.

Este microserviço implementa o padrão **Backend for Frontend (BFF)**, atuando como camada intermediária entre os clientes (Web e Mobile) e os microserviços internos do sistema.

**Objetivo principal:**

Orquestrar chamadas, agregar dados de múltiplos serviços e reduzir o acoplamento direto entre o frontend e os microserviços de domínio.

---

### Papel na Arquitetura de Microserviços

**O BFF é responsável por:**

- Orquestrar chamadas entre microserviços

- Agregar dados de múltiplas fontes

- Reduzir a complexidade no frontend

- Centralizar regras específicas de apresentação

- Padronizar respostas para os clientes

A comunicação ocorre via **HTTP**, utilizando padrão REST e troca de dados em formato **JSON**.

**O BFF se comunica com os seguintes microserviços:**

- **ms-usuarios** — gerenciamento de usuários e autenticação

- **ms-agendador** — gerenciamento de tarefas e status

- **ms-notificacao** — envio de notificações e alertas

---

**Fluxo arquitetural:**

    • Frontend → BFF → Microserviços → Banco de Dados

**Essa abordagem promove:**

- Separação clara de responsabilidades

- Desacoplamento entre frontend e serviços de domínio

- Evolução independente dos microserviços

- Maior controle da comunicação externa

---

### API REST

O **bff-agendador** expõe endpoints REST **stateless**, utilizando:

- Métodos HTTP: (GET, POST, PUT, PATCH, DELETE)

- Representação de recursos em **JSON**

- Comunicação síncrona entre serviços via REST

#### Integração com OpenFeign

O BFF utiliza **OpenFeign** para comunicação declarativa entre microserviços, permitindo:

- Redução de código boilerplate
- Padronização de chamadas HTTP
- Facilidade na manutenção de endpoints remotos

---

### Autenticação

A autenticação é centralizada no **ms-usuarios**, utilizando:

- **Spring Security**

- **JWT (JSON Web Token)**

O BFF atua como intermediário, validando e repassando o token JWT para os serviços apropriados, garantindo que apenas requisições autenticadas sejam processadas.

---

### Observabilidade

O microserviço utiliza **Spring Boot Actuator** para monitoramento e exposição de métricas operacionais.

**Os endpoints de gerenciamento permitem:**

-  Healthcheck da aplicação

-  Monitoramento de disponibilidade

-  Exposição de métricas de desempenho

-  Informações do ambiente

**Exemplo de endpoint:**

    http://localhost:8083/actuator/health

A utilização do Actuator no BFF é especialmente relevante, pois ele atua como ponto central de entrada do sistema, permitindo monitorar a disponibilidade da camada de orquestração.

---

### Documentação da API

A documentação das APIs está disponível via **Swagger** nos respectivos microserviços:

**Usuário API**

    http://localhost:8080/swagger-ui.html

**Agendador API**

    http://localhost:8081/swagger-ui.html

**Notificação API**

    http://localhost:8082/swagger-ui.html

**BFF API**

    http://localhost:8083/swagger-ui.html

---

### Tecnologias Utilizadas

- **Java 17**

- **Spring Boot**

- **Maven**

- **OpenFeign** (Comunicação entre microserviços)

- **Spring Boot Actuator**

- **Docker**

---

### Endpoints Expostos

| Serviço	              | Porta |
|-----------------------|-------| 
| Usuário API	                 | 8080  |
| Agendador API	         | 8081  |
| Notificação API        | 8082  |
| BFF       | 8083  |

---

### Execução do Projeto

**Docker**

    docker compose down
   
    docker compose up --build

---

### Variáveis de Ambiente

Criar um arquivo .env na raiz do projeto contendo:

**Banco de Dados (PostgreSQL)**

    DB_USER=usuario

    DB_PASSWORD=senha

Responsável pela autenticação no banco utilizado pelo BFF

**Login do Sistema (Automação / Cron)**

    USUARIO_EMAIL=email

    USUARIO_SENHA=senha

Credenciais utilizadas para autenticação automática entre serviços (ex: rotinas agendadas).

**Portas dos Microserviços**

    USUARIO_API_PORT=8080

    AGENDADOR_API_PORT=8081

    NOTIFICACAO_API_PORT=8082 

    BFF_API_PORT=8083

**Definem as portas de comunicação entre:**

- ms-usuarios

- ms-tarefas

- ms-notificacao

- bff-agendador

**Configuração de E-mail (Notificações)**

    MAIL_USERNAME=email

    MAIL_PASSWORD=senha

    MAIL_HOST=smtp.gmail.com

    MAIL_PORT=587

Utilizado para envio de notificações por e-mail via SMTP.

---

### Benefícios Arquiteturais

- Redução de chamadas diretas do frontend para múltiplos serviços

- Orquestração centralizada

- Melhor organização da comunicação externa

- Segurança centralizada via JWT

- Escalabilidade independente

- Aplicação do padrão BFF

---

### Melhorias Futuras

- Implementação de Circuit Breaker (Resiliência)

- Integração com Prometheus para métricas

- Implementação de mensageria (RabbitMQ ou Kafka)

- Deploy em ambiente Cloud

- Implementação de testes automatizados (unitários e de integração)

##

### Autor

**Geisivan Vitena**

LinkedIn:  
https://www.linkedin.com/in/geisivan-vitena-a46168246/
