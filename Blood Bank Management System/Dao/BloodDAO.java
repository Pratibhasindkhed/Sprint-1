package com.bloodbank.dao;// Declares the package this interface belongs to

import com.bloodbank.entity.Blood;  // Importing the Blood entity class
import java.util.List;  // Importing the List interface to handle collections of Blood objects

// Interface for CRUD operations related to the Blood entity
public interface BloodDAO {

    // Method to retrieve a Blood record by its unique identifier (bloodId)
    Blood getBloodById(String bloodId);

    // Method to save a new Blood record into the database
    void saveBlood(Blood blood);

    // Method to update an existing Blood record
    void updateBlood(Blood blood);

    // Method to delete a Blood record from the database by its unique identifier (bloodId)
    void deleteBlood(String bloodId);

    // Method to retrieve a list of Blood records associated with a specific Blood Bank by its unique identifier (bloodBankId)
    List<Blood> getBloodByBloodBankId(String bloodBankId);

    // Method to retrieve all Blood records from the database
    List<Blood> getAllBlood();
}

