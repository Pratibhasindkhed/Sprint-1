package com.bloodbank.entity;

import javax.persistence.*;

@Entity
@Table(name = "admin")
public class Admin {

    @Id
    @Column(name = "admin_id",length=20)
    private String adminId;

    @Column(name = "admin_name", nullable = false, length = 100)
    private String adminName;

    @Column(name = "location", length = 255)
    private String location;

    // One Admin manages One BloodBank
    @OneToOne(mappedBy = "admin", cascade = CascadeType.ALL)
    private BloodBank managedBloodBank;

    // ✅ Default Constructor
    public Admin() {
    }

    // ✅ Parameterized Constructor
    public Admin(String adminName, String location) {
        this.adminName = adminName;
        this.location = location;
    }

    // ✅ Getters and Setters
    public String getAdminId() { return adminId; }
    public void setAdminId(String adminId) { this.adminId = adminId; }

    public String getAdminName() { return adminName; }
    public void setAdminName(String adminName) { this.adminName = adminName; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public BloodBank getManagedBloodBank() { return managedBloodBank; }
    public void setManagedBloodBank(BloodBank managedBloodBank) { this.managedBloodBank = managedBloodBank; }
    
    @Override
    public String toString() {
        return "Admin{" +
                "adminId=" + adminId +
                ", adminName='" + adminName + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
