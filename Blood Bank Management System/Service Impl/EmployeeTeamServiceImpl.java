// Declares the package this class belongs to
package com.bloodbank.service.impl;

import java.util.List; // Importing List interface to work with collections of EmployeeTeam objects

// Importing DAO and entity classes required for service logic
import com.bloodbank.dao.EmployeeTeamDAO;
import com.bloodbank.entity.EmployeeTeam;
import com.bloodbank.service.EmployeeTeamService;

// Service implementation class for managing EmployeeTeam entities
public class EmployeeTeamServiceImpl implements EmployeeTeamService {

    // DAO instance to interact with the database layer
    private final EmployeeTeamDAO employeeTeamDao;

    // Constructor to initialize the DAO instance via dependency injection
    public EmployeeTeamServiceImpl(EmployeeTeamDAO employeeTeamDao) {
        this.employeeTeamDao = employeeTeamDao;
    }

    // Retrieves an EmployeeTeam record by its unique ID
    @Override
    public EmployeeTeam getEmployeeTeamById(String id) {
        return employeeTeamDao.getEmployeeTeamById(id);
    }

    // Saves a new EmployeeTeam record into the database
    @Override
    public void saveEmployeeTeam(EmployeeTeam employeeTeam) {
        employeeTeamDao.saveEmployeeTeam(employeeTeam);
    }

    // Updates an existing EmployeeTeam record in the database
    @Override
    public void updateEmployeeTeam(EmployeeTeam employeeTeam) {
        employeeTeamDao.updateEmployeeTeam(employeeTeam);
    }

    // Deletes an EmployeeTeam record from the database using its ID
    @Override
    public void deleteEmployeeTeam(String id) {
        employeeTeamDao.deleteEmployeeTeam(id);
    }

    // Retrieves all EmployeeTeam records from the database
    @Override
    public List<EmployeeTeam> getAllEmployeeTeams() {
        return employeeTeamDao.getAllEmployeeTeams();
    }
}
