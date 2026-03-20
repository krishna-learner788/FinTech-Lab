package com.MIT.FIS10.entity.customer;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "customer_contact_detail")
public class CustomerContactDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Customer Number is required")
    @Column(name = "customer_number", nullable = false)
    private String customerNumber;

    @NotBlank(message = "Contact Type is required")
    @Column(name = "contact_type", nullable = false)
    private String contactType;

    @NotBlank(message = "Contact Value is required")
    @Size(max = 200, message = "Contact Value must be at most 200 characters")
    @Column(name = "contact_value", nullable = false)
    private String contactValue;

    @Column(name = "is_primary")
    private Boolean isPrimary;

    @Column(name = "is_verified")
    private Boolean isVerified;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCustomerNumber() { return customerNumber; }
    public void setCustomerNumber(String customerNumber) { this.customerNumber = customerNumber; }

    public String getContactType() { return contactType; }
    public void setContactType(String contactType) { this.contactType = contactType; }

    public String getContactValue() { return contactValue; }
    public void setContactValue(String contactValue) { this.contactValue = contactValue; }

    public Boolean getIsPrimary() { return isPrimary; }
    public void setIsPrimary(Boolean isPrimary) { this.isPrimary = isPrimary; }

    public Boolean getIsVerified() { return isVerified; }
    public void setIsVerified(Boolean isVerified) { this.isVerified = isVerified; }
}
