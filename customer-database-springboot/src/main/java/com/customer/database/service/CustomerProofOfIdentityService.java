package com.customer.database.service;

import com.customer.database.dto.CustomerProofOfIdentityRequest;
import com.customer.database.entity.CustomerProofOfIdentity;
import com.customer.database.repository.CustomerProofOfIdentityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class CustomerProofOfIdentityService {

    private final CustomerProofOfIdentityRepository repository;

    public CustomerProofOfIdentityService(CustomerProofOfIdentityRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public CustomerProofOfIdentity insert(CustomerProofOfIdentityRequest request) {
        CustomerProofOfIdentity entity = CustomerProofOfIdentity.builder()
                .customerIdentifier(request.getCustomerIdentifier())
                .proofTypeClassificationId(request.getProofTypeClassificationId())
                .classificationTypeValue(request.getClassificationTypeValue())
                .startDate(LocalDate.parse(request.getStartDate()))
                .endDate(request.getEndDate() != null ? LocalDate.parse(request.getEndDate()) : null)
                .effectiveDate(LocalDate.parse(request.getEffectiveDate()))
                .crudValue(request.getCrudValue())
                .auditFields(AuditFieldsMapper.toEntity(request.getAuditFields()))
                .build();
        return repository.save(entity);
    }

    public List<CustomerProofOfIdentity> findAll() {
        return repository.findAll();
    }

    public CustomerProofOfIdentity findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proof of identity not found with ID: " + id));
    }

    public List<CustomerProofOfIdentity> findAllActive() {
        return repository.findAllActive();
    }

    public List<CustomerProofOfIdentity> findActiveByCustomerIdentifier(String customerIdentifier) {
        return repository.findActiveByCustomerIdentifier(customerIdentifier);
    }

    @Transactional
    public CustomerProofOfIdentity logicalUpdate(CustomerProofOfIdentityRequest request) {
        request.setCrudValue("U");
        return insert(request);
    }

    @Transactional
    public CustomerProofOfIdentity logicalDelete(CustomerProofOfIdentityRequest request) {
        request.setCrudValue("D");
        return insert(request);
    }
}
