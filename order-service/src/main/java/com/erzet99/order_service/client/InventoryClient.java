package com.erzet99.order_service.client;

import org.springframework.web.client.RestClient;

public class InventoryClient {
    private final RestClient restClient;
    String uriBase = "http://localhost:8082";

    public InventoryClient() {
        this.restClient = RestClient.create();
    }

    public boolean isInStock(String skuCode, Integer quantity) {
        boolean result = restClient.get()
                .uri(uriBase + "/api/inventory")
                .retrieve()
                .body(Boolean.class);

        return result;
    }
}
