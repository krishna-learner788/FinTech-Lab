package com.MIT.FIS10.service.customer;

import com.MIT.FIS10.entity.customer.CustomerProofOfIdentity;
import com.MIT.FIS10.exception.ResourceNotFoundException;
import com.MIT.FIS10.repository.customer.CustomerProofOfIdentityRepository;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerProofOfIdentityService {

    @Autowired
    private CustomerProofOfIdentityRepository repository;

    public List<CustomerProofOfIdentity> getAll() {
        return repository.findAll();
    }

    public Page<CustomerProofOfIdentity> getPaginated(int page, int pageSize) {
        return repository.findAll(PageRequest.of(page, pageSize));
    }

    public CustomerProofOfIdentity findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proof of Identity not found with ID: " + id));
    }

    public List<CustomerProofOfIdentity> findByCustomerNumber(String customerNumber) {
        return repository.findByCustomerNumber(customerNumber);
    }

    public void save(@Valid CustomerProofOfIdentity proof) {
        repository.save(proof);
    }

    public void update(Long id, CustomerProofOfIdentity proof) {
        CustomerProofOfIdentity existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proof of Identity not found"));
        existing.setCustomerNumber(proof.getCustomerNumber());
        existing.setIdType(proof.getIdType());
        existing.setIdNumber(proof.getIdNumber());
        existing.setIssueDate(proof.getIssueDate());
        existing.setExpiryDate(proof.getExpiryDate());
        existing.setIssuingAuthority(proof.getIssuingAuthority());
        existing.setIssuingCountry(proof.getIssuingCountry());
        repository.save(existing);
    }

    public void deleteById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Proof of Identity not found with ID: " + id);
        }
    }
}
