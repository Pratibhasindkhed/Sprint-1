package com.bloodbank.entity;

import jakarta.persistence.*;

import java.util.List;

import javax.validation.constraints.NotBlank; // Importing NotBlank annotation to ensure the field is not blank
import javax.validation.constraints.Size; // Importing Size annotation to validate string length
import javax.validation.constraints.Pattern; // Importing Pattern annotation to validate the format of the ID

/**
 * Entity class representing a BloodBank in the Blood Bank Management System.
 * This class is mapped to the 'bloodbank' table in the database.
 */
@Entity // Specifies that this class is a JPA entity
@Table(name = "bloodbank") // Maps this entity to the 'bloodbank' table in the database
public class BloodBank {

    // ===== Primary Key: BloodBank ID =====

    /**
     * Unique identifier for the BloodBank entity. This is the primary key of the 'bloodbank' table.
     * The ID is a string type, and we apply validation to ensure it is neither null nor blank.
     */
    @Id // Marks this field as the primary key of the entity
    @Column(name = "bloodbank_id", length = 20) // Maps this field to the 'bloodbank_id' column with a max length of 20
    @NotBlank(message = "Blood Bank ID cannot be blank") // Ensures that the blood bank ID is not blank
    @Pattern(regexp = "^B[0-9]+$", message = "Blood ID must start with 'BB' followed by digits (e.g., BB1, BB2)") // Validates that the bloodId starts with 'B' and is followed by digits
    @Size(max = 20, message = "Blood Bank ID cannot exceed 20 characters") // Ensures that the ID does not exceed 20 characters
    private String bloodBankId; // Declares the bloodBankId field as a String

    // ===== Blood ID Field with Validation =====

    /**
     * Unique identifier for the blood. The ID must start with 'B' followed by digits.
     * This field is validated using annotations to ensure the proper format.
     */
    @Id // Marks this field as the primary key for Blood entity (if Blood entity has this field)
    @NotBlank(message = "Blood ID cannot be blank") // Ensures that the bloodId is not blank
    @Pattern(regexp = "^B[0-9]+$", message = "Blood ID must start with 'B' followed by digits (e.g., B1, B2)") // Validates that the bloodId starts with 'B' and is followed by digits
    @Size(max = 20, message = "Blood ID must not exceed 20 characters") // Ensures that the Blood ID has a maximum length of 20 characters
    @Column(name = "blood_id", length = 20) // Maps the field to the 'blood_id' column in the table with a max length of 20
    private String bloodId; // Declares the bloodId field as a String for blood identification

    // ===== BloodBank Name =====

    /**
     * The name of the blood bank. It is a required field and cannot be blank.
     */
    @Column(name = "name", nullable = false, length = 100) // Maps to the 'name' column with a maximum length of 100
    @NotBlank(message = "Blood Bank Name cannot be blank") // Ensures that the name is not blank
    @Size(max = 100, message = "Blood Bank Name cannot exceed 100 characters") // Validates the length of the name
    private String name; // Declares the name field as a String

    // ===== BloodBank Location =====

    /**
     * The location of the blood bank. This field is required and cannot be blank.
     */
    @Column(name = "location", nullable = false, length = 255) // Maps to the 'location' column with a maximum length of 255
    @NotBlank(message = "Blood Bank Location cannot be blank") // Ensures that the location is not blank
    @Size(max = 255, message = "Blood Bank Location cannot exceed 255 characters") // Validates the length of the location
    private String location; // Declares the location field as a String

    // ===== Available Blood Groups =====

    /**
     * A list of blood groups available in the blood bank. This field is optional and can be blank.
     */
    @Column(name = "available_blood_groups", length = 255) // Maps to the 'available_blood_groups' column with a maximum length of 255
    @Size(max = 255, message = "Available Blood Groups cannot exceed 255 characters") // Validates the length of availableBloodGroups
    private String availableBloodGroups; // Declares the availableBloodGroups field as a String

    // ===== Blood Quantity =====

    /**
     * The quantity of blood available in the blood bank. This field is optional.
     */
    @Column(name = "quantity") // Maps to the 'quantity' column
    private int quantity; // Declares the quantity field as an integer

    // ===== Relationships =====

    /**
     * One blood bank can store many blood units. This sets up a one-to-many relationship with Blood.
     */
    @OneToMany(mappedBy = "storedIn", cascade = CascadeType.ALL) // Specifies a one-to-many relationship with Blood
    private List<Blood> bloodUnits; // Declares the bloodUnits field to link to a list of Blood entities

    /**
     * One blood bank can be associated with many hospitals. This sets up a one-to-many relationship with Hospital.
     */
    @OneToMany(mappedBy = "contactedBloodBank", cascade = CascadeType.ALL) // Specifies a one-to-many relationship with Hospital
    private List<Hospital> hospitals; // Declares the hospitals field to link to a list of Hospital entities

    /**
     * Each blood bank is managed by one admin. This sets up a one-to-one relationship with Admin.
     */
    @OneToOne // Specifies a one-to-one relationship with the Admin entity
    @JoinColumn(name = "admin_id") // Specifies the column in the 'bloodbank' table that links to 'admin_id' in the Admin table
    private Admin admin; // Declares the admin field to link to an Admin entity

    // ===== Constructors =====

    /**
     * Default constructor required by JPA (Java Persistence API).
     */
    public BloodBank() {}

    /**
     * Constructor to initialize the BloodBank object with specific values.
     * @param bloodBankId The unique identifier for the blood bank.
     * @param name The name of the blood bank.
     * @param location The location of the blood bank.
     * @param availableBloodGroups The list of available blood groups.
     * @param quantity The quantity of blood available.
     * @param admin The admin responsible for managing the blood bank.
     */
    public BloodBank(String bloodBankId, String name, String location, String availableBloodGroups, int quantity, Admin admin) {
        this.bloodBankId = bloodBankId; // Initializes bloodBankId with the provided value
        this.name = name; // Initializes name with the provided value
        this.location = location; // Initializes location with the provided value
        this.availableBloodGroups = availableBloodGroups; // Initializes availableBloodGroups with the provided value
        this.quantity = quantity; // Initializes quantity with the provided value
        this.admin = admin; // Initializes admin with the provided Admin object
    }

    // ===== Getters and Setters =====

    public String getBloodBankId() {
        return bloodBankId; // Returns the blood bank identifier
    }

    public void setBloodBankId(String bloodBankId) {
        this.bloodBankId = bloodBankId; // Sets the blood bank identifier
    }

    public String getBloodId() {
        return bloodId; // Returns the blood identifier
    }

    public void setBloodId(String bloodId) {
        this.bloodId = bloodId; // Sets the blood identifier
    }

    public String getName() {
        return name; // Returns the name of the blood bank
    }

    public void setName(String name) {
        this.name = name; // Sets the name of the blood bank
    }

    public String getLocation() {
        return location; // Returns the location of the blood bank
    }

    public void setLocation(String location) {
        this.location = location; // Sets the location of the blood bank
    }

    public String getAvailableBloodGroups() {
        return availableBloodGroups; // Returns the available blood groups
    }

    public void setAvailableBloodGroups(String availableBloodGroups) {
        this.availableBloodGroups = availableBloodGroups; // Sets the available blood groups
    }

    public int getQuantity() {
        return quantity; // Returns the quantity of blood
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity; // Sets the quantity of blood
    }

    public List<Blood> getBloodUnits() {
        return bloodUnits; // Returns the list of blood units
    }

    public void setBloodUnits(List<Blood> bloodUnits) {
        this.bloodUnits = bloodUnits; // Sets the list of blood units
    }

    public List<Hospital> getHospitals() {
        return hospitals; // Returns the list of hospitals associated with the blood bank
    }

    public void setHospitals(List<Hospital> hospitals) {
        this.hospitals = hospitals; // Sets the list of hospitals
    }

    public Admin getAdmin() {
        return admin; // Returns the admin managing the blood bank
    }

    public void setAdmin(Admin admin) {
        this.admin = admin; // Sets the admin managing the blood bank
    }

    // ===== toString Method =====

    /**
     * Returns a string representation of the BloodBank object, showing all relevant fields.
     * @return A string representation of the BloodBank object
     */
    @Override
    public String toString() {
        return "BloodBank{" +
                "bloodBankId='" + bloodBankId + '\'' + // Displays the blood bank identifier
                ", bloodId='" + bloodId + '\'' + // Displays the blood identifier
                ", name='" + name + '\'' + // Displays the name of the blood bank
                ", location='" + location + '\'' + // Displays the location of the blood bank
                ", availableBloodGroups='" + availableBloodGroups + '\'' + // Displays the available blood groups
                ", quantity=" + quantity + // Displays the quantity of blood
                ", admin=" + admin + // Displays the admin managing the blood bank
                '}'; // Closing the string representation
    }
}

