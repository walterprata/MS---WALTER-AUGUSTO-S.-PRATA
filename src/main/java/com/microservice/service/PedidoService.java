package com.microservice.service;

import com.microservice.exceptions.ObjectNotFoundException;
import com.microservice.model.Pedido;
import com.microservice.repository.ClienteRepository;
import com.microservice.repository.ItensRepository;
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

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ItensRepository itensRepository;

    public Pedido buscarPedido(Long id) throws ObjectNotFoundException {
        Pedido pedido = pedidoRepository.findByCodigoPedido(id);
        if (isNull(pedido)) {
            throw new ObjectNotFoundException("Pedido não encontrado! : " + id);
        }
        return pedido;
    }

    @Transactional
    public void salvarPedido(Pedido pedido) {
        pedidoRepository.save(pedido);
    }


}
