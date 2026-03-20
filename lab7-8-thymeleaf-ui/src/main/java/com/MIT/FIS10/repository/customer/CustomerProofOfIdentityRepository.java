package com.MIT.FIS10.repository.customer;

import com.MIT.FIS10.entity.customer.CustomerProofOfIdentity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerProofOfIdentityRepository extends JpaRepository<CustomerProofOfIdentity, Long> {
    List<CustomerProofOfIdentity> findByCustomerNumber(String customerNumber);
}
