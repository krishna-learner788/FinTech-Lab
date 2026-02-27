package com.customer.database.repository;

import com.customer.database.entity.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, Long> {

    List<CustomerAddress> findByCustomerIdentifier(String customerIdentifier);

    @Query("SELECT c FROM CustomerAddress c WHERE c.customerIdentifier = :custId AND c.crudValue <> 'D' ORDER BY c.effectiveDate DESC")
    List<CustomerAddress> findActiveByCustomerIdentifier(@Param("custId") String customerIdentifier);

    @Query("SELECT c FROM CustomerAddress c WHERE c.crudValue <> 'D' ORDER BY c.effectiveDate DESC")
    List<CustomerAddress> findAllActive();
}
