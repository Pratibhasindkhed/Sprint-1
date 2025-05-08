package com.bloodbank.dao.impl;

import com.bloodbank.util.*;  // Importing Hibernate utility class for session management
import java.util.List;  // Importing List interface to handle collections of Admin objects
import org.hibernate.Session;  // Importing Hibernate Session class to manage database operations
import org.hibernate.Transaction;  // Importing Hibernate Transaction class for transaction management
import com.bloodbank.dao.AdminDAO;  // Importing the AdminDao interface
import com.bloodbank.entity.Admin;  // Importing the Admin entity class

// Implementation of AdminDao interface for performing CRUD operations on the Admin entity
public class AdminDAOImpl implements AdminDAO {

    // Method to retrieve an Admin record by its unique identifier (adminId)
    @Override
    public Admin getAdminById(String adminId) {
        // Opening a session using HibernateUtil
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            // Retrieving the Admin entity by its primary key (adminId)
            return session.get(Admin.class, adminId);
        } catch (Exception e) {
            // Printing the exception stack trace in case of an error
            e.printStackTrace();
            return null;
        } finally {
            // Closing the session after the operation
            session.close();
        }
    }

    // Method to save a new Admin record into the database
    @Override
    public void saveAdmin(Admin admin) {
        // Opening a session
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;  // Declaring a transaction object
        try {
            // Starting a new transaction
            transaction = session.beginTransaction();
            // Saving the Admin entity to the database
            session.save(admin);
            // Committing the transaction to save the changes
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

    // Method to update an existing Admin record in the database
    @Override
    public void updateAdmin(Admin admin) {
        // Opening a session
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            // Starting a new transaction
            transaction = session.beginTransaction();
            // Updating the Admin entity in the database
            session.update(admin);
            // Committing the transaction
            transaction.commit();
        } catch (Exception e) {
            // Rolling back the transaction if an error occurs
            if (transaction != null) {
                transaction.rollback();
            }
            // Printing the exception stack trace
            e.printStackTrace();
        } finally {
            // Closing the session
            session.close();
        }
    }

    // Method to delete an Admin record from the database by its unique identifier (adminId)
    @Override
    public void deleteAdmin(String adminId) {
        // Opening a session
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            // Starting a new transaction
            transaction = session.beginTransaction();
            // Retrieving the Admin entity by its primary key (adminId)
            Admin admin = session.get(Admin.class, adminId);
            // Checking if the Admin entity exists before attempting to delete
            if (admin != null) {
                // Deleting the Admin entity from the database
                session.delete(admin);
            }
            // Committing the transaction
            transaction.commit();
        } catch (Exception e) {
            // Rolling back the transaction if an error occurs
            if (transaction != null) {
                transaction.rollback();
            }
            // Printing the exception stack trace
            e.printStackTrace();
        } finally {
            // Closing the session
            session.close();
        }
    }

    // Method to retrieve all Admin records from the database
    @Override
    public List<Admin> getAllAdmins() {
        // Opening a session
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            // Creating and executing a query to retrieve all Admin entities
            return session.createQuery("FROM Admin", Admin.class).list();
        } catch (Exception e) {
            // Printing the exception stack trace if an error occurs
            e.printStackTrace();
            return null;
        } finally {
            // Closing the session
            session.close();
        }
    }
}
