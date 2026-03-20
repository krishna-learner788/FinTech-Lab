package com.MIT.FIS10.service.customer;

import com.MIT.FIS10.entity.customer.CustomerAddress;
import com.MIT.FIS10.exception.ResourceNotFoundException;
import com.MIT.FIS10.repository.customer.CustomerAddressRepository;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerAddressService {

    @Autowired
    private CustomerAddressRepository repository;

    public List<CustomerAddress> getAll() {
        return repository.findAll();
    }

    public Page<CustomerAddress> getPaginated(int page, int pageSize) {
        return repository.findAll(PageRequest.of(page, pageSize));
    }

    public CustomerAddress findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found with ID: " + id));
    }

    public List<CustomerAddress> findByCustomerNumber(String customerNumber) {
        return repository.findByCustomerNumber(customerNumber);
    }

    public void save(@Valid CustomerAddress address) {
        repository.save(address);
    }

    public void update(Long id, CustomerAddress address) {
        CustomerAddress existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found"));
        existing.setCustomerNumber(address.getCustomerNumber());
        existing.setAddressType(address.getAddressType());
        existing.setAddressLine1(address.getAddressLine1());
        existing.setAddressLine2(address.getAddressLine2());
        existing.setCity(address.getCity());
        existing.setState(address.getState());
        existing.setPostalCode(address.getPostalCode());
        existing.setCountry(address.getCountry());
        repository.save(existing);
    }

    public void deleteById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Address not found with ID: " + id);
        }
    }
}
