package com.talanta.ecommerce.customer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request) {
        return ResponseEntity.ok(customerService.createCustomer(request));
    }

    @PutMapping("{customer-id}")
    public ResponseEntity<Void> updateCustomer(
            @PathVariable("customer-id") String customerId,
            @RequestBody @Valid CustomerRequest request
    ) {
        customerService.updateCustomer(customerId, request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("{customer-id}")
    public ResponseEntity<CustomerDTO> getSingleCustomer(@PathVariable("customer-id") String customerId) {
        return ResponseEntity.ok(customerService.getSingleCustomer(customerId));
    }

    @DeleteMapping("{customer-id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("customer-id") String customerId) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.accepted().build();
    }
}
