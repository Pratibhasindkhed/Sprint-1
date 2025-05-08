package com.bloodbank.entity; // Specifies the package where the entity class belongs

// Importing the necessary JPA and Hibernate Validator annotations
import javax.validation.constraints.NotBlank; // For validating that the field is not blank
import javax.validation.constraints.Pattern; // For validating the field's value against a regular expression
import javax.validation.constraints.Size; // For validating the size of the string

import jakarta.persistence.*;// JPA annotations for entity mapping and relationships

/**
 * Entity class representing a Blood Unit in the Blood Bank Management System.
 * This class is mapped to the 'blood' table in the database.
 */
@Entity // Marks this class as a JPA entity, meaning it will be mapped to a table in the database
@Table(name = "blood") // Specifies the table name in the database ('blood' table)
public class Blood {

    // ===== Primary Key: Blood ID =====

    /**
     * Unique identifier for the Blood entity. This is the primary key for the 'blood' table.
     * The ID is a string (e.g., B1, B2), and it cannot be blank.
     * Validates that bloodId is not blank and follows a specific format using a regex pattern.
     */
    @Id // Specifies that this field is the primary key of the entity
    @NotBlank(message = "Blood ID cannot be blank") // Validates that the bloodId is not blank
    @Pattern(regexp = "^B[0-9]+$", message = "Blood ID must start with 'B' followed by digits (e.g., B1, B2)") // Validates that the bloodId starts with 'B' and is followed by digits
    @Size(max = 20, message = "Blood ID must not exceed 20 characters") // Ensures that the Blood ID has a maximum length of 20 characters
    @Column(name = "blood_id", length = 20) // Maps the field to the 'blood_id' column in the table with a max length of 20
    private String bloodId; // Declares the bloodId field as a String

    // ===== Blood Group =====

    /**
     * The blood group of the blood unit. It cannot be blank and should be at most 10 characters.
     */
    @NotBlank(message = "Blood group is required") // Validates that the bloodGroup is not blank
    @Size(max = 10, message = "Blood group cannot exceed 10 characters") // Validates that the blood group has a maximum length of 10
    @Column(name = "blood_group", nullable = false, length = 10) // Maps the field to the 'blood_group' column in the table with a max length of 10
    private String bloodGroup; // Declares the bloodGroup field as a String

    // ===== Relationships =====

    /**
     * Many blood units are stored in one blood bank. This sets up a many-to-one relationship with BloodBank.
     */
    @ManyToOne // Specifies a many-to-one relationship with the BloodBank entity
    @JoinColumn(name = "bloodbank_id", nullable = false) // Specifies the column in the 'blood' table that links to 'bloodbank_id' in the BloodBank table
    private BloodBank storedIn; // Declares the storedIn field to link to a BloodBank object

    /**
     * Many blood units can be donated by one donor. This sets up a many-to-one relationship with Donor.
     */
    @ManyToOne // Specifies a many-to-one relationship with the Donor entity
    @JoinColumn(name = "donor_id") // Specifies the column in the 'blood' table that links to 'donor_id' in the Donor table
    private Donor donatedBy; // Declares the donatedBy field to link to a Donor object

    /**
     * Many blood units can be received by one patient. This sets up a many-to-one relationship with Patient.
     */
    @ManyToOne // Specifies a many-to-one relationship with the Patient entity
    @JoinColumn(name = "patient_id") // Specifies the column in the 'blood' table that links to 'patient_id' in the Patient table
    private Patient receivedBy; // Declares the receivedBy field to link to a Patient object

    // ===== Constructors =====

    /**
     * Default constructor required by JPA (Java Persistence API).
     */
    public Blood() {}

    /**
     * Constructor to initialize the Blood object with specific values.
     * @param bloodGroup The blood group of the blood unit.
     * @param storedIn The blood bank where the blood unit is stored.
     * @param donatedBy The donor who donated the blood.
     * @param receivedBy The patient who received the blood.
     */
    public Blood(String bloodGroup, BloodBank storedIn, Donor donatedBy, Patient receivedBy) {
        this.bloodGroup = bloodGroup; // Initializes bloodGroup with the provided value
        this.storedIn = storedIn; // Initializes storedIn with the provided BloodBank object
        this.donatedBy = donatedBy; // Initializes donatedBy with the provided Donor object
        this.receivedBy = receivedBy; // Initializes receivedBy with the provided Patient object
    }

    // ===== Getters and Setters =====

    /**
     * Getter for bloodId.
     * @return bloodId
     */
    public String getBloodId() {
        return bloodId; // Returns the value of bloodId
    }

    /**
     * Setter for bloodId.
     * @param bloodId Sets the bloodId to the provided value
     */
    public void setBloodId(String bloodId) {
        this.bloodId = bloodId; // Sets the value of bloodId
    }

    /**
     * Getter for bloodGroup.
     * @return bloodGroup
     */
    public String getBloodGroup() {
        return bloodGroup; // Returns the value of bloodGroup
    }

    /**
     * Setter for bloodGroup.
     * @param bloodGroup Sets the bloodGroup to the provided value
     */
    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup; // Sets the value of bloodGroup
    }

    /**
     * Getter for storedIn (BloodBank).
     * @return storedIn (BloodBank)
     */
    public BloodBank getStoredIn() {
        return storedIn; // Returns the associated BloodBank where the blood is stored
    }

    /**
     * Setter for storedIn (BloodBank).
     * @param storedIn Sets the storedIn field to the provided BloodBank object
     */
    public void setStoredIn(BloodBank storedIn) {
        this.storedIn = storedIn; // Sets the value of storedIn
    }

    /**
     * Getter for donatedBy (Donor).
     * @return donatedBy (Donor)
     */
    public Donor getDonatedBy() {
        return donatedBy; // Returns the associated Donor who donated the blood unit
    }

    /**
     * Setter for donatedBy (Donor).
     * @param donatedBy Sets the donatedBy field to the provided Donor object
     */
    public void setDonatedBy(Donor donatedBy) {
        this.donatedBy = donatedBy; // Sets the value of donatedBy
    }

    /**
     * Getter for receivedBy (Patient).
     * @return receivedBy (Patient)
     */
    public Patient getReceivedBy() {
        return receivedBy; // Returns the associated Patient who received the blood unit
    }

    /**
     * Setter for receivedBy (Patient).
     * @param receivedBy Sets the receivedBy field to the provided Patient object
     */
    public void setReceivedBy(Patient receivedBy) {
        this.receivedBy = receivedBy; // Sets the value of receivedBy
    }

    // ===== toString Method =====

    /**
     * Returns a string representation of the Blood object, showing all relevant fields.
     * @return A string representation of the Blood object
     */
    @Override
    public String toString() {
        return "Blood{" +
                "bloodId='" + bloodId + '\'' + // Displays the bloodId
                ", bloodGroup='" + bloodGroup + '\'' + // Displays the bloodGroup
                ", storedIn=" + storedIn + // Displays the BloodBank where the blood is stored
                ", donatedBy=" + donatedBy + // Displays the Donor who donated the blood
                ", receivedBy=" + receivedBy + // Displays the Patient who received the blood
                '}'; // Closing the string representation
    }
}
