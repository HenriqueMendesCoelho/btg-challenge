package com.btgpactual.challenge.infrastructure.storage;

import com.btgpactual.challenge.domain.model.enums.StatusPedido;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class PedidosStatusStore {
	private ConcurrentHashMap<UUID, StatusPedido> statusMap = new ConcurrentHashMap<>();

	public void adicionarPedido(UUID pedidoId) {
		statusMap.put(pedidoId, StatusPedido.PENDENTE);
	}

	public void atualizarStatus(UUID pedidoId, StatusPedido status) {
		statusMap.put(pedidoId, status);
	}

	public String consultarStatus(UUID pedidoId) {
		return statusMap.getOrDefault(pedidoId, StatusPedido.NAO_ENCONTRADO).getDescription();
	}
}
