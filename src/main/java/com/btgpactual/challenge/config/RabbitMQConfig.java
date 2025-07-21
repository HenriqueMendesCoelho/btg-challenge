package com.btgpactual.challenge.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;

@Configuration
public class RabbitMQConfig {

	@Bean
	Queue processarPedidoQueue() {
		return new Queue("processar-pedidos");
	}

	@Bean
	SimpleRabbitListenerContainerFactory lowLoadFactory(ConnectionFactory connectionFactory) {
		var factory = new SimpleRabbitListenerContainerFactory();
		factory.setTaskExecutor(Executors.newVirtualThreadPerTaskExecutor());
		factory.setConnectionFactory(connectionFactory);
		factory.setPrefetchCount(20);
		factory.setConcurrentConsumers(10);
		factory.setMaxConcurrentConsumers(30);
		return factory;
	}

}
