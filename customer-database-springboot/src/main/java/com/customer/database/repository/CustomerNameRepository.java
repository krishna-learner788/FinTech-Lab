package com.customer.database.repository;

import com.customer.database.entity.CustomerName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerNameRepository extends JpaRepository<CustomerName, Long> {

    List<CustomerName> findByCustomerIdentifier(String customerIdentifier);

    @Query("SELECT c FROM CustomerName c WHERE c.customerIdentifier = :custId AND c.crudValue <> 'D' ORDER BY c.effectiveDate DESC")
    List<CustomerName> findActiveByCustomerIdentifier(@Param("custId") String customerIdentifier);

    @Query("SELECT c FROM CustomerName c WHERE c.crudValue <> 'D' ORDER BY c.effectiveDate DESC")
    List<CustomerName> findAllActive();
}
