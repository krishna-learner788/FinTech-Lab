package com.MIT.FIS10.repository.customer;

import com.MIT.FIS10.entity.customer.CustomerName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerNameRepository extends JpaRepository<CustomerName, Long> {
    List<CustomerName> findByCustomerNumber(String customerNumber);
}
