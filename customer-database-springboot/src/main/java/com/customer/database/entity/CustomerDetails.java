package com.customer.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Entity: Customer Details
 * Short Name: CSTDT
 *
 * Main customer information table with composite keys and foreign keys.
 * Data can be stored in different columns as the data of this type is unique and not repetitive.
 * Uses various data types.
 *
 * Insert-Only Paradigm: CRUD_VALUE tracks logical state (C=Created, U=Updated, D=Deleted)
 */
@Entity
@Table(name = "CUSTOMER_DETAILS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CSTDT_ID")
    private Long id;

    @Column(name = "CSTDT_CUST_NUM", nullable = false, length = 50)
    private String customerNumber;

    @Column(name = "CSTDT_CUST_TYP", nullable = false, length = 100)
    private String customerType;

    @Column(name = "CSTDT_FULL_NAME", nullable = false, length = 200)
    private String customerFullName;

    @Column(name = "CSTDT_DOB_DOI")
    private LocalDate customerDobDoi;

    @Column(name = "CSTDT_STATUS", nullable = false, length = 50)
    private String customerStatus;

    @Column(name = "CSTDT_CONTACT_NUM", length = 20)
    private String customerContactNumber;

    @Column(name = "CSTDT_MOBILE_NUM", length = 20)
    private String customerMobileNumber;

    @Column(name = "CSTDT_EMAIL_ID", length = 150)
    private String customerEmailId;

    @Column(name = "CSTDT_COUNTRY_ORIG", length = 100)
    private String customerCountryOfOrigination;

    @Column(name = "CSTDT_EFCTV_DT", nullable = false)
    private LocalDate effectiveDate;

    @Column(name = "CSTDT_CRUD_VALUE", nullable = false, length = 1)
    private String crudValue;

    // ==================== AUDIT COLUMNS (CSTDT_ prefix) ====================

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "userId",       column = @Column(name = "CSTDT_USER_ID",          nullable = false, length = 100)),
        @AttributeOverride(name = "wsId",          column = @Column(name = "CSTDT_WS_ID",            nullable = false, length = 100)),
        @AttributeOverride(name = "prgmId",        column = @Column(name = "CSTDT_PRGM_ID",          nullable = false, length = 100)),
        @AttributeOverride(name = "hostTs",        column = @Column(name = "CSTDT_HOST_TS",          nullable = false)),
        @AttributeOverride(name = "localTs",       column = @Column(name = "CSTDT_LOCAL_TS",         nullable = false)),
        @AttributeOverride(name = "acptTs",        column = @Column(name = "CSTDT_ACPT_TS",          nullable = false)),
        @AttributeOverride(name = "acptTsUtcOfst", column = @Column(name = "CSTDT_ACPT_TS_UTC_OFST", nullable = false, length = 6)),
        @AttributeOverride(name = "ldbid",         column = @Column(name = "CSTDT_LDBID",            nullable = false)),
        @AttributeOverride(name = "uuid",          column = @Column(name = "CSTDT_UUID",             length = 100))
    })
    private AuditFields auditFields;
}
