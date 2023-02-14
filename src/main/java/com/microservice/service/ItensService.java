package com.microservice.service;

import com.microservice.dto.ItensDto;
import com.microservice.exceptions.ObjectNotFoundException;
import com.microservice.model.Cliente;
import com.microservice.model.Itens;
import com.microservice.repository.ItensRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class ItensService {

    private ItensRepository itensRepository;
    private ClienteService clienteService;

    public ItensService(ItensRepository itensRepository, ClienteService clienteService) {
        this.itensRepository = itensRepository;
        this.clienteService = clienteService;
    }

    public List<ItensDto> listarItensPorCliente(Long id) throws ObjectNotFoundException {
        Cliente cliente = clienteService.consultarCliente(id);
        if (isNull(cliente)) {
            throw new ObjectNotFoundException("Cliente não encontrado! : " + id);
        }

        List<Itens> itensList = itensRepository.findAllByClienteCodigoCliente(id);
        List<ItensDto> itensDtoList = itensList.stream().map(this::fromDTO).collect(Collectors.toList());
        return itensDtoList;
    }

    @Transactional
    public void salvarItens(List<Itens> itens) {
        itensRepository.saveAll(itens);

    }

    public ItensDto fromDTO(Itens itens) {
        ItensDto itensDto = new ItensDto();
        BeanUtils.copyProperties(itens, itensDto);
        return itensDto;
    }
}
