package com.customer.database.service;

import com.customer.database.dto.AuditFieldsDto;
import com.customer.database.entity.AuditFields;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Utility to map AuditFieldsDto to AuditFields entity.
 * Auto-populates hostTs (server time) and uuid if not provided.
 */
public final class AuditFieldsMapper {

    private AuditFieldsMapper() {
    }

    public static AuditFields toEntity(AuditFieldsDto dto) {
        LocalDateTime now = LocalDateTime.now();

        return AuditFields.builder()
                .userId(dto.getUserId())
                .wsId(dto.getWsId())
                .prgmId(dto.getPrgmId())
                .hostTs(now)  // always set by server
                .localTs(dto.getLocalTs() != null ? LocalDateTime.parse(dto.getLocalTs()) : now)
                .acptTs(dto.getAcptTs() != null ? LocalDateTime.parse(dto.getAcptTs()) : now)
                .acptTsUtcOfst(dto.getAcptTsUtcOfst() != null ? dto.getAcptTsUtcOfst() : "+00:00")
                .ldbid(dto.getLdbid() != null ? dto.getLdbid() : 1)
                .uuid(dto.getUuid() != null ? dto.getUuid() : UUID.randomUUID().toString())
                .build();
    }
}
