package com.customer.database.controller;

import com.customer.database.dto.ApiResponse;
import com.customer.database.dto.CustomerIdentificationRequest;
import com.customer.database.entity.CustomerIdentification;
import com.customer.database.service.CustomerIdentificationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/identifications")
public class CustomerIdentificationController {

    private final CustomerIdentificationService service;

    public CustomerIdentificationController(CustomerIdentificationService service) {
        this.service = service;
    }

    @PostMapping("/insert")
    public ResponseEntity<ApiResponse<CustomerIdentification>> insert(
            @Valid @RequestBody CustomerIdentificationRequest request) {
        CustomerIdentification saved = service.insert(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Identification record inserted successfully", saved));
    }

    @PostMapping("/logical-update")
    public ResponseEntity<ApiResponse<CustomerIdentification>> logicalUpdate(
            @Valid @RequestBody CustomerIdentificationRequest request) {
        CustomerIdentification saved = service.logicalUpdate(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Identification record logically updated", saved));
    }

    @PostMapping("/logical-delete")
    public ResponseEntity<ApiResponse<CustomerIdentification>> logicalDelete(
            @Valid @RequestBody CustomerIdentificationRequest request) {
        CustomerIdentification saved = service.logicalDelete(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Identification record logically deleted", saved));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CustomerIdentification>>> findAll() {
        List<CustomerIdentification> records = service.findAll();
        return ResponseEntity.ok(ApiResponse.success("All identification records", records));
    }

    @GetMapping("/active")
    public ResponseEntity<ApiResponse<List<CustomerIdentification>>> findAllActive() {
        List<CustomerIdentification> records = service.findAllActive();
        return ResponseEntity.ok(ApiResponse.success("Active identification records", records));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerIdentification>> findById(@PathVariable Long id) {
        CustomerIdentification record = service.findById(id);
        return ResponseEntity.ok(ApiResponse.success("Identification record found", record));
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<ApiResponse<List<CustomerIdentification>>> findByType(@PathVariable String type) {
        List<CustomerIdentification> records = service.findByType(type);
        return ResponseEntity.ok(ApiResponse.success("Identification records for type: " + type, records));
    }
}
