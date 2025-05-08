// Specifies that this class belongs to the 'com.bloodbank.entity' package
package com.bloodbank.entity;

// JPA and validation annotations
import jakarta.persistence.*;
import javax.validation.constraints.*;

@Entity // Marks this class as a JPA entity (maps to a table)
@Table(name = "hospital") // Specifies the name of the table in the database
public class Hospital {

    // Primary key for the Hospital entity
    @Id // Marks this field as the primary key
    @NotBlank(message = "Hospital ID cannot be blank") // Validates that the hospital ID is not empty
    @Pattern(regexp = "^H[0-9]+$", message = "Hospital ID must start with 'H' followed by digits (e.g., H1, H2)") // Must follow custom format
    @Size(max = 20, message = "Hospital ID must not exceed 20 characters") // Restricts the maximum length
    @Column(name = "hospital_id", length = 20) // Maps to 'hospital_id' column in DB
    private String hospitalId;

    // Hospital name field with constraints
    @NotBlank(message = "Hospital name is required") // Must not be blank
    @Size(max = 100, message = "Hospital name must not exceed 100 characters") // Max 100 characters
    @Column(name = "hospital_name", nullable = false, length = 100) // Maps to 'hospital_name' column
    private String hospitalName;

    // Hospital location field with constraints
    @NotBlank(message = "Location is required") // Must not be blank
    @Size(max = 255, message = "Location must not exceed 255 characters") // Max 255 characters
    @Column(name = "location", nullable = false, length = 255) // Maps to 'location' column
    private String location;

    // Many-to-one relationship: many hospitals contact one blood bank
    @ManyToOne // Specifies many hospitals can be linked to one blood bank
    @JoinColumn(name = "bloodbank_id", nullable = false) // Maps the foreign key column 'bloodbank_id'
    @NotNull(message = "BloodBank reference must not be null") // Ensures this field is not null
    private BloodBank contactedBloodBank;

    // Default constructor (required by JPA)
    public Hospital() {
    }

    // Parameterized constructor (excluding ID, assuming it's generated/set externally)
    public Hospital(String hospitalName, String location, BloodBank contactedBloodBank) {
        this.hospitalName = hospitalName;
        this.location = location;
        this.contactedBloodBank = contactedBloodBank;
    }

    // Getter and setter for hospitalId
    public String getHospitalId() {
        return hospitalId;
    }
    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    // Getter and setter for hospitalName
    public String getHospitalName() {
        return hospitalName;
    }
    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    // Getter and setter for location
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    // Getter and setter for contactedBloodBank
    public BloodBank getContactedBloodBank() {
        return contactedBloodBank;
    }
    public void setContactedBloodBank(BloodBank contactedBloodBank) {
        this.contactedBloodBank = contactedBloodBank;
    }

    // Overridden toString method for printing object info
    @Override
    public String toString() {
        return "Hospital{" +
                "hospitalId='" + hospitalId + '\'' +
                ", hospitalName='" + hospitalName + '\'' +
                ", location='" + location + '\'' +
                ", contactedBloodBank=" + contactedBloodBank +
                '}';
    }
}
