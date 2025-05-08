// Specifies the package this class belongs to
package com.bloodbank.entity;

// Imports JPA and validation annotations
import jakarta.persistence.*;
import javax.validation.constraints.*;

import java.util.List;

// Marks this class as a JPA entity
@Entity
// Maps this class to the "employee_team" table in the database
@Table(name = "employee_team")
public class EmployeeTeam {

    // Specifies that this field is the primary key of the entity
    @Id
    @NotBlank(message = "Employee ID cannot be blank") // Validates that empId is not blank
    @Pattern(regexp = "^E[0-9]+$", message = "Employee ID must start with 'E' followed by digits (e.g., E1, E2)") // Pattern for employee ID
    @Size(max = 20, message = "Employee ID must not exceed 20 characters") // Max length of 20
    @Column(name = "emp_id", length = 20) // Maps to "emp_id" column in table
    private String empId;

    // Validates that the employee name is not blank
    @NotBlank(message = "Employee name is required")
    @Size(max = 100, message = "Employee name must not exceed 100 characters") // Limits length
    @Column(name = "emp_name", nullable = false, length = 100) // Maps to "emp_name" column
    private String empName;

    // Validates that the contact number is not blank
    @NotBlank(message = "Contact number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Contact number must be 10 digits only") // Only digits, 10 digits
    @Column(name = "contact_no", nullable = false, unique = true, length = 15) // Maps to "contact_no" column
    private String contactNo;

    // One employee can register multiple donors
    @OneToMany(mappedBy = "registeredBy", cascade = CascadeType.ALL)
    private List<Donor> donors;

    // One employee can register multiple patients
    @OneToMany(mappedBy = "registeredBy", cascade = CascadeType.ALL)
    private List<Patient> patients;

    // Default constructor (required by JPA)
    public EmployeeTeam() {
    }

    // Parameterized constructor (excluding ID for auto-generation or setting separately)
    public EmployeeTeam(String empName, String contactNo) {
        this.empName = empName;
        this.contactNo = contactNo;
    }

    // Getter and setter for empId
    public String getEmpId() {
        return empId;
    }
    public void setEmpId(String empId) {
        this.empId = empId;
    }

    // Getter and setter for empName
    public String getEmpName() {
        return empName;
    }
    public void setEmpName(String empName) {
        this.empName = empName;
    }

    // Getter and setter for contactNo
    public String getContactNo() {
        return contactNo;
    }
    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    // Getter and setter for donors
    public List<Donor> getDonors() {
        return donors;
    }
    public void setDonors(List<Donor> donors) {
        this.donors = donors;
    }

    // Getter and setter for patients
    public List<Patient> getPatients() {
        return patients;
    }
    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    // toString method for debugging or display purposes
    @Override
    public String toString() {
        return "EmployeeTeam{" +
                "empId='" + empId + '\'' +
                ", empName='" + empName + '\'' +
                ", contactNo='" + contactNo + '\'' +
                '}';
    }
}
