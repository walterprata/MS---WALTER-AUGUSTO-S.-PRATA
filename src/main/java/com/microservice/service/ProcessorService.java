package com.microservice.service;

import com.microservice.dto.ItensDto;
import com.microservice.dto.PedidoDto;
import com.microservice.model.Cliente;
import com.microservice.model.Itens;
import com.microservice.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.microservice.utils.UtilsMicroservice.somarPreco;

@Service
public class ProcessorService {

    private final ClienteService clienteService;
    private final ItensService itensService;
    private final PedidoService pedidoService;

    @Autowired
    public ProcessorService(ClienteService clienteService, ItensService itensService, PedidoService pedidoService) {
        this.clienteService = clienteService;
        this.itensService = itensService;
        this.pedidoService = pedidoService;
    }

    public Pedido convertAndSave(PedidoDto pedidoDto) {
        List<Itens> itensList = new ArrayList<>();
        Cliente cliente = new Cliente();
        cliente.setCodigoCliente(pedidoDto.getCodigoCliente());
        cliente.setNome(pedidoDto.getNome());
        clienteService.salvarCliente(cliente);

        Pedido pedido = new Pedido();
        pedido.setCodigoPedido(pedidoDto.getCodigoPedido());
        pedido.setCliente(cliente);

        for (ItensDto itensDto : pedidoDto.getItens()) {
            Itens itens = new Itens();
            itens.setProduto(itensDto.getProduto());
            itens.setPreco(itensDto.getPreco());
            itens.setQuantidade(itensDto.getQuantidade());
            itens.setCliente(cliente);
            itensList.add(itens);
        }

        itensService.salvarItens(itensList);
        pedido.setItens(itensList);
        pedido.setValorTotal(somarPreco(itensList));
        pedidoService.salvarPedido(pedido);
        return pedido;
    }
}
