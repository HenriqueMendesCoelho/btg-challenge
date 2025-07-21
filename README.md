# Rabbitmq Order Handler

Este projeto é uma aplicação backend desenvolvida em Java com Spring Boot para gerenciamento de pedidos. Ele utiliza
RabbitMQ para processamento assíncrono de pedidos e oferece uma API REST para criação e consulta do status dos mesmos.

## Tecnologias utilizadas

- Java 21
- Spring Boot 3.5.3
- RabbitMQ (Spring AMQP)
- Lombok
- Validação com Spring Boot Starter Validation
- Maven para gerenciamento de dependências e build

## Funcionalidades

- Criação de pedidos com status inicial "pendente"
- Processamento assíncrono de pedidos (simulado com delay)
- Consulta do status de um pedido pelo seu ID
- Tratamento de exceção para pedido não encontrado

## Endpoints

- **POST** `/api/pedidos`  
  Cria um novo pedido. Recebe um JSON com os dados do pedido e retorna o pedido criado com seu status.

- **GET** `/api/pedidos/status/{id}`  
  Consulta o status do pedido pelo ID. Retorna o pedido com status ou 404 se não encontrado.

## Configuração

As configurações principais estão em `src/main/resources/application.properties`.  
São configurados parâmetros como:

- Nome da aplicação
- Porta do servidor (padrão 8080)
- Context path da API `/api`
- Configurações do RabbitMQ: host, porta, usuário e senha (via variáveis de ambiente)

## Como rodar

1. Tenha Java 21 instalado.
2. Configure o RabbitMQ localmente ou em um servidor acessível, defina as variáveis de ambiente:
    - `RABBITMQ_HOST`
    - `RABBITMQ_PORT`
    - `RABBITMQ_USERNAME`
    - `RABBITMQ_PASSWORD`
3. Compile e rode a aplicação com Maven:
   ```bash
   ./mvnw spring-boot:run
   ```
4. Acesse a API na URL `http://localhost:8080/api`

## Estrutura do projeto

- `controller/` - Controllers REST para expor a API
- `domain/` - Modelos de domínio, enums e serviços de negócio
- `infrastructure/messaging/` - Componentes para envio e recebimento de mensagens RabbitMQ
- `exception/` - Exceções personalizadas para controle de erros
