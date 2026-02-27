package com.customer.database.controller;

import com.customer.database.dto.ApiResponse;
import com.customer.database.dto.CustomerAddressRequest;
import com.customer.database.entity.CustomerAddress;
import com.customer.database.service.CustomerAddressService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer-addresses")
public class CustomerAddressController {

    private final CustomerAddressService service;

    public CustomerAddressController(CustomerAddressService service) {
        this.service = service;
    }

    @PostMapping("/insert")
    public ResponseEntity<ApiResponse<CustomerAddress>> insert(
            @Valid @RequestBody CustomerAddressRequest request) {
        CustomerAddress saved = service.insert(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Customer address record inserted successfully", saved));
    }

    @PostMapping("/logical-update")
    public ResponseEntity<ApiResponse<CustomerAddress>> logicalUpdate(
            @Valid @RequestBody CustomerAddressRequest request) {
        CustomerAddress saved = service.logicalUpdate(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Customer address record logically updated", saved));
    }

    @PostMapping("/logical-delete")
    public ResponseEntity<ApiResponse<CustomerAddress>> logicalDelete(
            @Valid @RequestBody CustomerAddressRequest request) {
        CustomerAddress saved = service.logicalDelete(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Customer address record logically deleted", saved));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CustomerAddress>>> findAll() {
        List<CustomerAddress> records = service.findAll();
        return ResponseEntity.ok(ApiResponse.success("All customer address records", records));
    }

    @GetMapping("/active")
    public ResponseEntity<ApiResponse<List<CustomerAddress>>> findAllActive() {
        List<CustomerAddress> records = service.findAllActive();
        return ResponseEntity.ok(ApiResponse.success("Active customer address records", records));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerAddress>> findById(@PathVariable Long id) {
        CustomerAddress record = service.findById(id);
        return ResponseEntity.ok(ApiResponse.success("Customer address record found", record));
    }

    @GetMapping("/customer/{customerIdentifier}")
    public ResponseEntity<ApiResponse<List<CustomerAddress>>> findByCustomerIdentifier(
            @PathVariable String customerIdentifier) {
        List<CustomerAddress> records = service.findActiveByCustomerIdentifier(customerIdentifier);
        return ResponseEntity.ok(ApiResponse.success("Customer address records for: " + customerIdentifier, records));
    }
}
