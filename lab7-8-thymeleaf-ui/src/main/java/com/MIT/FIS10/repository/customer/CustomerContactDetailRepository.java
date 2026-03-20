package com.MIT.FIS10.repository.customer;

import com.MIT.FIS10.entity.customer.CustomerContactDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerContactDetailRepository extends JpaRepository<CustomerContactDetail, Long> {
    List<CustomerContactDetail> findByCustomerNumber(String customerNumber);
}
