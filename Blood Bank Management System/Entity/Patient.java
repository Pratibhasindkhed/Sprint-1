// Package declaration
package com.bloodbank.entity;

// Import JPA and validation annotations
import jakarta.persistence.*;
import javax.validation.constraints.*;

import java.util.Date;
import java.util.List;

@Entity // Marks this class as a JPA entity
@Table(name = "patient") // Maps the class to the 'patient' table in the database
public class Patient {

    // Primary key for Patient entity
    @Id // Declares this field as the primary key
    @NotBlank(message = "Patient ID cannot be blank") // Ensures patientId is not blank
    @Pattern(regexp = "^P[0-9]+$", message = "Patient ID must start with 'P' followed by digits (e.g., P1, P2)") // Pattern format
    @Size(max = 20, message = "Patient ID must not exceed 20 characters") // Restricts length
    @Column(name = "patient_id", length = 20) // Maps to 'patient_id' column
    private String patientId;

    // Name of the patient
    @NotBlank(message = "Patient name is required") // Ensures not blank
    @Size(max = 50, message = "Patient name must not exceed 50 characters") // Restricts length
    @Column(name = "patient_name", nullable = false, length = 50) // Maps to 'patient_name'
    private String patientName;

    // Gender field
    @Size(max = 10, message = "Gender must not exceed 10 characters") // Optional, but length-constrained
    @Column(name = "patient_gender", length = 10) // Maps to 'patient_gender'
    private String patientGender;

    // Address field
    @Size(max = 255, message = "Address must not exceed 255 characters") // Optional, but with length limit
    @Column(name = "patient_address", length = 255) // Maps to 'patient_address'
    private String patientAddress;

    // Contact number with uniqueness and format
    @NotBlank(message = "Contact number is required") // Ensures not blank
    @Pattern(regexp = "^[0-9]{10,15}$", message = "Contact number must be between 10 and 15 digits") // Validates phone format
    @Column(name = "contact_no", unique = true, length = 15) // Maps to 'contact_no'
    private String contactNo;

    // Blood group field
    @NotBlank(message = "Blood group is required") // Ensures not blank
    @Size(max = 10, message = "Blood group must not exceed 10 characters") // Restricts length
    @Column(name = "blood_group", nullable = false, length = 10) // Maps to 'blood_group'
    private String bloodGroup;

    // Date of intake field
    @PastOrPresent(message = "Date of intake cannot be in the future") // Validates date is today or past
    @Temporal(TemporalType.DATE) // Only date part (not time)
    @Column(name = "date_of_intake") // Maps to 'date_of_intake'
    private Date dateOfIntake;

    // Many-to-one relationship with EmployeeTeam
    @ManyToOne // Many patients can be registered by one employee
    @JoinColumn(name = "emp_id", nullable = false) // Maps foreign key column 'emp_id'
    @NotNull(message = "Employee who registered must not be null") // Ensures it is provided
    private EmployeeTeam registeredBy;

    // One-to-many relationship: patient can receive multiple blood units
    @OneToMany(mappedBy = "receivedBy", cascade = CascadeType.ALL) // Maps 'receivedBy' in Blood entity
    private List<Blood> receivedBlood;

    // Default constructor (JPA requirement)
    public Patient() {
    }

    // Parameterized constructor (without ID)
    public Patient(String patientName, String patientGender, String patientAddress, String contactNo, String bloodGroup, Date dateOfIntake, EmployeeTeam registeredBy) {
        this.patientName = patientName;
        this.patientGender = patientGender;
        this.patientAddress = patientAddress;
        this.contactNo = contactNo;
        this.bloodGroup = bloodGroup;
        this.dateOfIntake = dateOfIntake;
        this.registeredBy = registeredBy;
    }

    // Getter and setter methods for all fields

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender;
    }

    public String getPatientAddress() {
        return patientAddress;
    }

    public void setPatientAddress(String patientAddress) {
        this.patientAddress = patientAddress;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public Date getDateOfIntake() {
        return dateOfIntake;
    }

    public void setDateOfIntake(Date dateOfIntake) {
        this.dateOfIntake = dateOfIntake;
    }

    public EmployeeTeam getRegisteredBy() {
        return registeredBy;
    }

    public void setRegisteredBy(EmployeeTeam registeredBy) {
        this.registeredBy = registeredBy;
    }

    public List<Blood> getReceivedBlood() {
        return receivedBlood;
    }

    public void setReceivedBlood(List<Blood> receivedBlood) {
        this.receivedBlood = receivedBlood;
    }

    // Overridden toString method for easy logging
    @Override
    public String toString() {
        return "Patient{" +
                "patientId='" + patientId + '\'' +
                ", patientName='" + patientName + '\'' +
                ", patientGender='" + patientGender + '\'' +
                ", patientAddress='" + patientAddress + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", bloodGroup='" + bloodGroup + '\'' +
                ", dateOfIntake=" + dateOfIntake +
                ", registeredBy=" + registeredBy +
                '}';
    }
}
