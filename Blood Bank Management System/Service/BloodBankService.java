package com.bloodbank.service;

import java.util.List;  // Importing List to handle collections of BloodBank entities
import com.bloodbank.entity.BloodBank;  // Importing the BloodBank entity class

// Interface defining the service layer methods for CRUD operations on BloodBank entities
public interface BloodBankService {

    // Method to retrieve a BloodBank entity by its unique identifier (id)
    BloodBank getBloodBankById(String id);

    // Method to save a new BloodBank entity to the database
    void saveBloodBank(BloodBank bloodBank);

    // Method to update an existing BloodBank entity in the database
    void updateBloodBank(BloodBank bloodBank);

    // Method to delete a BloodBank entity from the database by its unique identifier (id)
    void deleteBloodBank(String id);

    // Method to retrieve all BloodBank entities from the database
    List<BloodBank> getAllBloodBanks();
    
 // Increase or decrease blood quantity in a BloodBank
    void updateBloodQuantity(String bloodBankId, int quantityChange);
}
