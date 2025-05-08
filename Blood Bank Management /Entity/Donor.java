package com.bloodbank.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "donor")
public class Donor {
    
    @Id
    @Column(name = "donor_id",length=20)
    private String donorId;

    @Column(name = "donor_name", nullable = false, length = 50)
    private String donorName;

    @Column(name = "donor_gender", length = 10)
    private String donorGender;

    @Column(name = "donor_address", length = 255)
    private String donorAddress;

    @Column(name = "contact_no", unique = true, length = 15)
    private String contactNo;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "blood_group", nullable = false, length = 10)
    private String bloodGroup;

    @Column(name = "date_of_donation")
    @Temporal(TemporalType.DATE)
    private Date dateOfDonation;

    // Many donors registered by one employee team
    @ManyToOne
    @JoinColumn(name = "emp_id", nullable = false)
    private EmployeeTeam registeredBy;

    // One donor donates multiple blood units
    @OneToMany(mappedBy = "donatedBy", cascade = CascadeType.ALL)
    private List<Blood> donatedBlood;

    // ✅ Default Constructor
    public Donor() {
    }

    // ✅ Parameterized Constructor
    public Donor(String donorName, String donorGender, String donorAddress, String contactNo, int age, String bloodGroup, Date dateOfDonation, EmployeeTeam registeredBy) {
        this.donorName = donorName;
        this.donorGender = donorGender;
        this.donorAddress = donorAddress;
        this.contactNo = contactNo;
        this.age = age;
        this.bloodGroup = bloodGroup;
        this.dateOfDonation = dateOfDonation;
        this.registeredBy = registeredBy;
    }

    // ✅ Getters and Setters
    public String getDonorId() {
        return donorId;
    }

    public void setDonorId(String donorId) {
        this.donorId = donorId;
    }

    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public String getDonorGender() {
        return donorGender;
    }

    public void setDonorGender(String donorGender) {
        this.donorGender = donorGender;
    }

    public String getDonorAddress() {
        return donorAddress;
    }

    public void setDonorAddress(String donorAddress) {
        this.donorAddress = donorAddress;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public Date getDateOfDonation() {
        return dateOfDonation;
    }

    public void setDateOfDonation(Date dateOfDonation) {
        this.dateOfDonation = dateOfDonation;
    }

    public EmployeeTeam getRegisteredBy() {
        return registeredBy;
    }

    public void setRegisteredBy(EmployeeTeam registeredBy) {
        this.registeredBy = registeredBy;
    }

    public List<Blood> getDonatedBlood() {
        return donatedBlood;
    }

    public void setDonatedBlood(List<Blood> donatedBlood) {
        this.donatedBlood = donatedBlood;
    }
    
    @Override
    public String toString() {
        return "Donor{" +
                "donorId=" + donorId +
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
