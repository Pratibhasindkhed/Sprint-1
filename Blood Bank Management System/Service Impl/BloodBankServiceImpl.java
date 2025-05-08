package com.bloodbank.service.impl;  // Declares the package for the service implementation

import java.util.List;  // Imports the List interface for handling multiple BloodBank objects

import com.bloodbank.dao.BloodBankDAO;  // Imports the BloodBankDao interface for data access
import com.bloodbank.entity.BloodBank;  // Imports the BloodBank entity class
import com.bloodbank.service.BloodBankService;  // Imports the BloodBankService interface to implement

// Implementation of the BloodBankService interface
public class BloodBankServiceImpl implements BloodBankService {

    private final BloodBankDAO bloodBankDao;  // Declares a dependency on the BloodBankDao for data operations

    // Constructor injection of BloodBankDao dependency
    public BloodBankServiceImpl(BloodBankDAO bloodBankDao) {
        this.bloodBankDao = bloodBankDao;  // Assigns the injected BloodBankDao instance to the class field
    }

    // Retrieve a BloodBank by its ID
    @Override
    public BloodBank getBloodBankById(String id) {
        return bloodBankDao.getBloodBankById(id);  // Delegates the call to the DAO to fetch BloodBank by ID
    }

    // Save a new BloodBank entity
    @Override
    public void saveBloodBank(BloodBank bloodBank) {
        bloodBankDao.saveBloodBank(bloodBank);  // Delegates the call to the DAO to save the BloodBank object
    }

    // Update an existing BloodBank entity
    @Override
    public void updateBloodBank(BloodBank bloodBank) {
        bloodBankDao.updateBloodBank(bloodBank);  // Delegates the call to the DAO to update the BloodBank object
    }

    // Delete a BloodBank by its ID
    @Override
    public void deleteBloodBank(String id) {
        bloodBankDao.deleteBloodBank(id);  // Delegates the call to the DAO to delete the BloodBank by ID
    }

    // Retrieve a list of all BloodBanks
    @Override
    public List<BloodBank> getAllBloodBanks() {
        return bloodBankDao.getAllBloodBanks();  // Delegates the call to the DAO to fetch all BloodBank records
    }

    // Increase or decrease blood quantity in a BloodBank
    @Override
    public void updateBloodQuantity(String bloodBankId, int quantityChange) {
        bloodBankDao.updateBloodQuantity(bloodBankId, quantityChange);  // Delegates the call to the DAO to update the blood quantity
    }
}
