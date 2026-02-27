package com.customer.database.service;

import com.customer.database.dto.CustomerIdentificationRequest;
import com.customer.database.entity.CustomerIdentification;
import com.customer.database.repository.CustomerIdentificationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class CustomerIdentificationService {

    private final CustomerIdentificationRepository repository;

    public CustomerIdentificationService(CustomerIdentificationRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public CustomerIdentification insert(CustomerIdentificationRequest request) {
        CustomerIdentification entity = CustomerIdentification.builder()
                .identificationType(request.getIdentificationType())
                .identificationItem(request.getIdentificationItem())
                .effectiveDate(LocalDate.parse(request.getEffectiveDate()))
                .crudValue(request.getCrudValue())
                .auditFields(AuditFieldsMapper.toEntity(request.getAuditFields()))
                .build();
        return repository.save(entity);
    }

    public List<CustomerIdentification> findAll() {
        return repository.findAll();
    }

    public CustomerIdentification findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Identification not found with ID: " + id));
    }

    public List<CustomerIdentification> findAllActive() {
        return repository.findAllActive();
    }

    public List<CustomerIdentification> findByType(String type) {
        return repository.findByIdentificationType(type);
    }

    @Transactional
    public CustomerIdentification logicalUpdate(CustomerIdentificationRequest request) {
        request.setCrudValue("U");
        return insert(request);
    }

    @Transactional
    public CustomerIdentification logicalDelete(CustomerIdentificationRequest request) {
        request.setCrudValue("D");
        return insert(request);
    }
}
