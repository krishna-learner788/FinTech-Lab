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
public class CustomerDetailsRequest {

    @NotBlank(message = "Customer number is required")
    private String customerNumber;

    @NotBlank(message = "Customer type is required")
    private String customerType;

    @NotBlank(message = "Customer full name is required")
    private String customerFullName;

    private String customerDobDoi; // format: yyyy-MM-dd

    @NotBlank(message = "Customer status is required")
    private String customerStatus;

    private String customerContactNumber;
    private String customerMobileNumber;
    private String customerEmailId;
    private String customerCountryOfOrigination;

    @NotNull(message = "Effective date is required (format: yyyy-MM-dd)")
    private String effectiveDate;

    @NotBlank(message = "CRUD value is required (C, U, or D)")
    @Pattern(regexp = "[CUD]", message = "CRUD value must be C, U, or D")
    private String crudValue;

    @NotNull(message = "Audit fields are required")
    private AuditFieldsDto auditFields;
}
