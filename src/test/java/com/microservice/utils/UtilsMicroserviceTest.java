package com.microservice.utils;

import com.microservice.model.Cliente;
import com.microservice.model.Itens;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static java.math.BigDecimal.valueOf;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UtilsMicroserviceTest {
    @Test
    void should_return_sum_total() {
        //Given
        Itens iten1 = new Itens(1, "Estojo", 10L, valueOf(10.5), new Cliente(1L, "João"));
        Itens iten2 = new Itens(2, "Borracha", 70L, valueOf(80.2), new Cliente(1L, "João"));
        List<Itens> list = List.of(iten1, iten2);

        //When
        BigDecimal result = UtilsMicroservice.somarPreco(list);

        //Then
        assertEquals(result, valueOf(90.7));
    }
}
