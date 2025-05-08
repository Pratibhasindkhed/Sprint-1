package com.bloodbank.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "employee_team")
public class EmployeeTeam {

    @Id
    @Column(name = "emp_id",length=20)
    private String empId;

    @Column(name = "emp_name", nullable = false, length = 100)
    private String empName;

    // One EmployeeTeam registers many Donors
    @OneToMany(mappedBy = "registeredBy", cascade = CascadeType.ALL)
    private List<Donor> donors;

    // One EmployeeTeam registers many Patients
    @OneToMany(mappedBy = "registeredBy", cascade = CascadeType.ALL)
    private List<Patient> patients;

    // ✅ Default Constructor
    public EmployeeTeam() {
    }

    // ✅ Parameterized Constructor
    public EmployeeTeam(String empName) {
        this.empName = empName;
    }

    // ✅ Getters and Setters
    public String getEmpId() { return empId; }
    public void setEmpId(String empId) { this.empId = empId; }

    public String getEmpName() { return empName; }
    public void setEmpName(String empName) { this.empName = empName; }

    public List<Donor> getDonors() { return donors; }
    public void setDonors(List<Donor> donors) { this.donors = donors; }

    public List<Patient> getPatients() { return patients; }
    public void setPatients(List<Patient> patients) { this.patients = patients; }
    
    @Override
    public String toString() {
        return "EmployeeTeam{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                '}';
    }
}

