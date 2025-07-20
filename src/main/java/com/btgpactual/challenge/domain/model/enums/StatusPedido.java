package com.btgpactual.challenge.domain.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusPedido {
	PENDENTE("pendente"), PROCESSADO("processado"), NAO_ENCONTRADO("Pedido não encontrado");

	private String description;
}
