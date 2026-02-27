package com.customer.database.service;

import com.customer.database.dto.CustomerAddressRequest;
import com.customer.database.entity.CustomerAddress;
import com.customer.database.repository.CustomerAddressRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class CustomerAddressService {

    private final CustomerAddressRepository repository;

    public CustomerAddressService(CustomerAddressRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public CustomerAddress insert(CustomerAddressRequest request) {
        CustomerAddress entity = CustomerAddress.builder()
                .customerIdentifier(request.getCustomerIdentifier())
                .addressComponentTypeClassificationId(request.getAddressComponentTypeClassificationId())
                .customerAddressValue(request.getCustomerAddressValue())
                .effectiveDate(LocalDate.parse(request.getEffectiveDate()))
                .crudValue(request.getCrudValue())
                .auditFields(AuditFieldsMapper.toEntity(request.getAuditFields()))
                .build();
        return repository.save(entity);
    }

    public List<CustomerAddress> findAll() {
        return repository.findAll();
    }

    public CustomerAddress findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer address not found with ID: " + id));
    }

    public List<CustomerAddress> findAllActive() {
        return repository.findAllActive();
    }

    public List<CustomerAddress> findActiveByCustomerIdentifier(String customerIdentifier) {
        return repository.findActiveByCustomerIdentifier(customerIdentifier);
    }

    @Transactional
    public CustomerAddress logicalUpdate(CustomerAddressRequest request) {
        request.setCrudValue("U");
        return insert(request);
    }

    @Transactional
    public CustomerAddress logicalDelete(CustomerAddressRequest request) {
        request.setCrudValue("D");
        return insert(request);
    }
}
