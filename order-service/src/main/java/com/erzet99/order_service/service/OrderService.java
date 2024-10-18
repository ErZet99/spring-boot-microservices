package com.erzet99.order_service.service;

import com.erzet99.order_service.client.InventoryClient;
import com.erzet99.order_service.dto.OrderRequest;
import com.erzet99.order_service.model.Order;
import com.erzet99.order_service.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
        this.inventoryClient = new InventoryClient();
    }

    public void placeOrder(OrderRequest orderRequest) {
        var isProductInStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());

        if (isProductInStock) {
            // map OrderRequest to Order object
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setPrice(orderRequest.price());
            order.setSkuCode(orderRequest.skuCode());
            order.setQuantity(orderRequest.quantity());
            // save order to OrderRepository
            orderRepository.save(order);
        } else {
            throw new RuntimeException("Product with SkuCode" + orderRequest.skuCode() + " is not in stock");
        }

    }

}
