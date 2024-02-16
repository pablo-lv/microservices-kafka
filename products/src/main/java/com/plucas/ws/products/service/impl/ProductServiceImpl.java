package com.plucas.ws.products.service.impl;

import com.plucas.ws.products.dto.ProductRequestDTO;
import com.plucas.ws.products.events.ProductCreatedEvent;
import com.plucas.ws.products.service.IProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class ProductServiceImpl implements IProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;

    public ProductServiceImpl(KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public String createProduct(ProductRequestDTO product) throws Exception {
        var productId = UUID.randomUUID().toString();
        //TODO: persist product details into database table before publishing an Event

        var event = new ProductCreatedEvent(productId, product.getTitle(), product.getPrice(), product.getQuantity());

        SendResult<String, ProductCreatedEvent> result = kafkaTemplate.send("product-created-events-topic", productId, event).get();
        log.info("ProductCreatedEvent published successfully: Topic: " + result.getRecordMetadata().topic()+ " - Partition: " + result.getRecordMetadata().partition() + " - Offset: " + result.getRecordMetadata().offset());

//        CompletableFuture<SendResult<String, ProductCreatedEvent>> future =
//                kafkaTemplate.send("product-created-events-topic", productId, event);
//
//        future.whenComplete((result, ex) -> {
//            if (ex == null) {
//                log.info("ProductCreatedEvent published successfully");
//            } else {
//                log.error("ProductCreatedEvent failed to publish" + ex.getMessage());
//            }
//        });
//        Synchronous way of waiting for the future to complete
//        future.join();

        log.info("Returning productId: " + productId + " from createProduct method");

        return productId;
    }
}
