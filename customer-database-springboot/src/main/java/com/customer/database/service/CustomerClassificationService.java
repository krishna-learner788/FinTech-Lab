package com.customer.database.service;

import com.customer.database.dto.CustomerClassificationRequest;
import com.customer.database.entity.CustomerClassification;
import com.customer.database.repository.CustomerClassificationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class CustomerClassificationService {

    private final CustomerClassificationRepository repository;

    public CustomerClassificationService(CustomerClassificationRepository repository) {
        this.repository = repository;
    }

    /**
     * INSERT a new classification record (insert-only paradigm).
     */
    @Transactional
    public CustomerClassification insert(CustomerClassificationRequest request) {
        CustomerClassification entity = CustomerClassification.builder()
                .classificationType(request.getClassificationType())
                .classificationTypeValue(request.getClassificationTypeValue())
                .effectiveDate(LocalDate.parse(request.getEffectiveDate()))
                .crudValue(request.getCrudValue())
                .auditFields(AuditFieldsMapper.toEntity(request.getAuditFields()))
                .build();
        return repository.save(entity);
    }

    /**
     * Get all records (full history).
     */
    public List<CustomerClassification> findAll() {
        return repository.findAll();
    }

    /**
     * Get record by ID.
     */
    public CustomerClassification findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Classification not found with ID: " + id));
    }

    /**
     * Get all active records (CRUD_VALUE != 'D').
     */
    public List<CustomerClassification> findAllActive() {
        return repository.findAllActive();
    }

    /**
     * Get active records by classification type.
     */
    public List<CustomerClassification> findActiveByType(String classificationType) {
        return repository.findActiveByType(classificationType);
    }

    /**
     * Get all records by type (full history including deleted).
     */
    public List<CustomerClassification> findByType(String classificationType) {
        return repository.findByClassificationType(classificationType);
    }

    /**
     * Logical UPDATE: inserts a new row with CRUD_VALUE = 'U'.
     */
    @Transactional
    public CustomerClassification logicalUpdate(CustomerClassificationRequest request) {
        request.setCrudValue("U");
        return insert(request);
    }

    /**
     * Logical DELETE: inserts a new row with CRUD_VALUE = 'D'.
     */
    @Transactional
    public CustomerClassification logicalDelete(CustomerClassificationRequest request) {
        request.setCrudValue("D");
        return insert(request);
    }
}
