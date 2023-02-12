package com.microservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Long codigoPedido;
    private BigDecimal valorTotal;
    @ManyToOne
    @JoinColumn(name= "cliente_id")
    private Cliente cliente;
    @OneToMany(mappedBy = "id")
    private List<Itens> itens;

}


