package com.sarthak.order.clients;

import com.sarthak.order.model.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "customer-service", url = "${application.config.customer-url}")
public interface CustomerClient {

    /*@PostMapping("/")
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request);

    @PutMapping("")
    public ResponseEntity<String> updateCustomer(@RequestBody @Valid CustomerRequest request);
*/
    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllCustomers();

    @GetMapping("/exists/{customerId}")
    public ResponseEntity<Boolean> existsById(@PathVariable String customerId);

    @GetMapping("/{customerId}")
    Optional<CustomerResponse> findById(@PathVariable String customerId);

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteById(@PathVariable String customerId);
}
