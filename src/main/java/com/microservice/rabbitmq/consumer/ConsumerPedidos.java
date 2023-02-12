package com.microservice.rabbitmq.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.dto.PedidoDto;
import com.microservice.service.ProcessorService;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class ConsumerPedidos {

    private final Logger logger = LogManager.getLogger(ConsumerPedidos.class);
    private ProcessorService processorService;

    public ConsumerPedidos(ProcessorService processorService) {
        this.processorService = processorService;
    }

    @RabbitListener(queues = "${mq.queue.pedidos-queue}")
    public void receberPedido(@Payload String pedido) {
        var mapper = new ObjectMapper();
        try {
            var json = mapper.readValue(pedido, PedidoDto.class);
            logger.info("Message: {}", json);
            processorService.convertAndSave(json);


        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}
