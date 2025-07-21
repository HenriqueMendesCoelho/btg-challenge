package com.btgpactual.challenge.infrastructure.messaging;

import com.btgpactual.challenge.domain.service.PedidoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
@RabbitListener(queues = "processar-pedidos", containerFactory = "lowLoadFactory")
@RequiredArgsConstructor
public class PedidoReceiver {

	private final PedidoService service;

	@RabbitHandler
	public void receive(String in) {
		try {
			UUID pedidoId = UUID.fromString(in);
			log.info("Processando pedido {}", in);

			service.processarPedido(pedidoId);

			log.info("Pedido {} processado", in);
		} catch (Exception e) {
			log.error("Erro ao processar pedido: {}", e);
		}
	}
}
