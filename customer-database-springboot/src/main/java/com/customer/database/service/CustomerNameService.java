package com.customer.database.service;

import com.customer.database.dto.CustomerNameRequest;
import com.customer.database.entity.CustomerName;
import com.customer.database.repository.CustomerNameRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class CustomerNameService {

    private final CustomerNameRepository repository;

    public CustomerNameService(CustomerNameRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public CustomerName insert(CustomerNameRequest request) {
        CustomerName entity = CustomerName.builder()
                .customerIdentifier(request.getCustomerIdentifier())
                .nameComponentTypeClassificationId(request.getNameComponentTypeClassificationId())
                .customerNameValue(request.getCustomerNameValue())
                .effectiveDate(LocalDate.parse(request.getEffectiveDate()))
                .crudValue(request.getCrudValue())
                .auditFields(AuditFieldsMapper.toEntity(request.getAuditFields()))
                .build();
        return repository.save(entity);
    }

    public List<CustomerName> findAll() {
        return repository.findAll();
    }

    public CustomerName findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer name not found with ID: " + id));
    }

    public List<CustomerName> findAllActive() {
        return repository.findAllActive();
    }

    public List<CustomerName> findActiveByCustomerIdentifier(String customerIdentifier) {
        return repository.findActiveByCustomerIdentifier(customerIdentifier);
    }

    @Transactional
    public CustomerName logicalUpdate(CustomerNameRequest request) {
        request.setCrudValue("U");
        return insert(request);
    }

    @Transactional
    public CustomerName logicalDelete(CustomerNameRequest request) {
        request.setCrudValue("D");
        return insert(request);
    }
}
