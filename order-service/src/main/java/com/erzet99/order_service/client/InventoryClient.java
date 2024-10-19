package com.erzet99.order_service.client;

import org.springframework.web.client.RestClient;

public class InventoryClient {
    private final RestClient restClient;
    String uriBase = "http://localhost:8082/api/inventory";

    public InventoryClient() {
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
