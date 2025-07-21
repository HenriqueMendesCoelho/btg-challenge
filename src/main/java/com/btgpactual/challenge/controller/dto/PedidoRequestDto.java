package com.btgpactual.challenge.controller.dto;

import com.btgpactual.challenge.domain.model.Pedido;
import com.btgpactual.challenge.domain.model.Produto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

@Builder
public record PedidoRequestDto(
		@NotNull Long userId,
		@Valid @NotEmpty List<ProdutoRequestDto> produtos
) {

	public record ProdutoRequestDto(
			@NotNull Integer produtoId,
			@NotNull Integer quantidade
	) {
		public Produto toDomain() {
			return Produto.builder().produtoId(produtoId).quantidade(quantidade).build();
		}
	}

	public Pedido toDomain() {
		return Pedido.builder()
				.userId(userId)
				.produtos(produtos.stream().map(PedidoRequestDto.ProdutoRequestDto::toDomain).toList())
				.build();
	}
}
