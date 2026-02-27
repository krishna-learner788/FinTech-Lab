package com.customer.database.repository;

import com.customer.database.entity.CustomerIdentification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerIdentificationRepository extends JpaRepository<CustomerIdentification, Long> {

    List<CustomerIdentification> findByIdentificationType(String identificationType);

    List<CustomerIdentification> findByIdentificationItem(String identificationItem);

    @Query("SELECT c FROM CustomerIdentification c WHERE c.crudValue <> 'D' ORDER BY c.effectiveDate DESC")
    List<CustomerIdentification> findAllActive();

    @Query("SELECT c FROM CustomerIdentification c WHERE c.identificationType = :type AND c.identificationItem = :item AND c.crudValue <> 'D'")
    List<CustomerIdentification> findActiveByTypeAndItem(@Param("type") String type, @Param("item") String item);
}
