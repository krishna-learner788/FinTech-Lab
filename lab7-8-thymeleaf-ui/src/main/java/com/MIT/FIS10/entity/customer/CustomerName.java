package com.MIT.FIS10.entity.customer;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "customer_name")
public class CustomerName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Customer Number is required")
    @Column(name = "customer_number", nullable = false)
    private String customerNumber;

    @NotBlank(message = "Name Type is required")
    @Column(name = "name_type", nullable = false)
    private String nameType;

    @Size(max = 100, message = "First Name must be at most 100 characters")
    @Column(name = "first_name")
    private String firstName;

    @Size(max = 100, message = "Middle Name must be at most 100 characters")
    @Column(name = "middle_name")
    private String middleName;

    @Size(max = 100, message = "Last Name must be at most 100 characters")
    @Column(name = "last_name")
    private String lastName;

    @Size(max = 20, message = "Prefix must be at most 20 characters")
    @Column(name = "prefix")
    private String prefix;

    @Size(max = 20, message = "Suffix must be at most 20 characters")
    @Column(name = "suffix")
    private String suffix;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCustomerNumber() { return customerNumber; }
    public void setCustomerNumber(String customerNumber) { this.customerNumber = customerNumber; }

    public String getNameType() { return nameType; }
    public void setNameType(String nameType) { this.nameType = nameType; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getMiddleName() { return middleName; }
    public void setMiddleName(String middleName) { this.middleName = middleName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getPrefix() { return prefix; }
    public void setPrefix(String prefix) { this.prefix = prefix; }

    public String getSuffix() { return suffix; }
    public void setSuffix(String suffix) { this.suffix = suffix; }
}
