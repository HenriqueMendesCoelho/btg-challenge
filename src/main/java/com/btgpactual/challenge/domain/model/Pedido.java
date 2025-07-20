package com.btgpactual.challenge.domain.model;

import com.btgpactual.challenge.domain.model.enums.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Pedido {

	@Builder.Default
	private UUID pedidoId = UUID.randomUUID();
	private Long userId;
	private StatusPedido status;
	private List<Produto> produtos;

}
