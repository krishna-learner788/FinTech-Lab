package com.customer.database.controller;

import com.customer.database.dto.ApiResponse;
import com.customer.database.dto.CustomerDetailsRequest;
import com.customer.database.entity.CustomerDetails;
import com.customer.database.service.CustomerDetailsService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer-details")
public class CustomerDetailsController {

    private final CustomerDetailsService service;

    public CustomerDetailsController(CustomerDetailsService service) {
        this.service = service;
    }

    @PostMapping("/insert")
    public ResponseEntity<ApiResponse<CustomerDetails>> insert(
            @Valid @RequestBody CustomerDetailsRequest request) {
        CustomerDetails saved = service.insert(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Customer details record inserted successfully", saved));
    }

    @PostMapping("/logical-update")
    public ResponseEntity<ApiResponse<CustomerDetails>> logicalUpdate(
            @Valid @RequestBody CustomerDetailsRequest request) {
        CustomerDetails saved = service.logicalUpdate(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Customer details record logically updated", saved));
    }

    @PostMapping("/logical-delete")
    public ResponseEntity<ApiResponse<CustomerDetails>> logicalDelete(
            @Valid @RequestBody CustomerDetailsRequest request) {
        CustomerDetails saved = service.logicalDelete(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Customer details record logically deleted", saved));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CustomerDetails>>> findAll() {
        List<CustomerDetails> records = service.findAll();
        return ResponseEntity.ok(ApiResponse.success("All customer details records", records));
    }

    @GetMapping("/active")
    public ResponseEntity<ApiResponse<List<CustomerDetails>>> findAllActive() {
        List<CustomerDetails> records = service.findAllActive();
        return ResponseEntity.ok(ApiResponse.success("Active customer details records", records));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerDetails>> findById(@PathVariable Long id) {
        CustomerDetails record = service.findById(id);
        return ResponseEntity.ok(ApiResponse.success("Customer details record found", record));
    }

    @GetMapping("/customer/{customerNumber}")
    public ResponseEntity<ApiResponse<List<CustomerDetails>>> findByCustomerNumber(
            @PathVariable String customerNumber) {
        List<CustomerDetails> records = service.findActiveByCustomerNumber(customerNumber);
        return ResponseEntity.ok(ApiResponse.success("Customer details for: " + customerNumber, records));
    }
}
