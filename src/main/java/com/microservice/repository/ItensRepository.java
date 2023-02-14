package com.microservice.repository;

import com.microservice.model.Itens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItensRepository extends JpaRepository<Itens, Long> {

    List<Itens> findAllByClienteCodigoCliente(Long id);
}
