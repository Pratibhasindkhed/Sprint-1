package com.bloodbank.service;  // Declares the package this interface belongs to

import java.util.List;  // Imports the List interface for handling multiple Hospital objects

import com.bloodbank.entity.Hospital;  // Imports the Hospital entity class

// Service interface for Hospital entity
public interface HospitalService {

    // Retrieve a Hospital by its ID
    // This method will return a Hospital object if found by the provided ID
    Hospital getHospitalById(String id);

    // Save a new Hospital entity
    // This method will be used to persist a new Hospital object in the database
    void saveHospital(Hospital hospital);

    // Update an existing Hospital entity
    // This method will update the Hospital record in the database with the provided Hospital object
    void updateHospital(Hospital hospital);

    // Delete a Hospital by its ID
    // This method will delete the Hospital record from the database that corresponds to the given ID
    void deleteHospital(String id);

    // Retrieve a list of all Hospitals
    // This method will return a list of all Hospital objects available in the database
    List<Hospital> getAllHospitals();
}
