package com.bloodbank.dao.impl;

import java.util.List;  // Importing List to handle collections of Hospital entities
import org.hibernate.Session;  // Importing Session to interact with the Hibernate session
import org.hibernate.Transaction;  // Importing Transaction for managing transactions in Hibernate
import com.bloodbank.dao.HospitalDAO;  // Importing the HospitalDao interface
import com.bloodbank.entity.Hospital;  // Importing the Hospital entity class
import com.bloodbank.util.HibernateUtil;  // Importing HibernateUtil to get the session factory

// Implementation of HospitalDao interface for CRUD operations on Hospital entities
public class HospitalDAOImpl implements HospitalDAO {

    // Method to retrieve a Hospital record by its unique identifier (hospitalId)
    @Override
    public Hospital getHospitalById(String hospitalId) {
        // Opening a Hibernate session
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            // Retrieving the Hospital entity by its primary key (hospitalId)
            return session.get(Hospital.class, hospitalId);
        } catch (Exception e) {
            // Printing the exception stack trace if any error occurs
            e.printStackTrace();
            return null;
        } finally {
            // Closing the session after the operation
            session.close();
        }
    }

    // Method to save a new Hospital record in the database
    @Override
    public void saveHospital(Hospital hospital) {
        // Opening a Hibernate session
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;  // Declaring a transaction object
        try {
            // Starting a new transaction
            transaction = session.beginTransaction();
            // Saving the Hospital entity to the database
            session.save(hospital);
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

    // Method to update an existing Hospital record in the database
    @Override
    public void updateHospital(Hospital hospital) {
        // Opening a Hibernate session
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;  // Declaring a transaction object
        try {
            // Starting a new transaction
            transaction = session.beginTransaction();
            // Updating the Hospital entity in the database
            session.update(hospital);
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

    // Method to delete a Hospital record from the database by its unique identifier (hospitalId)
    @Override
    public void deleteHospital(String hospitalId) {
        // Opening a Hibernate session
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;  // Declaring a transaction object
        try {
            // Starting a new transaction
            transaction = session.beginTransaction();
            // Retrieving the Hospital entity by its primary key (hospitalId)
            Hospital hospital = session.get(Hospital.class, hospitalId);
            // If the Hospital entity exists, delete it
            if (hospital != null) {
                session.delete(hospital);
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

    // Method to retrieve all Hospital records from the database
    @Override
    public List<Hospital> getAllHospitals() {
        // Opening a Hibernate session
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            // Creating and executing a query to retrieve all Hospital entities
            return session.createQuery("FROM Hospital", Hospital.class).list();
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
