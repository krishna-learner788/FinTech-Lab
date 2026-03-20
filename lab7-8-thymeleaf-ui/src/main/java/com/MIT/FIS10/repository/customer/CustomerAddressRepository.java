package com.MIT.FIS10.repository.customer;

import com.MIT.FIS10.entity.customer.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, Long> {
    List<CustomerAddress> findByCustomerNumber(String customerNumber);
}
