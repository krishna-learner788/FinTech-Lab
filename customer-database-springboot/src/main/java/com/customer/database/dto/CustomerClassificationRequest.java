package com.customer.database.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerClassificationRequest {

    @NotBlank(message = "Classification type is required")
    private String classificationType;

    @NotBlank(message = "Classification type value is required")
    private String classificationTypeValue;

    @NotNull(message = "Effective date is required (format: yyyy-MM-dd)")
    private String effectiveDate;

    @NotBlank(message = "CRUD value is required (C, U, or D)")
    @Pattern(regexp = "[CUD]", message = "CRUD value must be C, U, or D")
    private String crudValue;

    @NotNull(message = "Audit fields are required")
    private AuditFieldsDto auditFields;
}
