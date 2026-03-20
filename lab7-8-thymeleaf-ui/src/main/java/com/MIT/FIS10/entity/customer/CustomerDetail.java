package com.MIT.FIS10.entity.customer;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "customer_detail")
public class CustomerDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Customer Number is required")
    @Size(max = 50, message = "Customer Number must be at most 50 characters")
    @Column(name = "customer_number", nullable = false, unique = true)
    private String customerNumber;

    @NotBlank(message = "Customer Type is required")
    @Column(name = "customer_type", nullable = false)
    private String customerType;

    @NotBlank(message = "Full Name is required")
    @Size(min = 2, max = 200, message = "Full Name must be between 2 and 200 characters")
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Status is required")
    @Column(name = "status", nullable = false)
    private String status;

    @Size(max = 20, message = "Contact Number must be at most 20 characters")
    @Column(name = "contact_number")
    private String contactNumber;

    @Size(max = 20, message = "Mobile Number must be at most 20 characters")
    @Column(name = "mobile_number")
    private String mobileNumber;

    @Email(message = "Invalid email address")
    @Column(name = "email_id")
    private String emailId;

    @Column(name = "country_of_origination")
    private String countryOfOrigination;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCustomerNumber() { return customerNumber; }
    public void setCustomerNumber(String customerNumber) { this.customerNumber = customerNumber; }

    public String getCustomerType() { return customerType; }
    public void setCustomerType(String customerType) { this.customerType = customerType; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }

    public String getMobileNumber() { return mobileNumber; }
    public void setMobileNumber(String mobileNumber) { this.mobileNumber = mobileNumber; }

    public String getEmailId() { return emailId; }
    public void setEmailId(String emailId) { this.emailId = emailId; }

    public String getCountryOfOrigination() { return countryOfOrigination; }
    public void setCountryOfOrigination(String countryOfOrigination) { this.countryOfOrigination = countryOfOrigination; }
}
