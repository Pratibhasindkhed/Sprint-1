package com.bloodbank.dao.impl;

import java.util.List;  // Importing List to handle collections of BloodBank objects
import org.hibernate.Session;  // Importing Hibernate Session class for managing database sessions
import org.hibernate.Transaction;  // Importing Hibernate Transaction class for transaction management
import com.bloodbank.dao.BloodBankDAO;  // Importing the BloodBankDao interface
import com.bloodbank.entity.BloodBank;  // Importing the BloodBank entity class
import com.bloodbank.util.HibernateUtil;  // Importing Hibernate utility class for session management

// Implementation of BloodBankDao interface for performing CRUD operations on the BloodBank entity
public class BloodBankDAOImpl implements BloodBankDAO {

    // Method to retrieve a BloodBank record by its unique identifier (bloodBankId)
    @Override
    public BloodBank getBloodBankById(String bloodBankId) {
        // Opening a session using HibernateUtil
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            // Retrieving the BloodBank entity by its primary key (bloodBankId)
            return session.get(BloodBank.class, bloodBankId);
        } catch (Exception e) {
            // Printing the exception stack trace in case of an error
            e.printStackTrace();
            return null;
        } finally {
            // Closing the session after the operation
            session.close();
        }
    }

    // Method to save a new BloodBank record into the database
    @Override
    public void saveBloodBank(BloodBank bloodBank) {
        // Opening a session
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;  // Declaring a transaction object
        try {
            // Starting a new transaction
            transaction = session.beginTransaction();
            // Saving the BloodBank entity to the database
            session.save(bloodBank);
            // Committing the transaction to persist the data
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

    // Method to update an existing BloodBank record in the database
    @Override
    public void updateBloodBank(BloodBank bloodBank) {
        // Opening a session
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            // Starting a new transaction
            transaction = session.beginTransaction();
            // Updating the BloodBank entity in the database
            session.update(bloodBank);
            // Committing the transaction to apply changes
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

    // Method to delete a BloodBank record from the database by its unique identifier (bloodBankId)
    @Override
    public void deleteBloodBank(String bloodBankId) {
        // Opening a session
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            // Starting a new transaction
            transaction = session.beginTransaction();
            // Retrieving the BloodBank entity by its primary key (bloodBankId)
            BloodBank bloodBank = session.get(BloodBank.class, bloodBankId);
            // If the BloodBank exists, delete it
            if (bloodBank != null) {
                session.delete(bloodBank);
            }
            // Committing the transaction to apply the deletion
            transaction.commit();
        } catch (Exception e) {
            // Rolling back the transaction if an error occurs
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

    // Method to retrieve all BloodBank records from the database
    @Override
    public List<BloodBank> getAllBloodBanks() {
        // Opening a session
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            // Creating and executing a query to retrieve all BloodBank entities
            return session.createQuery("FROM BloodBank", BloodBank.class).list();
        } catch (Exception e) {
            // Printing the exception stack trace in case of an error
            e.printStackTrace();
            return null;
        } finally {
            // Closing the session after the operation
            session.close();
        }
    }

    // Method to update the blood quantity for a specific BloodBank
    @Override
    public void updateBloodQuantity(String bloodBankId, int quantityChange) {
        // Start a transaction
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            // Get the BloodBank entity by its id
            BloodBank bloodBank = session.get(BloodBank.class, bloodBankId);
            if (bloodBank != null) {
                int currentQty = bloodBank.getQuantity();  // Get the current quantity of blood
                int updatedQty = currentQty + quantityChange;  // Calculate the new quantity

                // Check if the updated quantity is negative
                if (updatedQty < 0) {
                    transaction.rollback();  // Rollback the transaction if the quantity is negative
                    throw new IllegalArgumentException("Not enough blood units available.");
                }

                // Set the updated quantity and save the changes
                bloodBank.setQuantity(updatedQty);
                session.merge(bloodBank);
            }
            transaction.commit();  // Commit the transaction to apply the changes
        } catch (Exception e) {
            // Rolling back the transaction if an error occurs
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();  // Printing the exception stack trace
        } finally {
            // Closing the session
            session.close();
        }
    }
}
