// Package declaration to specify the location of this class in the project structure
package com.bloodbank.service.impl;

import java.util.List; // Importing List for handling collections of Blood objects

// Importing required classes and interfaces
import com.bloodbank.dao.BloodDAO;
import com.bloodbank.entity.Blood;
import com.bloodbank.service.BloodService;

// Implementation class for the BloodService interface
public class BloodServiceImpl implements BloodService {

    // Dependency on the DAO layer to perform database operations
    private final BloodDAO bloodDao;

    // Constructor to initialize BloodDao via dependency injection
    public BloodServiceImpl(BloodDAO bloodDao) {
        this.bloodDao = bloodDao;
    }

    // Method to retrieve a Blood record based on its ID
    @Override
    public Blood getBloodById(String id) {
        return bloodDao.getBloodById(id);
    }

    // Method to save a new Blood record to the database
    @Override
    public void saveBlood(Blood blood) {
        bloodDao.saveBlood(blood);
    }

    // Method to update an existing Blood record in the database
    @Override
    public void updateBlood(Blood blood) {
        bloodDao.updateBlood(blood);
    }

    // Method to delete a Blood record based on its ID
    @Override
    public void deleteBlood(String id) {
        bloodDao.deleteBlood(id);
    }

    // Method to retrieve all Blood records associated with a specific Blood Bank ID
    @Override
    public List<Blood> getBloodByBloodBankId(String bloodBankId) {
        return bloodDao.getBloodByBloodBankId(bloodBankId);
    }

    // Method to retrieve all Blood records from the database
    @Override
    public List<Blood> getAllBlood() {
        return bloodDao.getAllBlood();
    }
}
