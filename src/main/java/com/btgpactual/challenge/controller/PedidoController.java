package com.btgpactual.challenge.controller;

import com.btgpactual.challenge.controller.dto.PedidoRequestDto;
import com.btgpactual.challenge.controller.dto.PedidoResponseDto;
import com.btgpactual.challenge.domain.model.Pedido;
import com.btgpactual.challenge.domain.service.PedidoService;
import com.btgpactual.challenge.exception.PedidoNaoEncontradoException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {

	private final PedidoService service;

	@PostMapping
	public ResponseEntity<PedidoResponseDto> createPedido(@RequestBody @Valid PedidoRequestDto request) {
		Pedido pedido = service.criarPedido(request.toDomain());

		return ResponseEntity.ok().body(new PedidoResponseDto(pedido));
	}

	@GetMapping("/{id}")
	public ResponseEntity<PedidoResponseDto> statusPedido(@PathVariable UUID id) {
		try {
			Pedido pedido = service.consultarStatus(id);
			return ResponseEntity.ok().body(new PedidoResponseDto(pedido));
		} catch (PedidoNaoEncontradoException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
