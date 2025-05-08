package com.bloodbank.dao.impl;

import java.util.List;  // Importing List to handle collections of EmployeeTeam entities
import org.hibernate.Session;  // Importing Session to interact with the Hibernate session
import org.hibernate.Transaction;  // Importing Transaction for managing transactions in Hibernate
import com.bloodbank.dao.EmployeeTeamDAO;  // Importing the EmployeeTeamDao interface
import com.bloodbank.entity.EmployeeTeam;  // Importing the EmployeeTeam entity class
import com.bloodbank.util.HibernateUtil;  // Importing HibernateUtil to get the session factory

// Implementation of EmployeeTeamDao interface for CRUD operations on EmployeeTeam entities
public class EmployeeTeamDAOImpl implements EmployeeTeamDAO {

    // Method to retrieve an EmployeeTeam record by its unique identifier (empId)
    @Override
    public EmployeeTeam getEmployeeTeamById(String empId) {
        // Opening a Hibernate session
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            // Retrieving the EmployeeTeam entity by its primary key (empId)
            return session.get(EmployeeTeam.class, empId);
        } catch (Exception e) {
            // Printing the exception stack trace if any error occurs
            e.printStackTrace();
            return null;
        } finally {
            // Closing the session after the operation
            session.close();
        }
    }

    // Method to save a new EmployeeTeam record in the database
    @Override
    public void saveEmployeeTeam(EmployeeTeam employeeTeam) {
        // Opening a Hibernate session
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;  // Declaring a transaction object
        try {
            // Starting a new transaction
            transaction = session.beginTransaction();
            // Saving the EmployeeTeam entity to the database
            session.save(employeeTeam);
            // Committing the transaction to persist the entity
            transaction.commit();
        } catch (Exception e) {
            // Rolling back the transaction in case of an error
            if (transaction != null) {
                transaction.rollback();
            }
            // Printing the exception stack trace
            e.printStackTrace();
        } finally {
            // Closing the session after the operation
            session.close();
        }
    }

    // Method to update an existing EmployeeTeam record in the database
    @Override
    public void updateEmployeeTeam(EmployeeTeam employeeTeam) {
        // Opening a Hibernate session
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;  // Declaring a transaction object
        try {
            // Starting a new transaction
            transaction = session.beginTransaction();
            // Updating the EmployeeTeam entity in the database
            session.update(employeeTeam);
            // Committing the transaction to apply the changes
            transaction.commit();
        } catch (Exception e) {
            // Rolling back the transaction in case of an error
            if (transaction != null) {
                transaction.rollback();
            }
            // Printing the exception stack trace
            e.printStackTrace();
        } finally {
            // Closing the session after the operation
            session.close();
        }
    }

    // Method to delete an EmployeeTeam record from the database by its unique identifier (empId)
    @Override
    public void deleteEmployeeTeam(String empId) {
        // Opening a Hibernate session
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;  // Declaring a transaction object
        try {
            // Starting a new transaction
            transaction = session.beginTransaction();
            // Retrieving the EmployeeTeam entity by its primary key (empId)
            EmployeeTeam employeeTeam = session.get(EmployeeTeam.class, empId);
            // If the EmployeeTeam entity exists, delete it
            if (employeeTeam != null) {
                session.delete(employeeTeam);
            }
            // Committing the transaction to apply the deletion
            transaction.commit();
        } catch (Exception e) {
            // Rolling back the transaction in case of an error
            if (transaction != null) {
                transaction.rollback();
            }
            // Printing the exception stack trace
            e.printStackTrace();
        } finally {
            // Closing the session after the operation
            session.close();
        }
    }

    // Method to retrieve all EmployeeTeam records from the database
    @Override
    public List<EmployeeTeam> getAllEmployeeTeams() {
        // Opening a Hibernate session
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            // Creating and executing a query to retrieve all EmployeeTeam entities
            return session.createQuery("FROM EmployeeTeam", EmployeeTeam.class).list();
        } catch (Exception e) {
            // Printing the exception stack trace if any error occurs
            e.printStackTrace();
            return null;
        } finally {
            // Closing the session after the operation
            session.close();
        }
    }
}
