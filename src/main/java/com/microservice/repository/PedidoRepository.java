package com.microservice.repository;

import com.microservice.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    Pedido findByCodigoPedido(Long codigoPedido);
    Long countByClienteCodigoCliente(Long CodigoCliente);
}
