package com.bloodbank.service;

import java.util.List;  // Importing List to handle collections of Blood entities
import com.bloodbank.entity.Blood;  // Importing the Blood entity class

// Interface defining the service layer methods for CRUD operations on Blood entities
public interface BloodService {

    // Method to retrieve a Blood entity by its unique identifier (id)
    Blood getBloodById(String id);

    // Method to save a new Blood entity to the database
    void saveBlood(Blood blood);

    // Method to update an existing Blood entity in the database
    void updateBlood(Blood blood);

    // Method to delete a Blood entity from the database by its unique identifier (id)
    void deleteBlood(String id);

    // Method to retrieve a list of Blood entities stored in a specific BloodBank
    List<Blood> getBloodByBloodBankId(String bloodBankId);

    // Method to retrieve all Blood entities from the database
    List<Blood> getAllBlood();
}
