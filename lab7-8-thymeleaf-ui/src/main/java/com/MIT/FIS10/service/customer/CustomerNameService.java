package com.MIT.FIS10.service.customer;

import com.MIT.FIS10.entity.customer.CustomerName;
import com.MIT.FIS10.exception.ResourceNotFoundException;
import com.MIT.FIS10.repository.customer.CustomerNameRepository;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerNameService {

    @Autowired
    private CustomerNameRepository repository;

    public List<CustomerName> getAll() {
        return repository.findAll();
    }

    public Page<CustomerName> getPaginated(int page, int pageSize) {
        return repository.findAll(PageRequest.of(page, pageSize));
    }

    public CustomerName findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer Name not found with ID: " + id));
    }

    public List<CustomerName> findByCustomerNumber(String customerNumber) {
        return repository.findByCustomerNumber(customerNumber);
    }

    public void save(@Valid CustomerName customerName) {
        repository.save(customerName);
    }

    public void update(Long id, CustomerName customerName) {
        CustomerName existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer Name not found"));
        existing.setCustomerNumber(customerName.getCustomerNumber());
        existing.setNameType(customerName.getNameType());
        existing.setFirstName(customerName.getFirstName());
        existing.setMiddleName(customerName.getMiddleName());
        existing.setLastName(customerName.getLastName());
        existing.setPrefix(customerName.getPrefix());
        existing.setSuffix(customerName.getSuffix());
        repository.save(existing);
    }

    public void deleteById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Customer Name not found with ID: " + id);
        }
    }
}
