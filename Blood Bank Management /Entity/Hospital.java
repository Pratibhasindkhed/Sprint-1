package com.bloodbank.entity;

import javax.persistence.*;

@Entity
@Table(name = "hospital")
public class Hospital {

    @Id
    @Column(name = "hospital_id")
    private String hospitalId;

    @Column(name = "hospital_name", nullable = false, length = 100)
    private String hospitalName;

    @Column(name = "location", nullable = false, length = 255)
    private String location;

    // Many Hospitals contact One BloodBank
    @ManyToOne
    @JoinColumn(name = "bloodbank_id", nullable = false)
    private BloodBank contactedBloodBank;

    // ✅ Default Constructor
    public Hospital() {
    }

    // ✅ Parameterized Constructor
    public Hospital(String hospitalName, String location, BloodBank contactedBloodBank) {
        this.hospitalName = hospitalName;
        this.location = location;
        this.contactedBloodBank = contactedBloodBank;
    }

    // ✅ Getters and Setters
    public String getHospitalId() { return hospitalId; }
    public void setHospitalId(String hospitalId) { this.hospitalId = hospitalId; }

    public String getHospitalName() { return hospitalName; }
    public void setHospitalName(String hospitalName) { this.hospitalName = hospitalName; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public BloodBank getContactedBloodBank() { return contactedBloodBank; }
    public void setContactedBloodBank(BloodBank contactedBloodBank) { this.contactedBloodBank = contactedBloodBank; }
    
    @Override
    public String toString() {
        return "Hospital{" +
                "hospitalId=" + hospitalId +
                ", hospitalName='" + hospitalName + '\'' +
                ", location='" + location + '\'' +
                ", contactedBloodBank=" + contactedBloodBank +
                '}';
    }
}

