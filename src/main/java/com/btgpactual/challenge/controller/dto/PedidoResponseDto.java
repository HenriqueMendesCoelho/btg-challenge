package com.btgpactual.challenge.controller.dto;

import com.btgpactual.challenge.domain.model.Pedido;
import lombok.Builder;

import java.util.UUID;

@Builder
public record PedidoResponseDto(
		UUID pedidoId,
		String status
) {
	public PedidoResponseDto(Pedido pedido) {
		this(pedido.getPedidoId(), pedido.getStatus().getDescription());
	}
}
