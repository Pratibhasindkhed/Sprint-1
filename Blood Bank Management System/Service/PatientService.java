package com.bloodbank.service;  // Declares the package this interface belongs to

import java.util.List;  // Imports the List interface for handling multiple Patient objects

import com.bloodbank.entity.Patient;  // Imports the Patient entity class

// Service interface for Patient entity
public interface PatientService {

    // Retrieve a Patient by its ID
    // This method will return a Patient object if found by the provided ID
    Patient getPatientById(String id);

    // Save a new Patient entity
    // This method will be used to persist a new Patient object in the database
    void savePatient(Patient patient);

    // Update an existing Patient entity
    // This method will update the Patient record in the database with the provided Patient object
    void updatePatient(Patient patient);

    // Delete a Patient by its ID
    // This method will delete the Patient record from the database that corresponds to the given ID
    void deletePatient(String id);

    // Retrieve a list of all Patients
    // This method will return a list of all Patient objects available in the database
    List<Patient> getAllPatients();
}
