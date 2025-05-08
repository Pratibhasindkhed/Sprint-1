package com.bloodbank.service.impl;  // Declares the package for the service implementation

import java.util.List;  // Imports the List interface for handling multiple Admin objects

import com.bloodbank.dao.AdminDAO;  // Imports the AdminDao interface for data access
import com.bloodbank.entity.Admin;  // Imports the Admin entity class
import com.bloodbank.service.AdminService;  // Imports the AdminService interface to implement

// Implementation of AdminService interface
public class AdminServiceImpl implements AdminService {

    private final AdminDAO adminDao;  // Declares a dependency on AdminDao for data operations

    // Constructor injection of AdminDao dependency
    public AdminServiceImpl(AdminDAO adminDao) {
        this.adminDao = adminDao;  // Assigns the injected AdminDao instance to the class field
    }

    // Retrieve an Admin by its ID
    @Override
    public Admin getAdminById(String id) {
        return adminDao.getAdminById(id);  // Calls the DAO layer to fetch the Admin by ID
    }

    // Save a new Admin entity
    @Override
    public void saveAdmin(Admin admin) {
        adminDao.saveAdmin(admin);  // Calls the DAO layer to save the Admin object
    }

    // Update an existing Admin entity
    @Override
    public void updateAdmin(Admin admin) {
        adminDao.updateAdmin(admin);  // Calls the DAO layer to update the Admin record
    }

    // Delete an Admin by its ID
    @Override
    public void deleteAdmin(String id) {
        adminDao.deleteAdmin(id);  // Calls the DAO layer to delete the Admin by ID
    }

    // Retrieve a list of all Admins
    @Override
    public List<Admin> getAllAdmins() {
        return adminDao.getAllAdmins();  // Calls the DAO layer to fetch all Admin records
    }
}
