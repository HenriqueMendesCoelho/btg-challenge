package com.btgpactual.challenge.domain.service;

import com.btgpactual.challenge.domain.model.Pedido;
import com.btgpactual.challenge.domain.model.enums.StatusPedido;
import com.btgpactual.challenge.exception.PedidoNaoEncontradoException;
import com.btgpactual.challenge.infrastructure.messaging.PedidoSender;
import com.btgpactual.challenge.infrastructure.storage.PedidosStatusStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PedidoService {
	private static final PedidosStatusStore store = new PedidosStatusStore();
	private final PedidoSender pedidoSender;

	public Pedido criarPedido(Pedido pedido) {
		pedido.setStatus(StatusPedido.PENDENTE);
		store.adicionarPedido(pedido.getPedidoId());
		pedidoSender.send(pedido.getPedidoId());

		return pedido;
	}

	public Pedido consultarStatus(UUID pedidoId) throws PedidoNaoEncontradoException {
		StatusPedido status = store.consultarStatus(pedidoId);
		if (StatusPedido.NAO_ENCONTRADO.equals(status)) {
			throw new PedidoNaoEncontradoException();
		}

		return Pedido.builder().pedidoId(pedidoId).status(status).build();
	}

	public void processarPedido(UUID pedidoId) throws InterruptedException {
		Thread.sleep(5000);
		store.atualizarStatus(pedidoId, StatusPedido.PROCESSADO);
	}
}
