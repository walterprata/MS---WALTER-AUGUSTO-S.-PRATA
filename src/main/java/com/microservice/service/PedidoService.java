package com.microservice.service;

import com.microservice.exceptions.ObjectNotFoundException;
import com.microservice.model.Cliente;
import com.microservice.model.Pedido;
import com.microservice.repository.PedidoRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static java.util.Objects.isNull;

@Service
public class PedidoService {

    private Logger logger = LogManager.getLogger(PedidoService.class);

    private ClienteService clienteService;
    private PedidoRepository pedidoRepository;

    @Autowired
    public PedidoService(ClienteService clienteService, PedidoRepository pedidoRepository) {
        this.clienteService = clienteService;
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido buscarPedido(Long id) throws ObjectNotFoundException {
        Pedido pedido = pedidoRepository.findByCodigoPedido(id);
        if (isNull(pedido)) {
            throw new ObjectNotFoundException("Pedido não encontrado! : " + id);
        }
        return pedido;
    }

    public Long consultarQuantidadePedidoPorCliente(Long id) throws ObjectNotFoundException {
        Cliente cliente = clienteService.consultarCliente(id);
        if (isNull(cliente)) {
            throw new ObjectNotFoundException("Cliente não encontrado! : " + id);
        }

        return pedidoRepository.countByClienteCodigoCliente(id);
    }

    @Transactional
    public void salvarPedido(Pedido pedido) {
        pedidoRepository.save(pedido);
    }


}
