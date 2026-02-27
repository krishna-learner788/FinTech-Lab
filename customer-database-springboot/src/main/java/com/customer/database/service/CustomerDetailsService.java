package com.customer.database.service;

import com.customer.database.dto.CustomerDetailsRequest;
import com.customer.database.entity.CustomerDetails;
import com.customer.database.repository.CustomerDetailsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class CustomerDetailsService {

    private final CustomerDetailsRepository repository;

    public CustomerDetailsService(CustomerDetailsRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public CustomerDetails insert(CustomerDetailsRequest request) {
        CustomerDetails entity = CustomerDetails.builder()
                .customerNumber(request.getCustomerNumber())
                .customerType(request.getCustomerType())
                .customerFullName(request.getCustomerFullName())
                .customerDobDoi(request.getCustomerDobDoi() != null ? LocalDate.parse(request.getCustomerDobDoi()) : null)
                .customerStatus(request.getCustomerStatus())
                .customerContactNumber(request.getCustomerContactNumber())
                .customerMobileNumber(request.getCustomerMobileNumber())
                .customerEmailId(request.getCustomerEmailId())
                .customerCountryOfOrigination(request.getCustomerCountryOfOrigination())
                .effectiveDate(LocalDate.parse(request.getEffectiveDate()))
                .crudValue(request.getCrudValue())
                .auditFields(AuditFieldsMapper.toEntity(request.getAuditFields()))
                .build();
        return repository.save(entity);
    }

    public List<CustomerDetails> findAll() {
        return repository.findAll();
    }

    public CustomerDetails findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer details not found with ID: " + id));
    }

    public List<CustomerDetails> findAllActive() {
        return repository.findAllActive();
    }

    public List<CustomerDetails> findActiveByCustomerNumber(String customerNumber) {
        return repository.findActiveByCustomerNumber(customerNumber);
    }

    @Transactional
    public CustomerDetails logicalUpdate(CustomerDetailsRequest request) {
        request.setCrudValue("U");
        return insert(request);
    }

    @Transactional
    public CustomerDetails logicalDelete(CustomerDetailsRequest request) {
        request.setCrudValue("D");
        return insert(request);
    }
}
