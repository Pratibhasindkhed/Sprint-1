package com.bloodbank.dao;// Declares the package this interface belongs to

import com.bloodbank.entity.BloodBank;// Imports the BloodBank entity class

import java.util.List;// Imports the List interface for handling multiple BloodBank objects

// DAO interface for BloodBank entity
public interface BloodBankDAO {

    // Retrieve a BloodBank by its ID
    BloodBank getBloodBankById(String bloodBankId);

    // Save a new BloodBank entity
    void saveBloodBank(BloodBank bloodBank);

    // Update an existing BloodBank entity
    void updateBloodBank(BloodBank bloodBank);

    // Delete a BloodBank by its ID
    void deleteBloodBank(String bloodBankId);

    // Retrieve a list of all BloodBanks
    List<BloodBank> getAllBloodBanks();

    // Increase or decrease blood quantity in a BloodBank
    void updateBloodQuantity(String bloodBankId, int quantityChange);
}
