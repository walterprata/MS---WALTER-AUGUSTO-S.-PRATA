package com.microservice.utils;

import com.microservice.model.Itens;

import java.math.BigDecimal;
import java.util.List;

public abstract class UtilsMicroservice {
    public static BigDecimal somarPreco(List<Itens> itens) {
        return itens.stream().map(Itens::getPreco).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
