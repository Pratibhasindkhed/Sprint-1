package com.bloodbank.dao.impl;

import java.util.List;  // Importing List for handling collections of Blood entities
import org.hibernate.Session;  // Importing Session for interacting with the Hibernate session
import org.hibernate.Transaction;  // Importing Transaction for managing transactions in Hibernate
import org.hibernate.query.Query;// Importing Query for creating HQL queries in Hibernate

import com.bloodbank.dao.BloodDAO;  // Importing the BloodDao interface
import com.bloodbank.entity.Blood;  // Importing the Blood entity class
import com.bloodbank.util.HibernateUtil;  // Importing HibernateUtil for getting the session factory

// Implementation of the BloodDao interface to perform CRUD operations on the Blood entity
public class BloodDAOImpl implements BloodDAO {

    // Method to retrieve a Blood record by its unique identifier (bloodId)
    @Override
    public Blood getBloodById(String bloodId) {
        // Opening a Hibernate session
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            // Retrieving the Blood entity by its primary key (bloodId)
            return session.get(Blood.class, bloodId);
        } catch (Exception e) {
            // Printing the exception stack trace if any error occurs
            e.printStackTrace();
            return null;
        } finally {
            // Closing the session after the operation
            session.close();
        }
    }

    // Method to save a new Blood record into the database
    @Override
    public void saveBlood(Blood blood) {
        // Opening a Hibernate session
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;  // Declaring a transaction object
        try {
            // Starting a new transaction
            transaction = session.beginTransaction();
            // Saving the Blood entity to the database
            session.save(blood);
            // Committing the transaction to persist the entity
            transaction.commit();
        } catch (Exception e) {
            // Rolling back the transaction if any error occurs
            if (transaction != null) {
                transaction.rollback();
            }
            // Printing the exception stack trace in case of an error
            e.printStackTrace();
        } finally {
            // Closing the session after the operation
            session.close();
        }
    }

    // Method to update an existing Blood record in the database
    @Override
    public void updateBlood(Blood blood) {
        // Opening a Hibernate session
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;  // Declaring a transaction object
        try {
            // Starting a new transaction
            transaction = session.beginTransaction();
            // Updating the Blood entity in the database
            session.update(blood);
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
            // Closing the session
            session.close();
        }
    }

    // Method to delete a Blood record from the database by its unique identifier (bloodId)
    @Override
    public void deleteBlood(String bloodId) {
        // Opening a Hibernate session
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;  // Declaring a transaction object
        try {
            // Starting a new transaction
            transaction = session.beginTransaction();
            // Retrieving the Blood entity by its primary key (bloodId)
            Blood blood = session.get(Blood.class, bloodId);
            // If the Blood entity exists, delete it
            if (blood != null) {
                session.delete(blood);
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
            // Closing the session
            session.close();
        }
    }

    // Method to retrieve a list of Blood records by the associated BloodBank's ID (bloodBankId)
    @Override
    public List<Blood> getBloodByBloodBankId(String bloodBankId) {
        // Opening a Hibernate session
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            // Creating a query to fetch all Blood records where the storedIn bloodBankId matches the given bloodBankId
            Query<Blood> query = session.createQuery("FROM Blood WHERE storedIn.bloodBankId = :bloodBankId", Blood.class);
            // Setting the parameter for the query to filter by bloodBankId
            query.setParameter("bloodBankId", bloodBankId);
            // Returning the list of Blood records
            return query.list();
        } catch (Exception e) {
            // Printing the exception stack trace if any error occurs
            e.printStackTrace();
            return null;
        } finally {
            // Closing the session after the operation
            session.close();
        }
    }

    // Method to retrieve all Blood records from the database
    @Override
    public List<Blood> getAllBlood() {
        // Opening a Hibernate session
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            // Creating and executing a query to retrieve all Blood entities
            return session.createQuery("FROM Blood", Blood.class).list();
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
