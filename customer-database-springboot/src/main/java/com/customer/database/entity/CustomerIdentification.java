package com.customer.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Entity: Customer Identification
 * Short Name: CSTID
 *
 * Stores internal identifiers and external identifiers for a customer.
 * Unique identifier for a given customer - check for duplicates in user-entered values.
 * Supports alternate options like phone number for customer identification.
 *
 * Insert-Only Paradigm: CRUD_VALUE tracks logical state (C=Created, U=Updated, D=Deleted)
 */
@Entity
@Table(name = "CUSTOMER_IDENTIFICATION")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerIdentification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CSTID_ID")
    private Long identificationId;

    @Column(name = "CSTID_TYP", nullable = false, length = 100)
    private String identificationType;

    @Column(name = "CSTID_ITEM", nullable = false, length = 200)
    private String identificationItem;

    @Column(name = "CSTID_EFCTV_DT", nullable = false)
    private LocalDate effectiveDate;

    @Column(name = "CSTID_CRUD_VALUE", nullable = false, length = 1)
    private String crudValue;

    // ==================== AUDIT COLUMNS (CSTID_ prefix) ====================

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "userId",       column = @Column(name = "CSTID_USER_ID",          nullable = false, length = 100)),
        @AttributeOverride(name = "wsId",          column = @Column(name = "CSTID_WS_ID",            nullable = false, length = 100)),
        @AttributeOverride(name = "prgmId",        column = @Column(name = "CSTID_PRGM_ID",          nullable = false, length = 100)),
        @AttributeOverride(name = "hostTs",        column = @Column(name = "CSTID_HOST_TS",          nullable = false)),
        @AttributeOverride(name = "localTs",       column = @Column(name = "CSTID_LOCAL_TS",         nullable = false)),
        @AttributeOverride(name = "acptTs",        column = @Column(name = "CSTID_ACPT_TS",          nullable = false)),
        @AttributeOverride(name = "acptTsUtcOfst", column = @Column(name = "CSTID_ACPT_TS_UTC_OFST", nullable = false, length = 6)),
        @AttributeOverride(name = "ldbid",         column = @Column(name = "CSTID_LDBID",            nullable = false)),
        @AttributeOverride(name = "uuid",          column = @Column(name = "CSTID_UUID",             length = 100))
    })
    private AuditFields auditFields;
}
