package com.customer.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Entity: Customer Name
 * Short Name: CSTNM
 *
 * Stores the customer's name components (First Name, Middle Name, Last Name, etc.)
 * linked via Classification ID for the name component type.
 *
 * Insert-Only Paradigm: CRUD_VALUE tracks logical state (C=Created, U=Updated, D=Deleted)
 */
@Entity
@Table(name = "CUSTOMER_NAME")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CSTNM_ID")
    private Long id;

    @Column(name = "CSTNM_CUST_ID", nullable = false, length = 50)
    private String customerIdentifier;

    @Column(name = "CSTNM_NAME_COMP_TYP_CLSF_ID", nullable = false)
    private Long nameComponentTypeClassificationId;

    @Column(name = "CSTNM_NAME_VALUE", nullable = false, length = 200)
    private String customerNameValue;

    @Column(name = "CSTNM_EFCTV_DT", nullable = false)
    private LocalDate effectiveDate;

    @Column(name = "CSTNM_CRUD_VALUE", nullable = false, length = 1)
    private String crudValue;

    // ==================== Relationship to Classification ====================

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CSTNM_NAME_COMP_TYP_CLSF_ID", referencedColumnName = "CSTCL_ID",
                insertable = false, updatable = false)
    private CustomerClassification nameComponentTypeClassification;

    // ==================== AUDIT COLUMNS (CSTNM_ prefix) ====================

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "userId",       column = @Column(name = "CSTNM_USER_ID",          nullable = false, length = 100)),
        @AttributeOverride(name = "wsId",          column = @Column(name = "CSTNM_WS_ID",            nullable = false, length = 100)),
        @AttributeOverride(name = "prgmId",        column = @Column(name = "CSTNM_PRGM_ID",          nullable = false, length = 100)),
        @AttributeOverride(name = "hostTs",        column = @Column(name = "CSTNM_HOST_TS",          nullable = false)),
        @AttributeOverride(name = "localTs",       column = @Column(name = "CSTNM_LOCAL_TS",         nullable = false)),
        @AttributeOverride(name = "acptTs",        column = @Column(name = "CSTNM_ACPT_TS",          nullable = false)),
        @AttributeOverride(name = "acptTsUtcOfst", column = @Column(name = "CSTNM_ACPT_TS_UTC_OFST", nullable = false, length = 6)),
        @AttributeOverride(name = "ldbid",         column = @Column(name = "CSTNM_LDBID",            nullable = false)),
        @AttributeOverride(name = "uuid",          column = @Column(name = "CSTNM_UUID",             length = 100))
    })
    private AuditFields auditFields;
}
