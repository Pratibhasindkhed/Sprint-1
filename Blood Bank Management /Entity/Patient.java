package com.bloodbank.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @Column(name = "patient_id")
    private String patientId;

    @Column(name = "patient_name", nullable = false, length = 50)
    private String patientName;

    @Column(name = "patient_gender", length = 10)
    private String patientGender;

    @Column(name = "patient_address", length = 255)
    private String patientAddress;

    @Column(name = "contact_no", unique = true, length = 15)
    private String contactNo;

    @Column(name = "blood_group", nullable = false, length = 10)
    private String bloodGroup;

    @Column(name = "date_of_intake")
    @Temporal(TemporalType.DATE)
    private Date dateOfIntake;

    // Many patients registered by one employee team
    @ManyToOne
    @JoinColumn(name = "emp_id", nullable = false)
    private EmployeeTeam registeredBy;

    // One patient can receive multiple blood units
    @OneToMany(mappedBy = "receivedBy", cascade = CascadeType.ALL)
    private List<Blood> receivedBlood;

    // ✅ Default Constructor
    public Patient() {
    }

    // ✅ Parameterized Constructor
    public Patient(String patientName, String patientGender, String patientAddress, String contactNo, String bloodGroup, Date dateOfIntake, EmployeeTeam registeredBy) {
        this.patientName = patientName;
        this.patientGender = patientGender;
        this.patientAddress = patientAddress;
        this.contactNo = contactNo;
        this.bloodGroup = bloodGroup;
        this.dateOfIntake = dateOfIntake;
        this.registeredBy = registeredBy;
    }

    // ✅ Getters and Setters
    public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }
    
    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }
    
    public String getPatientGender() { return patientGender; }
    public void setPatientGender(String patientGender) { this.patientGender = patientGender; }
    
    public String getPatientAddress() { return patientAddress; }
    public void setPatientAddress(String patientAddress) { this.patientAddress = patientAddress; }
    
    public String getContactNo() { return contactNo; }
    public void setContactNo(String contactNo) { this.contactNo = contactNo; }
    
    public String getBloodGroup() { return bloodGroup; }
    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }
    
    public Date getDateOfIntake() { return dateOfIntake; }
    public void setDateOfIntake(Date dateOfIntake) { this.dateOfIntake = dateOfIntake; }
    
    public EmployeeTeam getRegisteredBy() { return registeredBy; }
    public void setRegisteredBy(EmployeeTeam registeredBy) { this.registeredBy = registeredBy; }
    
    public List<Blood> getReceivedBlood() { return receivedBlood; }
    public void setReceivedBlood(List<Blood> receivedBlood) { this.receivedBlood = receivedBlood; }
    
    @Override
    public String toString() {
        return "Patient{" +
                "patientId=" + patientId +
                ", patientName='" + patientName + '\'' +
                ", patientGender='" + patientGender + '\'' +
                ", patientAddress='" + patientAddress + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", bloodGroup='" + bloodGroup + '\'' +
                ", dateOfIntake=" + dateOfIntake +
                ", registeredBy=" + registeredBy +
                '}';
    }
}
