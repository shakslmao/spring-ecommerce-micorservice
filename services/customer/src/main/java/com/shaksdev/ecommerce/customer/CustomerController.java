package com.shaksdev.ecommerce.customer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest customerRequest) {
        return ResponseEntity.ok(customerService.createCustomer(customerRequest));
    }

    @PutMapping
    public ResponseEntity<Void>  updateCustomer(@RequestBody @Valid CustomerRequest customerRequest) {
        customerService.updateCustomer(customerRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAll() {
        return ResponseEntity.ok(customerService.findAllCustomers());
    }

    @GetMapping("/exists/{customer-id}")
    public ResponseEntity<Boolean> customerExistsById(@PathVariable("customer-id") String customerId) {
        return ResponseEntity.ok(customerService.customerExistsById(customerId));
    }

    @GetMapping("/{customer-id}")
    public ResponseEntity<CustomerResponse> findById(@PathVariable("customer-id") String customerId) {
        return ResponseEntity.ok(customerService.findById(customerId));
    }

    @DeleteMapping("/{customer-id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("customer-id") String customerId) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.accepted().build();
    }
}
