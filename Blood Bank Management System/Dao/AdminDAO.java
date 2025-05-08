package com.bloodbank.dao;// Declares the package this interface belongs to


import com.bloodbank.entity.Admin;// Imports the Admin entity class

import java.util.List;// Imports the List interface for handling multiple Admin objects

// DAO interface for Admin entity
public interface AdminDAO {

    // Retrieve an Admin by its ID
    Admin getAdminById(String adminId);

    // Save a new Admin entity
    void saveAdmin(Admin admin);

    // Update an existing Admin entity
    void updateAdmin(Admin admin);

    // Delete an Admin by its ID
    void deleteAdmin(String adminId);

    // Retrieve a list of all Admins
    List<Admin> getAllAdmins();
}
