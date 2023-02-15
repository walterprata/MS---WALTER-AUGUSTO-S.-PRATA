package com.microservice.service;

import com.microservice.dto.ItensDto;
import com.microservice.dto.PedidoDto;
import com.microservice.model.Cliente;
import com.microservice.model.Itens;
import com.microservice.model.Pedido;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static java.math.BigDecimal.valueOf;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProcessorServiceTest {
    private static ClienteService clienteService;
    private static ItensService itensService;
    private static PedidoService pedidoService;
    private static ProcessorService processorService;

    @BeforeAll
    static void setup(){
        clienteService = mock(ClienteService.class);
        itensService = mock(ItensService.class);
        pedidoService = mock(PedidoService.class);
        processorService = new ProcessorService(clienteService, itensService, pedidoService);
    }
    @Test
    void should_check_the_calls_of_the_repositories(){
        //Given
        PedidoDto pedidoDto = createPedidoDto();

        //When
        processorService.convertAndSave(pedidoDto);
        //Then
        verify(clienteService, times(1)).salvarCliente(any());
        verify(itensService, times(1)).salvarItens(anyList());
        verify(pedidoService, times(1)).salvarPedido(any());
    }

    @Test
    void should_build_pedido_dto(){
        //Given
        PedidoDto pedidoDto = createPedidoDto();
        long expectedCodCliente = pedidoDto.getCodigoCliente();
        long expectedCodPedido = pedidoDto.getCodigoPedido();
        String expectedNome = pedidoDto.getNome();
        BigDecimal expectedValorTotal = valueOf(90.7);
        ItensDto iten1 = pedidoDto.getItens().get(0);
        ItensDto iten2 = pedidoDto.getItens().get(1);


        //When
        Pedido pedido = processorService.convertAndSave(pedidoDto);
        List<Itens> itensList = pedido.getItens();

        //Then
        assertEquals(expectedCodCliente, pedido.getCliente().getCodigoCliente());
        assertEquals(expectedCodPedido, pedido.getCodigoPedido());
        assertEquals(expectedNome, pedido.getCliente().getNome());
        assertEquals(expectedValorTotal, pedido.getValorTotal());
        assertEquals(iten1.getPreco(), itensList.get(0).getPreco());
        assertEquals(iten1.getQuantidade(), itensList.get(0).getQuantidade());
        assertEquals(iten1.getProduto(), itensList.get(0).getProduto());
        assertEquals(iten2.getPreco(), itensList.get(1).getPreco());
        assertEquals(iten2.getQuantidade(), itensList.get(1).getQuantidade());
        assertEquals(iten2.getProduto(), itensList.get(1).getProduto());

    }

    public PedidoDto createPedidoDto(){
        ItensDto iten1 = new ItensDto( "Estojo", 10L, valueOf(10.5) );
        ItensDto iten2 = new ItensDto("Borracha", 70L, valueOf(80.2));
        List<ItensDto> list = List.of(iten1, iten2);
        return new PedidoDto(1020L, 1L, "João", list );
    }

    public Pedido createPedido(){
        long codigoPedido = 200;
        BigDecimal valorTotal = valueOf(10.0);
        Itens iten1 = new Itens(1, "Estojo", 10L, valueOf(10.5), new Cliente(1L, "João"));
        Itens iten2 = new Itens(2, "Borracha", 70L, valueOf(80.2), new Cliente(1L, "João"));
        List<Itens> list = List.of(iten1, iten2);

        return Pedido.builder()
                .id(1)
                .codigoPedido(codigoPedido)
                .valorTotal(valueOf(90.7))
                .cliente(new Cliente(1L,"João"))
                .itens(list)
                .build();

    }
}
