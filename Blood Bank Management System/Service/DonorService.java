package com.bloodbank.service;  // Declares the package this interface belongs to

import java.util.List;  // Imports the List interface to handle multiple Donor objects

import com.bloodbank.entity.Donor;  // Imports the Donor entity class

// Service interface for Donor entity
public interface DonorService {

    // Retrieve a Donor by its ID
    // This method will return a Donor object if found by the provided ID
    Donor getDonorById(String id);

    // Save a new Donor entity
    // This method will be used to persist a Donor object into the database
    void saveDonor(Donor donor);

    // Update an existing Donor entity
    // This method will update an existing Donor record in the database with the provided Donor object
    void updateDonor(Donor donor);

    // Delete a Donor by its ID
    // This method will delete the Donor record from the database corresponding to the given ID
    void deleteDonor(String id);

    // Retrieve a list of all Donors
    // This method will return a list of all Donor objects available in the database
    List<Donor> getAllDonors();
}
