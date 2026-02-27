package com.customer.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Embeddable audit columns present in ALL entities.
 * Column names use "OOO" as placeholder — each entity overrides
 * these with its own prefix via @AttributeOverride.
 *
 * Audit Columns:
 * - USER_ID        : User who created the data
 * - WS_ID          : Workstation from where data is created
 * - PRGM_ID        : Program/module that initiated the process (RULE_SYSTEM_ID)
 * - HOST_TS        : UTC timestamp when the row got inserted in the DB
 * - LOCAL_TS       : Operation timestamp when the transaction is initiated (UTC)
 * - ACPT_TS        : Acceptance timestamp (local/client system timestamp)
 * - ACPT_TS_UTC_OFST : UTC offset for ACPT_TS
 * - LDBID          : Logical database identifier for the financial institution
 * - UUID           : Universal Unique Identifier for the service request
 */
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditFields {

    @Column(name = "OOO_USER_ID", nullable = false, length = 100)
    private String userId;

    @Column(name = "OOO_WS_ID", nullable = false, length = 100)
    private String wsId;

    @Column(name = "OOO_PRGM_ID", nullable = false, length = 100)
    private String prgmId;

    @Column(name = "OOO_HOST_TS", nullable = false)
    private LocalDateTime hostTs;

    @Column(name = "OOO_LOCAL_TS", nullable = false)
    private LocalDateTime localTs;

    @Column(name = "OOO_ACPT_TS", nullable = false)
    private LocalDateTime acptTs;

    @Column(name = "OOO_ACPT_TS_UTC_OFST", nullable = false, length = 6)
    private String acptTsUtcOfst;

    @Column(name = "OOO_LDBID", nullable = false)
    private Integer ldbid;

    @Column(name = "OOO_UUID", length = 100)
    private String uuid;
}
