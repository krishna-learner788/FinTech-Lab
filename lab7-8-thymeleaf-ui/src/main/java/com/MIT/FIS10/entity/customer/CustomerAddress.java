package com.MIT.FIS10.entity.customer;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "customer_address")
public class CustomerAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Customer Number is required")
    @Column(name = "customer_number", nullable = false)
    private String customerNumber;

    @NotBlank(message = "Address Type is required")
    @Column(name = "address_type", nullable = false)
    private String addressType;

    @Size(max = 200, message = "Address Line 1 must be at most 200 characters")
    @Column(name = "address_line1")
    private String addressLine1;

    @Size(max = 200, message = "Address Line 2 must be at most 200 characters")
    @Column(name = "address_line2")
    private String addressLine2;

    @Size(max = 100, message = "City must be at most 100 characters")
    @Column(name = "city")
    private String city;

    @Size(max = 100, message = "State must be at most 100 characters")
    @Column(name = "state")
    private String state;

    @Size(max = 20, message = "Postal Code must be at most 20 characters")
    @Column(name = "postal_code")
    private String postalCode;

    @Size(max = 100, message = "Country must be at most 100 characters")
    @Column(name = "country")
    private String country;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCustomerNumber() { return customerNumber; }
    public void setCustomerNumber(String customerNumber) { this.customerNumber = customerNumber; }

    public String getAddressType() { return addressType; }
    public void setAddressType(String addressType) { this.addressType = addressType; }

    public String getAddressLine1() { return addressLine1; }
    public void setAddressLine1(String addressLine1) { this.addressLine1 = addressLine1; }

    public String getAddressLine2() { return addressLine2; }
    public void setAddressLine2(String addressLine2) { this.addressLine2 = addressLine2; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getPostalCode() { return postalCode; }
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
}
