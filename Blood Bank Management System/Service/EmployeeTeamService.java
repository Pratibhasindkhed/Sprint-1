package com.bloodbank.service;  // Declares the package this interface belongs to

import java.util.List;  // Imports the List interface to handle multiple EmployeeTeam objects

import com.bloodbank.entity.EmployeeTeam;  // Imports the EmployeeTeam entity class

// Service interface for EmployeeTeam entity
public interface EmployeeTeamService {

    // Retrieve an EmployeeTeam by its ID
    // This method will return an EmployeeTeam object if found by the provided ID
    EmployeeTeam getEmployeeTeamById(String id);

    // Save a new EmployeeTeam entity
    // This method will be used to persist an EmployeeTeam object into the database
    void saveEmployeeTeam(EmployeeTeam employeeTeam);

    // Update an existing EmployeeTeam entity
    // This method will update an existing EmployeeTeam record in the database with the provided EmployeeTeam object
    void updateEmployeeTeam(EmployeeTeam employeeTeam);

    // Delete an EmployeeTeam by its ID
    // This method will delete the EmployeeTeam record from the database corresponding to the given ID
    void deleteEmployeeTeam(String id);

    // Retrieve a list of all EmployeeTeams
    // This method will return a list of all EmployeeTeam objects available in the database
    List<EmployeeTeam> getAllEmployeeTeams();
}
