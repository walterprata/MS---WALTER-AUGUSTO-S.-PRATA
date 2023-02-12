package com.microservice.service;

import com.microservice.dto.ItensDto;
import com.microservice.dto.PedidoDto;
import com.microservice.model.Cliente;
import com.microservice.model.Itens;
import com.microservice.model.Pedido;
import com.microservice.repository.ClienteRepository;
import com.microservice.repository.ItensRepository;
import com.microservice.repository.PedidoRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static com.microservice.utils.UtilsMicroservice.somarPreco;

@Service
public class PedidoService {

    private Logger logger = LogManager.getLogger(PedidoService.class);

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ItensRepository itensRepository;

    @Transactional
    public void salvarPedido(Pedido pedido) {
        pedidoRepository.save(pedido);
    }

}
