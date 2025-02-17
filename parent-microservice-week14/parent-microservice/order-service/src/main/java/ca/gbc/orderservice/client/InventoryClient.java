package ca.gbc.orderservice.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

public interface InventoryClient {

    // Logger instance for the interface
    Logger log = LoggerFactory.getLogger(InventoryClient.class);

    // Annotated method to check inventory
    @GetExchange("/api/inventory")
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    @Retry(name = "inventory")
    boolean isInStock(@RequestParam String skuCode, @RequestParam Integer quantity);

    // Fallback method for circuit breaker
    default boolean fallbackMethod(String skuCode, Integer quantity, Throwable throwable) {
        log.warn("Cannot get inventory for SKU code {}, failure reason: {}", skuCode, throwable.getMessage());
        return false;
    }

}
