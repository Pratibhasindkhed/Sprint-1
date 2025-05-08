package com.bloodbank.entity; // Importing the package where this entity belongs

import javax.validation.constraints.NotBlank;// @NotBlank is used to validate that the field is not null or empty after trimming whitespace
import javax.validation.constraints.Pattern;// @Pattern is used to validate that the field matches a given regular expression pattern
import javax.validation.constraints.Size;// @Size is used to validate that the field's length is within a specified range (min and max lengths)

import jakarta.persistence.*;// JPA annotations for entity mapping and relationships

/**
 * Entity class representing an Admin in the Blood Bank Management System.
 * This class is mapped to the 'admin' table in the database.
 */
@Entity // Marks this class as a JPA entity to be mapped to a database table
@Table(name = "admin") // Specifies the name of the table ('admin') in the database
public class Admin {

    // ===== Primary Key: Admin ID =====

    /**
     * Unique identifier for the Admin. It must follow the format "A" followed by digits (e.g., A1, A2).
     * It cannot be blank, must be <= 20 characters, and must match the specified pattern.
     */
    @Id // Marks this field as the primary key of the entity
    @NotBlank(message = "Admin ID is required") // Validates that the field is not blank
    @Pattern(regexp = "^A\\d+$", message = "Admin ID must start with 'A' followed by digits (e.g., A1, A2)") // Validates that the ID matches the pattern: A followed by digits
    @Size(max = 20, message = "Admin ID must not exceed 20 characters") // Validates that the ID is no longer than 20 characters
    @Column(name = "admin_id", length = 20) // Maps the field to the 'admin_id' column in the table with a max length of 20 characters
    private String adminId; // Declares the adminId field as a String

    // ===== Admin Name =====

    /**
     * Name of the Admin. It cannot be blank, must be between 2 and 50 characters in length.
     */
    @NotBlank(message = "Admin name is required") // Validates that the field is not blank
    @Size(min = 2, max = 50, message = "Admin name must be between 2 and 50 characters") // Validates that the name is between 2 and 50 characters
    @Column(name = "admin_name", nullable = false, length = 50) // Maps the field to the 'admin_name' column in the table with a max length of 50
    private String adminName; // Declares the adminName field as a String

    // ===== Location =====

    /**
     * Location of the Admin or the assigned branch. It is optional but, if provided, cannot exceed 100 characters.
     */
    @Size(max = 100, message = "Location must be at most 100 characters") // Validates that the location is no longer than 100 characters
    @Column(name = "location", length = 100) // Maps the field to the 'location' column in the table with a max length of 100
    private String location; // Declares the location field as a String

    // ===== Contact Number =====

    /**
     * Admin's contact number. It must consist of exactly 10 digits.
     */
    @Pattern(regexp = "^[0-9]{10}$", message = "Contact number must be exactly 10 digits") // Validates that the contact number consists of exactly 10 digits
    @Column(name = "contact_number", length = 10) // Maps the field to the 'contact_number' column in the table with a max length of 10
    private String contactNumber; // Declares the contactNumber field as a String

    // ===== One-to-One Relationship with BloodBank =====

    /**
     * A one-to-one relationship where one Admin manages one BloodBank.
     * This establishes a bidirectional relationship.
     */
    @OneToOne(mappedBy = "admin", cascade = CascadeType.ALL) // Specifies a one-to-one relationship with BloodBank entity
    private BloodBank managedBloodBank; // Declares the managedBloodBank field as a BloodBank object

    // ===== Default Constructor =====

    /**
     * Default constructor required by JPA (Java Persistence API).
     */
    public Admin() {}

    // ===== Parameterized Constructor =====

    /**
     * Constructor with parameters to initialize the Admin object.
     * @param adminName Admin's name
     * @param location Admin's location
     * @param contactNumber Admin's contact number
     */
    public Admin(String adminName, String location, String contactNumber) {
        this.adminName = adminName; // Initializes adminName with the provided value
        this.location = location; // Initializes location with the provided value
        this.contactNumber = contactNumber; // Initializes contactNumber with the provided value
    }

    // ===== Getters and Setters =====

    /**
     * Getter for adminId.
     * @return adminId
     */
    public String getAdminId() {
        return adminId; // Returns the value of adminId
    }

    /**
     * Setter for adminId.
     * @param adminId Sets the adminId to the provided value
     */
    public void setAdminId(String adminId) {
        this.adminId = adminId; // Sets the value of adminId
    }

    /**
     * Getter for adminName.
     * @return adminName
     */
    public String getAdminName() {
        return adminName; // Returns the value of adminName
    }

    /**
     * Setter for adminName.
     * @param adminName Sets the adminName to the provided value
     */
    public void setAdminName(String adminName) {
        this.adminName = adminName; // Sets the value of adminName
    }

    /**
     * Getter for location.
     * @return location
     */
    public String getLocation() {
        return location; // Returns the value of location
    }

    /**
     * Setter for location.
     * @param location Sets the location to the provided value
     */
    public void setLocation(String location) {
        this.location = location; // Sets the value of location
    }

    /**
     * Getter for contactNumber.
     * @return contactNumber
     */
    public String getContactNumber() {
        return contactNumber; // Returns the value of contactNumber
    }

    /**
     * Setter for contactNumber.
     * @param contactNumber Sets the contactNumber to the provided value
     */
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber; // Sets the value of contactNumber
    }

    /**
     * Getter for managedBloodBank.
     * @return managedBloodBank
     */
    public BloodBank getManagedBloodBank() {
        return managedBloodBank; // Returns the associated managedBloodBank
    }

    /**
     * Setter for managedBloodBank.
     * @param managedBloodBank Sets the managedBloodBank to the provided value
     */
    public void setManagedBloodBank(BloodBank managedBloodBank) {
        this.managedBloodBank = managedBloodBank; // Sets the value of managedBloodBank
    }

    // ===== toString Method =====

    /**
     * Returns a string representation of the Admin object.
     * @return A string describing the Admin
     */
    @Override
    public String toString() {
        return "Admin{" +
                "adminId='" + adminId + '\'' + // Displays the adminId
                ", adminName='" + adminName + '\'' + // Displays the adminName
                ", location='" + location + '\'' + // Displays the location
                ", contactNumber='" + contactNumber + '\'' + // Displays the contactNumber
                ", managedBloodBank=" + managedBloodBank + // Displays the associated managedBloodBank
                '}';
    }
}
