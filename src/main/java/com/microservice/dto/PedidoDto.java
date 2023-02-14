package com.microservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDto {

    private Long codigoPedido;
    private Long codigoCliente;

    private String nome;
    private List<ItensDto> itens = new ArrayList<>();
}
