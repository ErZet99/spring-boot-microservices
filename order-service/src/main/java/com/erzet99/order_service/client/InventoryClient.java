package com.erzet99.order_service.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class InventoryClient {
    private final RestClient restClient;

    public InventoryClient(@Value("${inventory.url}/api/inventory") String uriBase) {
        this.restClient = RestClient.builder()
                .baseUrl(uriBase)
                .build();
    }

    public boolean isInStock(String skuCode, Integer quantity) {
        try {
            return Boolean.TRUE.equals(restClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .queryParam("skuCode", skuCode)
                            .queryParam("quantity", quantity)
                            .build())
                    .header("Content-Type", "application/json")
                    .retrieve()
                    .body(Boolean.class));
        } catch (Exception e) {
            System.err.println("Failed to check stock: " + e.getMessage());
            return false;
        }
    }
}
