package com.bloodbank.dao.impl;

import java.util.List;  // Importing List to handle collections of Donor entities
import org.hibernate.Session;  // Importing Session for interacting with the Hibernate session
import org.hibernate.Transaction;  // Importing Transaction for managing transactions in Hibernate
import com.bloodbank.dao.DonorDAO;  // Importing the DonorDao interface
import com.bloodbank.entity.Donor;  // Importing the Donor entity class
import com.bloodbank.util.HibernateUtil;  // Importing HibernateUtil to get the session factory

// Implementation of DonorDao interface for CRUD operations on Donor entities
public class DonorDAOImpl implements DonorDAO {

    // Method to retrieve a Donor record by its unique identifier (donorId)
    @Override
    public Donor getDonorById(String donorId) {
        // Opening a Hibernate session
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            // Retrieving the Donor entity by its primary key (donorId)
            return session.get(Donor.class, donorId);
        } catch (Exception e) {
            // Printing the exception stack trace if any error occurs
            e.printStackTrace();
            return null;
        } finally {
            // Closing the session after the operation
            session.close();
        }
    }

    // Method to save a new Donor record in the database
    @Override
    public void saveDonor(Donor donor) {
        // Opening a Hibernate session
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;  // Declaring a transaction object
        try {
            // Starting a new transaction
            transaction = session.beginTransaction();
            // Saving the Donor entity to the database
            session.save(donor);
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

    // Method to update an existing Donor record in the database
    @Override
    public void updateDonor(Donor donor) {
        // Opening a Hibernate session
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;  // Declaring a transaction object
        try {
            // Starting a new transaction
            transaction = session.beginTransaction();
            // Updating the Donor entity in the database
            session.update(donor);
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

    // Method to delete a Donor record from the database by its unique identifier (donorId)
    @Override
    public void deleteDonor(String donorId) {
        // Opening a Hibernate session
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;  // Declaring a transaction object
        try {
            // Starting a new transaction
            transaction = session.beginTransaction();
            // Retrieving the Donor entity by its primary key (donorId)
            Donor donor = session.get(Donor.class, donorId);
            // If the Donor entity exists, delete it
            if (donor != null) {
                session.delete(donor);
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

    // Method to retrieve all Donor records from the database
    @Override
    public List<Donor> getAllDonors() {
        // Opening a Hibernate session
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            // Creating and executing a query to retrieve all Donor entities
            return session.createQuery("FROM Donor", Donor.class).list();
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
