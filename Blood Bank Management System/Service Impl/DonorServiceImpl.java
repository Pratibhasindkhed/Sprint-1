// Declares the package where this class resides
package com.bloodbank.service.impl;

import java.util.List; // Importing List interface for handling multiple donor objects

// Importing DAO and entity classes
import com.bloodbank.dao.DonorDAO;
import com.bloodbank.entity.Donor;
import com.bloodbank.service.DonorService;

// This class implements the DonorService interface and provides service layer logic for donor operations
public class DonorServiceImpl implements DonorService {

    // Reference to the DonorDao to perform database-related operations
    private final DonorDAO donorDao;

    // Constructor that injects the DonorDao dependency
    public DonorServiceImpl(DonorDAO donorDao) {
        this.donorDao = donorDao;
    }

    // Retrieves a donor by their unique ID by delegating to the DAO layer
    @Override
    public Donor getDonorById(String id) {
        return donorDao.getDonorById(id);
    }

    // Saves a new donor record to the database
    @Override
    public void saveDonor(Donor donor) {
        donorDao.saveDonor(donor);
    }

    // Updates an existing donor record in the database
    @Override
    public void updateDonor(Donor donor) {
        donorDao.updateDonor(donor);
    }

    // Deletes a donor record by their unique ID
    @Override
    public void deleteDonor(String id) {
        donorDao.deleteDonor(id);
    }

    // Retrieves a list of all donor records from the database
    @Override
    public List<Donor> getAllDonors() {
        return donorDao.getAllDonors();
    }
}
