# Microserviço - BFF Agendador de Tarefas

### Descrição

O bff-agendador-tarefas é um microserviço Backend for Frontend (BFF) que atua como intermediário entre os frontends (web e mobile) e os microserviços do sistema de Agendamento de Tarefas.

### Responsabilidades:

### Agregar e adaptar dados de múltiplos microserviços, como:

- usuario – informações dos usuários do sistema
- agendador-tarefas – dados, status e prazos das tarefas
- notificação – envio de alertas e notificações de tarefas

O BFF simplifica a comunicação do frontend com o backend, agregando informações e entregando respostas específicas para cada tipo de cliente, reduzindo chamadas desnecessárias, melhorando performance e mantendo consistência na exibição de dados.

### Tecnologias Utilizadas

- Java 17
- Spring Boot
- Maven 
- OpenFeing (Comunicação entre microserviços)
