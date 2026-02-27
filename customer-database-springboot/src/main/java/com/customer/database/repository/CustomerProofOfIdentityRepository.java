package com.customer.database.repository;

import com.customer.database.entity.CustomerProofOfIdentity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerProofOfIdentityRepository extends JpaRepository<CustomerProofOfIdentity, Long> {

    List<CustomerProofOfIdentity> findByCustomerIdentifier(String customerIdentifier);

    @Query("SELECT c FROM CustomerProofOfIdentity c WHERE c.customerIdentifier = :custId AND c.crudValue <> 'D' ORDER BY c.effectiveDate DESC")
    List<CustomerProofOfIdentity> findActiveByCustomerIdentifier(@Param("custId") String customerIdentifier);

    @Query("SELECT c FROM CustomerProofOfIdentity c WHERE c.crudValue <> 'D' ORDER BY c.effectiveDate DESC")
    List<CustomerProofOfIdentity> findAllActive();
}
