package com.btgpactual.challenge.infrastructure.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PedidoSender {

	private final RabbitTemplate template;
	private final Queue processarPedidoQueue;

	public void send(UUID uuid) {
		template.convertAndSend(processarPedidoQueue.getName(), uuid.toString());
	}
}
