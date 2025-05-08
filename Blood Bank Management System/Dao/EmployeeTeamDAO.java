package com.bloodbank.dao;// Declares the package this interface belongs to

import com.bloodbank.entity.EmployeeTeam;  // Importing the EmployeeTeam entity class
import java.util.List;  // Importing the List interface to handle collections of EmployeeTeam objects

// Interface for CRUD operations related to the EmployeeTeam entity
public interface EmployeeTeamDAO {

    // Method to retrieve an EmployeeTeam record by its unique identifier (empId)
    EmployeeTeam getEmployeeTeamById(String empId);

    // Method to save a new EmployeeTeam record into the database
    void saveEmployeeTeam(EmployeeTeam employeeTeam);

    // Method to update an existing EmployeeTeam record
    void updateEmployeeTeam(EmployeeTeam employeeTeam);

    // Method to delete an EmployeeTeam record from the database by its unique identifier (empId)
    void deleteEmployeeTeam(String empId);

    // Method to retrieve all EmployeeTeam records from the database
    List<EmployeeTeam> getAllEmployeeTeams();
}
