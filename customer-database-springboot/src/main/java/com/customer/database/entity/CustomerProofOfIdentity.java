package com.customer.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Entity: Customer Proof of Identity
 * Short Name: CSTPI
 *
 * Stores ID proofs linked to a customer (Aadhaar, Passport, Driving License, PAN, etc.)
 * One or more ID Types can be defined per customer.
 * Each ID type and value should be unique.
 * At a given point in time there should not be duplicate values for a given ID type.
 * Each ID type should have a start and end date.
 * For types that are unique for the life of customer, end date = max date (never expire).
 * All ID types retain history (insert-only).
 *
 * Insert-Only Paradigm: CRUD_VALUE tracks logical state (C=Created, U=Updated, D=Deleted)
 */
@Entity
@Table(name = "CUSTOMER_PROOF_OF_IDENTITY")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerProofOfIdentity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CSTPI_ID")
    private Long id;

    @Column(name = "CSTPI_CUST_ID", nullable = false, length = 50)
    private String customerIdentifier;

    @Column(name = "CSTPI_PROOF_TYP_CLSF_ID", nullable = false)
    private Long proofTypeClassificationId;

    @Column(name = "CSTPI_CLSF_TYP_VALUE", nullable = false, length = 200)
    private String classificationTypeValue;

    @Column(name = "CSTPI_START_DT", nullable = false)
    private LocalDate startDate;

    @Column(name = "CSTPI_END_DT")
    private LocalDate endDate;

    @Column(name = "CSTPI_EFCTV_DT", nullable = false)
    private LocalDate effectiveDate;

    @Column(name = "CSTPI_CRUD_VALUE", nullable = false, length = 1)
    private String crudValue;

    // ==================== Relationship to Classification ====================

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CSTPI_PROOF_TYP_CLSF_ID", referencedColumnName = "CSTCL_ID",
                insertable = false, updatable = false)
    private CustomerClassification proofTypeClassification;

    // ==================== AUDIT COLUMNS (CSTPI_ prefix) ====================

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "userId",       column = @Column(name = "CSTPI_USER_ID",          nullable = false, length = 100)),
        @AttributeOverride(name = "wsId",          column = @Column(name = "CSTPI_WS_ID",            nullable = false, length = 100)),
        @AttributeOverride(name = "prgmId",        column = @Column(name = "CSTPI_PRGM_ID",          nullable = false, length = 100)),
        @AttributeOverride(name = "hostTs",        column = @Column(name = "CSTPI_HOST_TS",          nullable = false)),
        @AttributeOverride(name = "localTs",       column = @Column(name = "CSTPI_LOCAL_TS",         nullable = false)),
        @AttributeOverride(name = "acptTs",        column = @Column(name = "CSTPI_ACPT_TS",          nullable = false)),
        @AttributeOverride(name = "acptTsUtcOfst", column = @Column(name = "CSTPI_ACPT_TS_UTC_OFST", nullable = false, length = 6)),
        @AttributeOverride(name = "ldbid",         column = @Column(name = "CSTPI_LDBID",            nullable = false)),
        @AttributeOverride(name = "uuid",          column = @Column(name = "CSTPI_UUID",             length = 100))
    })
    private AuditFields auditFields;
}
