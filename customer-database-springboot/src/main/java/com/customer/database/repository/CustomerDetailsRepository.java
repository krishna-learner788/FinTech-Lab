package com.customer.database.repository;

import com.customer.database.entity.CustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerDetailsRepository extends JpaRepository<CustomerDetails, Long> {

    List<CustomerDetails> findByCustomerNumber(String customerNumber);

    List<CustomerDetails> findByCustomerType(String customerType);

    List<CustomerDetails> findByCustomerStatus(String customerStatus);

    @Query("SELECT c FROM CustomerDetails c WHERE c.customerNumber = :custNum AND c.crudValue <> 'D' ORDER BY c.effectiveDate DESC")
    List<CustomerDetails> findActiveByCustomerNumber(@Param("custNum") String customerNumber);

    @Query("SELECT c FROM CustomerDetails c WHERE c.crudValue <> 'D' ORDER BY c.effectiveDate DESC")
    List<CustomerDetails> findAllActive();
}
