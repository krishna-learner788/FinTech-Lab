package com.customer.database.controller;

import com.customer.database.dto.ApiResponse;
import com.customer.database.dto.CustomerProofOfIdentityRequest;
import com.customer.database.entity.CustomerProofOfIdentity;
import com.customer.database.service.CustomerProofOfIdentityService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/proof-of-identity")
public class CustomerProofOfIdentityController {

    private final CustomerProofOfIdentityService service;

    public CustomerProofOfIdentityController(CustomerProofOfIdentityService service) {
        this.service = service;
    }

    @PostMapping("/insert")
    public ResponseEntity<ApiResponse<CustomerProofOfIdentity>> insert(
            @Valid @RequestBody CustomerProofOfIdentityRequest request) {
        CustomerProofOfIdentity saved = service.insert(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Proof of identity record inserted successfully", saved));
    }

    @PostMapping("/logical-update")
    public ResponseEntity<ApiResponse<CustomerProofOfIdentity>> logicalUpdate(
            @Valid @RequestBody CustomerProofOfIdentityRequest request) {
        CustomerProofOfIdentity saved = service.logicalUpdate(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Proof of identity record logically updated", saved));
    }

    @PostMapping("/logical-delete")
    public ResponseEntity<ApiResponse<CustomerProofOfIdentity>> logicalDelete(
            @Valid @RequestBody CustomerProofOfIdentityRequest request) {
        CustomerProofOfIdentity saved = service.logicalDelete(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Proof of identity record logically deleted", saved));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CustomerProofOfIdentity>>> findAll() {
        List<CustomerProofOfIdentity> records = service.findAll();
        return ResponseEntity.ok(ApiResponse.success("All proof of identity records", records));
    }

    @GetMapping("/active")
    public ResponseEntity<ApiResponse<List<CustomerProofOfIdentity>>> findAllActive() {
        List<CustomerProofOfIdentity> records = service.findAllActive();
        return ResponseEntity.ok(ApiResponse.success("Active proof of identity records", records));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerProofOfIdentity>> findById(@PathVariable Long id) {
        CustomerProofOfIdentity record = service.findById(id);
        return ResponseEntity.ok(ApiResponse.success("Proof of identity record found", record));
    }

    @GetMapping("/customer/{customerIdentifier}")
    public ResponseEntity<ApiResponse<List<CustomerProofOfIdentity>>> findByCustomerIdentifier(
            @PathVariable String customerIdentifier) {
        List<CustomerProofOfIdentity> records = service.findActiveByCustomerIdentifier(customerIdentifier);
        return ResponseEntity.ok(ApiResponse.success("Proof of identity records for: " + customerIdentifier, records));
    }
}
