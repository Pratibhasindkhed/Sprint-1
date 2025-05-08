package com.bloodbank.entity; // Declares the package for this class

// Importing necessary JPA and validation packages
import jakarta.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Entity // Specifies that this class is a JPA entity
@Table(name = "donor") // Maps this entity to the 'donor' table in the database
public class Donor {

    // ================================
    // Primary Key: String Donor ID
    // ================================

    @Id // Marks this field as the primary key of the entity
    @NotBlank(message = "Donor ID cannot be blank") // Validates that the ID is not empty
    @Pattern(regexp = "^D[0-9]+$", message = "Donor ID must start with 'D' followed by digits (e.g., D1, D2)") // Custom pattern rule
    @Size(max = 20, message = "Donor ID cannot exceed 20 characters") // Limits the length of the ID
    @Column(name = "donor_id", length = 20) // Maps this field to the 'donor_id' column in the DB
    private String donorId; // Declares the donorId as a String

    // ================================
    // Donor Name
    // ================================

    @NotBlank(message = "Donor Name cannot be blank") // Must not be null or empty
    @Size(max = 50, message = "Donor Name cannot exceed 50 characters") // Max length 50
    @Column(name = "donor_name", nullable = false, length = 50) // DB column settings
    private String donorName; // Stores donor's full name

    // ================================
    // Donor Gender
    // ================================

    @Size(max = 10, message = "Donor Gender cannot exceed 10 characters") // Optional, but max 10 characters
    @Column(name = "donor_gender", length = 10) // Maps to 'donor_gender' column
    private String donorGender; // Stores gender

    // ================================
    // Donor Address
    // ================================

    @Size(max = 255, message = "Donor Address cannot exceed 255 characters") // Max size 255
    @Column(name = "donor_address", length = 255) // DB column settings
    private String donorAddress; // Stores full address of donor

    // ================================
    // Contact Number
    // ================================

    @NotBlank(message = "Contact number cannot be blank") // Must be provided
    @Pattern(regexp = "^\\d{10}$", message = "Contact number must be exactly 10 digits") // Only digits, exactly 10
    @Column(name = "contact_no", unique = true, length = 10) // Unique, 10-digit phone number
    private String contactNo; // Stores contact number

    // ================================
    // Age
    // ================================

    @Min(value = 18, message = "Age must be at least 18") // Lower bound
    @Max(value = 100, message = "Age must be less than or equal to 100") // Upper bound
    @Column(name = "age", nullable = false) // Non-nullable DB column
    private int age; // Stores age of donor

    // ================================
    // Blood Group
    // ================================

    @NotBlank(message = "Blood group cannot be blank") // Required field
    @Size(max = 10, message = "Blood group cannot exceed 10 characters") // Max length
    @Column(name = "blood_group", nullable = false, length = 10) // DB column settings
    private String bloodGroup; // Stores donor's blood group (e.g., A+, O-)

    // ================================
    // Date of Donation
    // ================================

    @NotNull(message = "Date of donation cannot be null") // Must be provided
    @Temporal(TemporalType.DATE) // Only the date is stored (no time)
    @Column(name = "date_of_donation") // DB column mapping
    private Date dateOfDonation; // Date when blood was donated

    // ================================
    // Many-to-One Relationship: Registered By
    // ================================

    @ManyToOne // Many donors can be registered by one employee
    @JoinColumn(name = "emp_id", nullable = false) // Foreign key to EmployeeTeam table
    private EmployeeTeam registeredBy; // Reference to the employee who registered the donor

    // ================================
    // One-to-Many Relationship: Donated Blood Units
    // ================================

    @OneToMany(mappedBy = "donatedBy", cascade = CascadeType.ALL) // One donor → many blood units
    private List<Blood> donatedBlood; // List of blood donations by the donor

    // ================================
    // Constructors
    // ================================

    // Default constructor (required by Hibernate)
    public Donor() {}

    // Parameterized constructor (excluding list to keep it simple)
    public Donor(String donorId, String donorName, String donorGender, String donorAddress, String contactNo, int age, String bloodGroup, Date dateOfDonation, EmployeeTeam registeredBy) {
        this.donorId = donorId;
        this.donorName = donorName;
        this.donorGender = donorGender;
        this.donorAddress = donorAddress;
        this.contactNo = contactNo;
        this.age = age;
        this.bloodGroup = bloodGroup;
        this.dateOfDonation = dateOfDonation;
        this.registeredBy = registeredBy;
    }

    // ================================
    // Getters and Setters
    // ================================

    public String getDonorId() { return donorId; }
    public void setDonorId(String donorId) { this.donorId = donorId; }

    public String getDonorName() { return donorName; }
    public void setDonorName(String donorName) { this.donorName = donorName; }

    public String getDonorGender() { return donorGender; }
    public void setDonorGender(String donorGender) { this.donorGender = donorGender; }

    public String getDonorAddress() { return donorAddress; }
    public void setDonorAddress(String donorAddress) { this.donorAddress = donorAddress; }

    public String getContactNo() { return contactNo; }
    public void setContactNo(String contactNo) { this.contactNo = contactNo; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getBloodGroup() { return bloodGroup; }
    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }

    public Date getDateOfDonation() { return dateOfDonation; }
    public void setDateOfDonation(Date dateOfDonation) { this.dateOfDonation = dateOfDonation; }

    public EmployeeTeam getRegisteredBy() { return registeredBy; }
    public void setRegisteredBy(EmployeeTeam registeredBy) { this.registeredBy = registeredBy; }

    public List<Blood> getDonatedBlood() { return donatedBlood; }
    public void setDonatedBlood(List<Blood> donatedBlood) { this.donatedBlood = donatedBlood; }

    // ================================
    // toString Method (for printing)
    // ================================

    @Override
    public String toString() {
        return "Donor{" +
                "donorId='" + donorId + '\'' +
                ", donorName='" + donorName + '\'' +
                ", donorGender='" + donorGender + '\'' +
                ", donorAddress='" + donorAddress + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", age=" + age +
                ", bloodGroup='" + bloodGroup + '\'' +
                ", dateOfDonation=" + dateOfDonation +
                ", registeredBy=" + registeredBy +
                '}';
    }
}
