package com.erzet99.order_service.client;

import org.springframework.web.client.RestClient;

public class InventoryClient {
    private final RestClient restClient;
    String uriBase = "http://localhost:8082";

    public InventoryClient() {
        this.restClient = RestClient.create();
    }

    public boolean isInStock(String skuCode, Integer quantity) {
        return restClient.get()
                .uri(uriBase + "/api/inventory?skuCode={skuCode}&quantity={quantity}", skuCode, quantity)
                .retrieve()
                .body(Boolean.class);
    }
}
