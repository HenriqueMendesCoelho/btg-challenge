package com.btgpactual.challenge.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.Executors;

public class RabbitMQConfig {

	@Bean
	Queue processarPedidoQueue() {
		return new Queue("processar-pedidos");
	}

	@Bean
	public SimpleRabbitListenerContainerFactory lowLoadFactory(ConnectionFactory connectionFactory) {
		var factory = new SimpleRabbitListenerContainerFactory();
		factory.setTaskExecutor(Executors.newVirtualThreadPerTaskExecutor());
		factory.setConnectionFactory(connectionFactory);
		factory.setConcurrentConsumers(1);
		factory.setMaxConcurrentConsumers(2);
		return factory;
	}

}
