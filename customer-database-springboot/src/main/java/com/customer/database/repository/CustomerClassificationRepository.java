package com.customer.database.repository;

import com.customer.database.entity.CustomerClassification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerClassificationRepository extends JpaRepository<CustomerClassification, Long> {

    List<CustomerClassification> findByClassificationType(String classificationType);

    List<CustomerClassification> findByClassificationTypeAndClassificationTypeValue(
            String classificationType, String classificationTypeValue);

    @Query("SELECT c FROM CustomerClassification c WHERE c.classificationType = :type AND c.crudValue <> 'D' ORDER BY c.effectiveDate DESC")
    List<CustomerClassification> findActiveByType(@Param("type") String classificationType);

    @Query("SELECT c FROM CustomerClassification c WHERE c.crudValue <> 'D' ORDER BY c.classificationType, c.effectiveDate DESC")
    List<CustomerClassification> findAllActive();
}
