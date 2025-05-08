package com.bloodbank.dao.impl;

import java.util.List;  // Importing List to handle collections of Patient entities
import org.hibernate.Session;  // Importing Session to interact with the Hibernate session
import org.hibernate.Transaction;  // Importing Transaction to manage transactions in Hibernate
import com.bloodbank.dao.PatientDAO;  // Importing the PatientDao interface
import com.bloodbank.entity.Patient;  // Importing the Patient entity class
import com.bloodbank.util.HibernateUtil;  // Importing HibernateUtil to get the session factory

// Implementation of the PatientDao interface for CRUD operations on Patient entities
public class PatientDAOImpl implements PatientDAO {

    // Method to retrieve a Patient record by its unique identifier (patientId)
    @Override
    public Patient getPatientById(String patientId) {
        // Opening a Hibernate session
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            // Retrieving the Patient entity by its primary key (patientId)
            return session.get(Patient.class, patientId);
        } catch (Exception e) {
            // Printing the exception stack trace if any error occurs
            e.printStackTrace();
            return null;
        } finally {
            // Closing the session after the operation
            session.close();
        }
    }

    // Method to save a new Patient record to the database
    @Override
    public void savePatient(Patient patient) {
        // Opening a Hibernate session
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;  // Declaring a transaction object
        try {
            // Starting a new transaction
            transaction = session.beginTransaction();
            // Saving the Patient entity to the database
            session.save(patient);
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

    // Method to update an existing Patient record in the database
    @Override
    public void updatePatient(Patient patient) {
        // Opening a Hibernate session
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;  // Declaring a transaction object
        try {
            // Starting a new transaction
            transaction = session.beginTransaction();
            // Updating the Patient entity in the database
            session.update(patient);
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

    // Method to delete a Patient record from the database by its unique identifier (patientId)
    @Override
    public void deletePatient(String patientId) {
        // Opening a Hibernate session
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;  // Declaring a transaction object
        try {
            // Starting a new transaction
            transaction = session.beginTransaction();
            // Retrieving the Patient entity by its primary key (patientId)
            Patient patient = session.get(Patient.class, patientId);
            // If the Patient entity exists, delete it
            if (patient != null) {
                session.delete(patient);
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

    // Method to retrieve all Patient records from the database
    @Override
    public List<Patient> getAllPatients() {
        // Opening a Hibernate session
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            // Creating and executing a query to retrieve all Patient entities
            return session.createQuery("FROM Patient", Patient.class).list();
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
