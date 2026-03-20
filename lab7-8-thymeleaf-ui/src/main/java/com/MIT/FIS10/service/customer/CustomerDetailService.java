package com.MIT.FIS10.service.customer;

import com.MIT.FIS10.entity.customer.CustomerDetail;
import com.MIT.FIS10.exception.ResourceNotFoundException;
import com.MIT.FIS10.repository.customer.CustomerDetailRepository;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerDetailService {

    @Autowired
    private CustomerDetailRepository customerDetailRepository;

    public List<CustomerDetail> getAllCustomers() {
        return customerDetailRepository.findAll();
    }

    public Page<CustomerDetail> getPaginatedCustomers(int page, int pageSize) {
        return customerDetailRepository.findAll(PageRequest.of(page, pageSize));
    }

    public CustomerDetail findById(Long id) {
        return customerDetailRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + id));
    }

    public void saveCustomer(@Valid CustomerDetail customer) {
        customerDetailRepository.save(customer);
    }

    public void updateCustomer(Long id, CustomerDetail customer) {
        CustomerDetail existing = customerDetailRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        existing.setCustomerNumber(customer.getCustomerNumber());
        existing.setCustomerType(customer.getCustomerType());
        existing.setFullName(customer.getFullName());
        existing.setDateOfBirth(customer.getDateOfBirth());
        existing.setStatus(customer.getStatus());
        existing.setContactNumber(customer.getContactNumber());
        existing.setMobileNumber(customer.getMobileNumber());
        existing.setEmailId(customer.getEmailId());
        existing.setCountryOfOrigination(customer.getCountryOfOrigination());
        customerDetailRepository.save(existing);
    }

    public void deleteCustomerById(Long id) {
        if (customerDetailRepository.existsById(id)) {
            customerDetailRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Customer not found with ID: " + id);
        }
    }
}
