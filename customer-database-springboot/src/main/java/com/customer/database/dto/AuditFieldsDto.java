package com.customer.database.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for audit fields — sent in every request.
 * The server will auto-populate hostTs if not provided.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditFieldsDto {

    private String userId;
    private String wsId;
    private String prgmId;
    private String localTs;      // ISO format: yyyy-MM-ddTHH:mm:ss
    private String acptTs;       // ISO format: yyyy-MM-ddTHH:mm:ss
    private String acptTsUtcOfst; // e.g. "+05:30"
    private Integer ldbid;
    private String uuid;
}
