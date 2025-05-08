package com.bloodbank.dao;// Declares the package this interface belongs to

import com.bloodbank.entity.Hospital;  // Importing the Hospital entity class
import java.util.List;  // Importing the List interface to handle collections of Hospital objects

// Interface for CRUD operations related to the Hospital entity
public interface HospitalDAO {

    // Method to retrieve a Hospital record by its unique identifier (hospitalId)
    Hospital getHospitalById(String hospitalId);

    // Method to save a new Hospital record into the database
    void saveHospital(Hospital hospital);

    // Method to update an existing Hospital record
    void updateHospital(Hospital hospital);

    // Method to delete a Hospital record from the database by its unique identifier (hospitalId)
    void deleteHospital(String hospitalId);

    // Method to retrieve all Hospital records from the database
    List<Hospital> getAllHospitals();
}
