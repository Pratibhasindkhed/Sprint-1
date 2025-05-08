package com.bloodbank.dao;// Declares the package this interface belongs to

import com.bloodbank.entity.Patient;  // Importing the Patient entity class
import java.util.List;  // Importing the List interface to handle collections of Patient objects

// Interface for CRUD operations related to the Patient entity
public interface PatientDAO {

    // Method to retrieve a Patient record by its unique identifier (patientId)
    Patient getPatientById(String patientId);

    // Method to save a new Patient record into the database
    void savePatient(Patient patient);

    // Method to update an existing Patient record
    void updatePatient(Patient patient);

    // Method to delete a Patient record from the database by its unique identifier (patientId)
    void deletePatient(String patientId);

    // Method to retrieve all Patient records from the database
    List<Patient> getAllPatients();
}
