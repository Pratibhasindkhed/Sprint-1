// Declares the package where this class is located
package com.bloodbank.service.impl;

import java.util.List; // Importing List to handle multiple Patient records

// Importing necessary DAO, entity, and service interface
import com.bloodbank.dao.PatientDAO;
import com.bloodbank.entity.Patient;
import com.bloodbank.service.PatientService;

// Service implementation class that provides business logic for Patient-related operations
public class PatientServiceImpl implements PatientService {

    // DAO reference to handle database interactions for the Patient entity
    private final PatientDAO patientDao;

    // Constructor for injecting the PatientDao dependency
    public PatientServiceImpl(PatientDAO patientDao) {
        this.patientDao = patientDao;
    }

    // Fetches a Patient record based on the given patient ID
    @Override
    public Patient getPatientById(String id) {
        return patientDao.getPatientById(id);
    }

    // Saves a new Patient record to the database
    @Override
    public void savePatient(Patient patient) {
        patientDao.savePatient(patient);
    }

    // Updates an existing Patient record in the database
    @Override
    public void updatePatient(Patient patient) {
        patientDao.updatePatient(patient);
    }

    // Deletes a Patient record from the database using its ID
    @Override
    public void deletePatient(String id) {
        patientDao.deletePatient(id);
    }

    // Retrieves all Patient records from the database
    @Override
    public List<Patient> getAllPatients() {
        return patientDao.getAllPatients();
    }
}
