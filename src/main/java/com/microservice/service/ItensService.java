package com.microservice.service;

import com.microservice.model.Itens;
import com.microservice.repository.ItensRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ItensService {
    @Autowired
    private ItensRepository itensRepository;

    @Transactional
    public void salvarItens(List<Itens> itens) {
        itensRepository.saveAll(itens);

    }
}
