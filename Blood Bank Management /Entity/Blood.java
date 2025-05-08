package com.bloodbank.entity;

import javax.persistence.*;

@Entity
@Table(name = "blood")
public class Blood {

    @Id
    @Column(name = "blood_id",length=20)
    private String bloodId;

    @Column(name = "blood_group", nullable = false, length = 10)
    private String bloodGroup;

    // Many blood units stored in one blood bank
    @ManyToOne
    @JoinColumn(name = "bloodbank_id", nullable = false)
    private BloodBank storedIn;

    // Many blood units can be donated by one donor
    @ManyToOne
    @JoinColumn(name = "donor_id")
    private Donor donatedBy;

    // Many blood units can be received by one patient
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient receivedBy;

    // ✅ Default Constructor
    public Blood() {
    }

    // ✅ Parameterized Constructor
    public Blood(String bloodGroup, BloodBank storedIn, Donor donatedBy, Patient receivedBy) {
        this.bloodGroup = bloodGroup;
        this.storedIn = storedIn;
        this.donatedBy = donatedBy;
        this.receivedBy = receivedBy;
    }

    // ✅ Getters and Setters
    public String getBloodId() { return bloodId; }
    public void setBloodId(String bloodId) { this.bloodId = bloodId; }

    public String getBloodGroup() { return bloodGroup; }
    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }

    public BloodBank getStoredIn() { return storedIn; }
    public void setStoredIn(BloodBank storedIn) { this.storedIn = storedIn; }

    public Donor getDonatedBy() { return donatedBy; }
    public void setDonatedBy(Donor donatedBy) { this.donatedBy = donatedBy; }

    public Patient getReceivedBy() { return receivedBy; }
    public void setReceivedBy(Patient receivedBy) { this.receivedBy = receivedBy; }
     
    @Override
    public String toString() {
        return "Blood{" +
                "bloodId=" + bloodId +
                ", bloodGroup='" + bloodGroup + '\'' +
                ", storedIn=" + storedIn +
                ", donatedBy=" + donatedBy +
                ", receivedBy=" + receivedBy +
                '}';
    }
}


