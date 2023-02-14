package com.microservice.controller;

import com.microservice.exceptions.ObjectNotFoundException;
import com.microservice.model.Pedido;
import com.microservice.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/{id}")
    public ResponseEntity<String> consultarValorPedido(@PathVariable("id") Long id) {
        try {
            Pedido pedido = pedidoService.buscarPedido(id);
            BigDecimal valorTotal = pedido.getValorTotal();
            return ResponseEntity.ok("Valor Total: " + valorTotal);
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não encontrado: " + id);
        }
    }

    @GetMapping("/quantidade/{id}")
    public ResponseEntity<String> consultarPedidosPorCliente(@PathVariable("id") Long id) {
        try {
            Long qtdPedido = pedidoService.consultarQuantidadePedidoPorCliente(id);

            return ResponseEntity.ok("Quantidade de pedidos realizados: " + qtdPedido);
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado: " + id);
        }
    }


}
