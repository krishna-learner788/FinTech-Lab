package com.customer.database.controller;

import com.customer.database.dto.ApiResponse;
import com.customer.database.dto.CustomerNameRequest;
import com.customer.database.entity.CustomerName;
import com.customer.database.service.CustomerNameService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer-names")
public class CustomerNameController {

    private final CustomerNameService service;

    public CustomerNameController(CustomerNameService service) {
        this.service = service;
    }

    @PostMapping("/insert")
    public ResponseEntity<ApiResponse<CustomerName>> insert(
            @Valid @RequestBody CustomerNameRequest request) {
        CustomerName saved = service.insert(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Customer name record inserted successfully", saved));
    }

    @PostMapping("/logical-update")
    public ResponseEntity<ApiResponse<CustomerName>> logicalUpdate(
            @Valid @RequestBody CustomerNameRequest request) {
        CustomerName saved = service.logicalUpdate(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Customer name record logically updated", saved));
    }

    @PostMapping("/logical-delete")
    public ResponseEntity<ApiResponse<CustomerName>> logicalDelete(
            @Valid @RequestBody CustomerNameRequest request) {
        CustomerName saved = service.logicalDelete(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Customer name record logically deleted", saved));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CustomerName>>> findAll() {
        List<CustomerName> records = service.findAll();
        return ResponseEntity.ok(ApiResponse.success("All customer name records", records));
    }

    @GetMapping("/active")
    public ResponseEntity<ApiResponse<List<CustomerName>>> findAllActive() {
        List<CustomerName> records = service.findAllActive();
        return ResponseEntity.ok(ApiResponse.success("Active customer name records", records));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerName>> findById(@PathVariable Long id) {
        CustomerName record = service.findById(id);
        return ResponseEntity.ok(ApiResponse.success("Customer name record found", record));
    }

    @GetMapping("/customer/{customerIdentifier}")
    public ResponseEntity<ApiResponse<List<CustomerName>>> findByCustomerIdentifier(
            @PathVariable String customerIdentifier) {
        List<CustomerName> records = service.findActiveByCustomerIdentifier(customerIdentifier);
        return ResponseEntity.ok(ApiResponse.success("Customer name records for: " + customerIdentifier, records));
    }
}
