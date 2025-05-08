package com.bloodbank.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "bloodbank")
public class BloodBank {

    @Id
    @Column(name = "bloodbank_id",length=20)
    private String bloodBankId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "location", nullable = false, length = 255)
    private String location;

    @Column(name = "available_blood_groups", length = 255)
    private String availableBloodGroups;

    @Column(name = "quantity")
    private int quantity;

    // One BloodBank stores multiple Blood units
    @OneToMany(mappedBy = "storedIn", cascade = CascadeType.ALL)
    private List<Blood> bloodUnits;

    // One BloodBank is contacted by multiple Hospitals
    @OneToMany(mappedBy = "contactedBloodBank", cascade = CascadeType.ALL)
    private List<Hospital> hospitals;

    // One BloodBank is managed by one Admin
    @OneToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    // ✅ Default Constructor
    public BloodBank() {
    }

    // ✅ Parameterized Constructor
    public BloodBank(String name, String location, String availableBloodGroups, int quantity, Admin admin) {
        this.name = name;
        this.location = location;
        this.availableBloodGroups = availableBloodGroups;
        this.quantity = quantity;
        this.admin = admin;
    }

    // ✅ Getters and Setters
    public String getBloodBankId() { return bloodBankId; }
    public void setBloodBankId(String bloodBankId) { this.bloodBankId = bloodBankId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getAvailableBloodGroups() { return availableBloodGroups; }
    public void setAvailableBloodGroups(String availableBloodGroups) { this.availableBloodGroups = availableBloodGroups; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public List<Blood> getBloodUnits() { return bloodUnits; }
    public void setBloodUnits(List<Blood> bloodUnits) { this.bloodUnits = bloodUnits; }

    public List<Hospital> getHospitals() { return hospitals; }
    public void setHospitals(List<Hospital> hospitals) { this.hospitals = hospitals; }

    public Admin getAdmin() { return admin; }
    public void setAdmin(Admin admin) { this.admin = admin; }
    
    @Override
    public String toString() {
        return "BloodBank{" +
                "bloodBankId=" + bloodBankId +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", availableBloodGroups='" + availableBloodGroups + '\'' +
                ", quantity=" + quantity +
                ", admin=" + admin +
                '}';
    }
}

