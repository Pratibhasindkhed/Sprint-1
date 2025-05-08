package com.bloodbank.service;

import java.util.List;  // Importing List to handle collections of Admin entities
import com.bloodbank.entity.Admin;  // Importing the Admin entity class

// Interface defining the service layer methods for CRUD operations on Admin entities
public interface AdminService {

    // Method to retrieve an Admin entity by its unique identifier (id)
    Admin getAdminById(String id);

    // Method to save a new Admin entity to the database
    void saveAdmin(Admin admin);

    // Method to update an existing Admin entity in the database
    void updateAdmin(Admin admin);

    // Method to delete an Admin entity from the database by its unique identifier (id)
    void deleteAdmin(String id);

    // Method to retrieve all Admin entities from the database
    List<Admin> getAllAdmins();
}
