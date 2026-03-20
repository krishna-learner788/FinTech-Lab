package com.MIT.FIS10.service.customer;

import com.MIT.FIS10.entity.customer.CustomerContactDetail;
import com.MIT.FIS10.exception.ResourceNotFoundException;
import com.MIT.FIS10.repository.customer.CustomerContactDetailRepository;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerContactDetailService {

    @Autowired
    private CustomerContactDetailRepository repository;

    public List<CustomerContactDetail> getAll() {
        return repository.findAll();
    }

    public Page<CustomerContactDetail> getPaginated(int page, int pageSize) {
        return repository.findAll(PageRequest.of(page, pageSize));
    }

    public CustomerContactDetail findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact Detail not found with ID: " + id));
    }

    public List<CustomerContactDetail> findByCustomerNumber(String customerNumber) {
        return repository.findByCustomerNumber(customerNumber);
    }

    public void save(@Valid CustomerContactDetail contact) {
        repository.save(contact);
    }

    public void update(Long id, CustomerContactDetail contact) {
        CustomerContactDetail existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact Detail not found"));
        existing.setCustomerNumber(contact.getCustomerNumber());
        existing.setContactType(contact.getContactType());
        existing.setContactValue(contact.getContactValue());
        existing.setIsPrimary(contact.getIsPrimary());
        existing.setIsVerified(contact.getIsVerified());
        repository.save(existing);
    }

    public void deleteById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Contact Detail not found with ID: " + id);
        }
    }
}
