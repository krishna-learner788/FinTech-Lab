package com.customer.database.controller;

import com.customer.database.dto.ApiResponse;
import com.customer.database.dto.CustomerClassificationRequest;
import com.customer.database.entity.CustomerClassification;
import com.customer.database.service.CustomerClassificationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for Customer Classification (CSTCL)
 * Base URL: /api/v1/classifications
 *
 * Insert-Only Paradigm:
 * - POST /insert         → Insert new record (C/U/D based on crudValue)
 * - POST /logical-update → Insert with CRUD_VALUE = 'U'
 * - POST /logical-delete → Insert with CRUD_VALUE = 'D'
 * - GET  /               → Get all records (full history)
 * - GET  /active          → Get active records only (CRUD_VALUE != 'D')
 * - GET  /{id}           → Get record by ID
 * - GET  /type/{type}    → Get by classification type
 */
@RestController
@RequestMapping("/api/v1/classifications")
public class CustomerClassificationController {

    private final CustomerClassificationService service;

    public CustomerClassificationController(CustomerClassificationService service) {
        this.service = service;
    }

    @PostMapping("/insert")
    public ResponseEntity<ApiResponse<CustomerClassification>> insert(
            @Valid @RequestBody CustomerClassificationRequest request) {
        CustomerClassification saved = service.insert(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Classification record inserted successfully", saved));
    }

    @PostMapping("/logical-update")
    public ResponseEntity<ApiResponse<CustomerClassification>> logicalUpdate(
            @Valid @RequestBody CustomerClassificationRequest request) {
        CustomerClassification saved = service.logicalUpdate(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Classification record logically updated (new row inserted with U)", saved));
    }

    @PostMapping("/logical-delete")
    public ResponseEntity<ApiResponse<CustomerClassification>> logicalDelete(
            @Valid @RequestBody CustomerClassificationRequest request) {
        CustomerClassification saved = service.logicalDelete(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Classification record logically deleted (new row inserted with D)", saved));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CustomerClassification>>> findAll() {
        List<CustomerClassification> records = service.findAll();
        return ResponseEntity.ok(ApiResponse.success("All classification records (full history)", records));
    }

    @GetMapping("/active")
    public ResponseEntity<ApiResponse<List<CustomerClassification>>> findAllActive() {
        List<CustomerClassification> records = service.findAllActive();
        return ResponseEntity.ok(ApiResponse.success("Active classification records", records));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerClassification>> findById(@PathVariable Long id) {
        CustomerClassification record = service.findById(id);
        return ResponseEntity.ok(ApiResponse.success("Classification record found", record));
    }

    @GetMapping("/type/{classificationType}")
    public ResponseEntity<ApiResponse<List<CustomerClassification>>> findByType(
            @PathVariable String classificationType) {
        List<CustomerClassification> records = service.findByType(classificationType);
        return ResponseEntity.ok(ApiResponse.success("Classification records for type: " + classificationType, records));
    }

    @GetMapping("/type/{classificationType}/active")
    public ResponseEntity<ApiResponse<List<CustomerClassification>>> findActiveByType(
            @PathVariable String classificationType) {
        List<CustomerClassification> records = service.findActiveByType(classificationType);
        return ResponseEntity.ok(ApiResponse.success("Active classification records for type: " + classificationType, records));
    }
}
