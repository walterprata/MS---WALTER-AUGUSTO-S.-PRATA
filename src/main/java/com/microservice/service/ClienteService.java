package com.microservice.service;

import com.microservice.model.Cliente;
import com.microservice.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente consultarCliente(Long id) {
        return clienteRepository.findByCodigoCliente(id);
    }

    @Transactional
    public void salvarCliente(Cliente cliente) {
        clienteRepository.save(cliente);
    }

}

