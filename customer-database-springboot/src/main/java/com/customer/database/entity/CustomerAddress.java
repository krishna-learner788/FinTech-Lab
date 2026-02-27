package com.customer.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Entity: Customer Address
 * Short Name: CSTAD
 *
 * Stores the customer's address components (First Line, Middle Line, Last Line, etc.)
 * linked via Classification ID for the address component type.
 *
 * Insert-Only Paradigm: CRUD_VALUE tracks logical state (C=Created, U=Updated, D=Deleted)
 */
@Entity
@Table(name = "CUSTOMER_ADDRESS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CSTAD_ID")
    private Long id;

    @Column(name = "CSTAD_CUST_ID", nullable = false, length = 50)
    private String customerIdentifier;

    @Column(name = "CSTAD_ADDR_COMP_TYP_CLSF_ID", nullable = false)
    private Long addressComponentTypeClassificationId;

    @Column(name = "CSTAD_ADDR_VALUE", nullable = false, length = 500)
    private String customerAddressValue;

    @Column(name = "CSTAD_EFCTV_DT", nullable = false)
    private LocalDate effectiveDate;

    @Column(name = "CSTAD_CRUD_VALUE", nullable = false, length = 1)
    private String crudValue;

    // ==================== Relationship to Classification ====================

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CSTAD_ADDR_COMP_TYP_CLSF_ID", referencedColumnName = "CSTCL_ID",
                insertable = false, updatable = false)
    private CustomerClassification addressComponentTypeClassification;

    // ==================== AUDIT COLUMNS (CSTAD_ prefix) ====================

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "userId",       column = @Column(name = "CSTAD_USER_ID",          nullable = false, length = 100)),
        @AttributeOverride(name = "wsId",          column = @Column(name = "CSTAD_WS_ID",            nullable = false, length = 100)),
        @AttributeOverride(name = "prgmId",        column = @Column(name = "CSTAD_PRGM_ID",          nullable = false, length = 100)),
        @AttributeOverride(name = "hostTs",        column = @Column(name = "CSTAD_HOST_TS",          nullable = false)),
        @AttributeOverride(name = "localTs",       column = @Column(name = "CSTAD_LOCAL_TS",         nullable = false)),
        @AttributeOverride(name = "acptTs",        column = @Column(name = "CSTAD_ACPT_TS",          nullable = false)),
        @AttributeOverride(name = "acptTsUtcOfst", column = @Column(name = "CSTAD_ACPT_TS_UTC_OFST", nullable = false, length = 6)),
        @AttributeOverride(name = "ldbid",         column = @Column(name = "CSTAD_LDBID",            nullable = false)),
        @AttributeOverride(name = "uuid",          column = @Column(name = "CSTAD_UUID",             length = 100))
    })
    private AuditFields auditFields;
}
