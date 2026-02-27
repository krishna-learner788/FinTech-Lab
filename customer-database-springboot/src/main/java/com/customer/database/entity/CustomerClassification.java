package com.customer.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Entity: Customer Classification Table
 * Short Name: CSTCL
 *
 * Lookup/reference table used to define different classification types:
 * - Customer Types (Individual, Corporate)
 * - Name Types (First Name, Middle Name, Last Name)
 * - ID Types (Aadhaar, Passport No, Driving License)
 * - Address Types (First Line, Middle Line, Last Line)
 *
 * Insert-Only Paradigm: CRUD_VALUE tracks logical state (C=Created, U=Updated, D=Deleted)
 */
@Entity
@Table(name = "CUSTOMER_CLASSIFICATION")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerClassification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CSTCL_ID")
    private Long classificationId;

    @Column(name = "CSTCL_TYP", nullable = false, length = 100)
    private String classificationType;

    @Column(name = "CSTCL_TYP_VALUE", nullable = false, length = 100)
    private String classificationTypeValue;

    @Column(name = "CSTCL_EFCTV_DT", nullable = false)
    private LocalDate effectiveDate;

    @Column(name = "CSTCL_CRUD_VALUE", nullable = false, length = 1)
    private String crudValue;

    // ==================== AUDIT COLUMNS (CSTCL_ prefix) ====================

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "userId",       column = @Column(name = "CSTCL_USER_ID",          nullable = false, length = 100)),
        @AttributeOverride(name = "wsId",          column = @Column(name = "CSTCL_WS_ID",            nullable = false, length = 100)),
        @AttributeOverride(name = "prgmId",        column = @Column(name = "CSTCL_PRGM_ID",          nullable = false, length = 100)),
        @AttributeOverride(name = "hostTs",        column = @Column(name = "CSTCL_HOST_TS",          nullable = false)),
        @AttributeOverride(name = "localTs",       column = @Column(name = "CSTCL_LOCAL_TS",         nullable = false)),
        @AttributeOverride(name = "acptTs",        column = @Column(name = "CSTCL_ACPT_TS",          nullable = false)),
        @AttributeOverride(name = "acptTsUtcOfst", column = @Column(name = "CSTCL_ACPT_TS_UTC_OFST", nullable = false, length = 6)),
        @AttributeOverride(name = "ldbid",         column = @Column(name = "CSTCL_LDBID",            nullable = false)),
        @AttributeOverride(name = "uuid",          column = @Column(name = "CSTCL_UUID",             length = 100))
    })
    private AuditFields auditFields;
}
