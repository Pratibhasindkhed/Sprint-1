// Declares the package where this service implementation class resides
package com.bloodbank.service.impl;

import java.util.List; // Importing List to handle collections of Hospital objects

// Importing required DAO, entity, and service interfaces
import com.bloodbank.dao.HospitalDAO;
import com.bloodbank.entity.Hospital;
import com.bloodbank.service.HospitalService;

// Service implementation class that handles business logic for Hospital entities
public class HospitalServiceImpl implements HospitalService {

    // Dependency on HospitalDao for data access operations
    private final HospitalDAO hospitalDao;

    // Constructor for injecting the HospitalDao dependency
    public HospitalServiceImpl(HospitalDAO hospitalDao) {
        this.hospitalDao = hospitalDao;
    }

    // Retrieves a Hospital record from the database using its unique ID
    @Override
    public Hospital getHospitalById(String id) {
        return hospitalDao.getHospitalById(id);
    }

    // Saves a new Hospital record into the database
    @Override
    public void saveHospital(Hospital hospital) {
        hospitalDao.saveHospital(hospital);
    }

    // Updates an existing Hospital record in the database
    @Override
    public void updateHospital(Hospital hospital) {
        hospitalDao.updateHospital(hospital);
    }

    // Deletes a Hospital record from the database using its ID
    @Override
    public void deleteHospital(String id) {
        hospitalDao.deleteHospital(id);
    }

    // Retrieves a list of all Hospital records from the database
    @Override
    public List<Hospital> getAllHospitals() {
        return hospitalDao.getAllHospitals();
    }
}
