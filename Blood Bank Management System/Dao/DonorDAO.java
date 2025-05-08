package com.bloodbank.dao;// Declares the package this interface belongs to

import com.bloodbank.entity.Donor;  // Importing the Donor entity class
import java.util.List;  // Importing the List interface to handle collections of Donor objects

// Interface for CRUD operations related to the Donor entity
public interface DonorDAO {

    // Method to retrieve a Donor record by its unique identifier (donorId)
    Donor getDonorById(String donorId);

    // Method to save a new Donor record into the database
    void saveDonor(Donor donor);

    // Method to update an existing Donor record
    void updateDonor(Donor donor);

    // Method to delete a Donor record from the database by its unique identifier (donorId)
    void deleteDonor(String donorId);

    // Method to retrieve all Donor records from the database
    List<Donor> getAllDonors();
}
