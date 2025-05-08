package com.blood_bank; // Package declaration for the Blood Bank Management System

import com.bloodbank.service.impl.*; // Importing service implementation classes
import com.bloodbank.util.HibernateUtil; // Importing Hibernate utility class
import com.bloodbank.service.*; // Importing service interfaces
import com.bloodbank.dao.impl.*; // Importing DAO implementation classes
import java.text.ParseException; // Importing ParseException for handling date parsing exceptions
import java.text.SimpleDateFormat; // Importing SimpleDateFormat to format dates
import java.util.Date; // Importing Date class for working with date objects
import java.util.InputMismatchException; // Importing exception class to handle invalid input types
import java.util.List; // Importing List class to store multiple objects
import java.util.Scanner; // Importing Scanner class to take user input

import com.bloodbank.entity.*; // Importing entity classes like Admin, BloodBank, etc.

public class AllOperations { // Main class that contains all operations for the system

    private static Scanner sc = new Scanner(System.in); // Scanner object for user input

    // Service objects to interact with the respective services (e.g., AdminService, BloodBankService)
    private static AdminService adminService = new AdminServiceImpl(new AdminDAOImpl());
    private static BloodBankService bloodBankService = new BloodBankServiceImpl(new BloodBankDAOImpl());
    private static BloodService bloodService = new BloodServiceImpl(new BloodDAOImpl());
    private static DonorService donorService = new DonorServiceImpl(new DonorDAOImpl());
    private static EmployeeTeamService employeeTeamService = new EmployeeTeamServiceImpl(new EmployeeTeamDAOImpl());
    private static HospitalService hospitalService = new HospitalServiceImpl(new HospitalDAOImpl());
    private static PatientService patientService = new PatientServiceImpl(new PatientDAOImpl());

    // Main method where the menu loop starts
    public static void main(String[] args) {
        while (true) { // Infinite loop for continuous operation
            // Displaying the main menu to the user
            System.out.println("\n=== Blood Bank Management System ===");
            System.out.println("1. Admin Operations");
            System.out.println("2. Blood Bank Operations");
            System.out.println("3. Blood Operations");
            System.out.println("4. Donor Operations");
            System.out.println("5. Employee Team Operations");
            System.out.println("6. Hospital Operations");
            System.out.println("7. Patient Operations");
            System.out.println("8. Exit");

            try {
                int choice = sc.nextInt(); // Taking user input for menu choice
                sc.nextLine(); // Consume newline left by nextInt()

                // Switch-case to perform the selected operation
                switch (choice) {
                    case 1: adminOperations(); break; // Call admin operations if choice is 1
                    case 2: bloodBankOperations(); break; // Call blood bank operations if choice is 2
                    case 3: bloodOperations(); break; // Call blood operations if choice is 3
                    case 4: donorOperations(); break; // Call donor operations if choice is 4
                    case 5: employeeTeamOperations(); break; // Call employee team operations if choice is 5
                    case 6: hospitalOperations(); break; // Call hospital operations if choice is 6
                    case 7: patientOperations(); break; // Call patient operations if choice is 7
                    case 8: // Exit case
                        HibernateUtil.shutdown(); // Close Hibernate session factory
                        System.exit(0); // Exit the program
                    default: // Default case for invalid input
                        System.out.println("Invalid choice.");
                }
            } catch (InputMismatchException e) { // Catching invalid input exceptions
                System.out.println("Invalid input."); // Inform user about invalid input
                sc.nextLine(); // Clear the scanner buffer
            }
        }
    }

 // Admin Operations menu method
    public static void adminOperations() {
        while (true) { // Loop to continuously show admin menu
            // Display menu options
            System.out.println("\n=== Admin Operations ===");
            System.out.println("1. Add Admin");
            System.out.println("2. Update Admin");
            System.out.println("3. Delete Admin");
            System.out.println("4. Get Admin by ID");
            System.out.println("5. Get all Admins");
            System.out.println("6. Back to Main Menu");

            try {
                int choice = sc.nextInt(); // Read menu choice from user
                sc.nextLine(); // Consume leftover newline

                // Perform corresponding action based on user's choice
                switch (choice) {
                    case 1:
                        addAdmin(); // Call method to add new admin
                        break;
                    case 2:
                        updateAdmin(); // Call method to update existing admin
                        break;
                    case 3:
                        deleteAdmin(); // Call method to delete admin
                        break;
                    case 4:
                        getAdminById(); // Call method to fetch admin by ID
                        break;
                    case 5:
                        getAllAdmins(); // Call method to list all admins
                        break;
                    case 6:
                        return; // Exit to main menu
                    default:
                        System.out.println("Invalid choice."); // Invalid option
                }
            } catch (InputMismatchException e) { // Catch non-integer input
                System.out.println("Invalid input."); // Inform user of error
                sc.nextLine(); // Clear scanner buffer
            }
        }
    }

    // Method to add a new admin
    private static void addAdmin() {
        // Prompt user for admin details
        System.out.print("Enter Admin ID: ");
        String adminId = sc.nextLine(); // Read admin ID

        System.out.print("Enter Admin Name: ");
        String adminName = sc.nextLine(); // Read admin name

        System.out.print("Enter Location: ");
        String location = sc.nextLine(); // Read location

        System.out.print("Enter Contact Number: ");
        String contactNumber = sc.nextLine(); // Read contact number

        // Create Admin object and set its fields
        Admin admin = new Admin();
        admin.setAdminId(adminId);
        admin.setAdminName(adminName);
        admin.setLocation(location);
        admin.setContactNumber(contactNumber); // Set contact number

        // Save admin using service layer
        adminService.saveAdmin(admin);

        System.out.println("Admin added successfully."); // Success message
    }

    // Method to update existing admin details
    private static void updateAdmin() {
        System.out.print("Enter Admin ID to update: ");
        String adminId = sc.nextLine(); // Read admin ID

        // Fetch admin from service by ID
        Admin admin = adminService.getAdminById(adminId);

        if (admin != null) { // If admin is found
            // Prompt for updated values
            System.out.print("Enter new Admin Name: ");
            admin.setAdminName(sc.nextLine());

            System.out.print("Enter new Location: ");
            admin.setLocation(sc.nextLine());

            System.out.print("Enter new Contact Number: ");
            admin.setContactNumber(sc.nextLine());

            // Call service to update admin
            adminService.updateAdmin(admin);

            System.out.println("Admin updated successfully."); // Success message
        } else {
            System.out.println("Admin not found."); // If no admin found
        }
    }

    // Method to delete admin
    private static void deleteAdmin() {
        System.out.print("Enter Admin ID to delete: ");
        String adminId = sc.nextLine(); // Read admin ID

        // Delete admin using service layer
        adminService.deleteAdmin(adminId);

        System.out.println("Admin deleted successfully."); // Success message
    }

    // Method to fetch and display admin by ID
    private static void getAdminById() {
        System.out.print("Enter Admin ID: ");
        String adminId = sc.nextLine(); // Read admin ID

        // Fetch admin from service
        Admin admin = adminService.getAdminById(adminId);

        if (admin != null) { // If found
            // Print admin details
            System.out.println("Admin Details:");
            System.out.println("ID: " + admin.getAdminId());
            System.out.println("Name: " + admin.getAdminName());
            System.out.println("Location: " + admin.getLocation());
            System.out.println("Contact Number: " + admin.getContactNumber());
        } else {
            System.out.println("Admin not found."); // If not found
        }
    }

    // Method to list all admins
    private static void getAllAdmins() {
        // Fetch all admins from service
        List<Admin> admins = adminService.getAllAdmins();

        if (admins != null && !admins.isEmpty()) { // If list is not empty
            System.out.println("All Admins:");

            // Loop through each admin and print details
            for (Admin admin : admins) {
                System.out.println("------------------------");
                System.out.println("ID: " + admin.getAdminId());
                System.out.println("Name: " + admin.getAdminName());
                System.out.println("Location: " + admin.getLocation());
                System.out.println("Contact Number: " + admin.getContactNumber());
            }
        } else {
            System.out.println("No admins found."); // If list is empty
        }
    }

 // Blood Bank operations menu
    public static void bloodBankOperations() {
        while (true) {
            System.out.println("\n=== Blood Bank Operations ===");
            System.out.println("1. Add Blood Bank");
            System.out.println("2. Update Blood Bank");
            System.out.println("3. Delete Blood Bank");
            System.out.println("4. Get Blood Bank by ID");
            System.out.println("5. Get all Blood Banks");
            System.out.println("6. Update Blood Quantity");
            System.out.println("7. Exit");

            try {
                int choice = sc.nextInt();
                sc.nextLine(); // Clear buffer

                switch (choice) {
                    case 1:
                        addBloodBank();
                        break;
                    case 2:
                        updateBloodBank();
                        break;
                    case 3:
                        deleteBloodBank();
                        break;
                    case 4:
                        getBloodBankById();
                        break;
                    case 5:
                        getAllBloodBanks();
                        break;
                    case 6:
                        updateBloodQuantity();
                        break;
                    case 7:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine(); // Clear buffer
            }
        }
    }

    // Method to add a new Blood Bank
    private static void addBloodBank() {
        System.out.print("Enter Blood Bank ID: ");
        String bloodBankId = sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Location: ");
        String location = sc.nextLine();
        System.out.print("Enter Available Blood Groups: ");
        String availableBloodGroups = sc.nextLine();
        System.out.print("Enter Quantity: ");
        int quantity = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Admin ID: ");
        String adminId = sc.nextLine();

        Admin admin = adminService.getAdminById(adminId);
        if (admin == null) {
            System.out.println("Admin not found.");
            return;
        }

        BloodBank bloodBank = new BloodBank();
        bloodBank.setBloodBankId(bloodBankId);
        bloodBank.setName(name);
        bloodBank.setLocation(location);
        bloodBank.setAvailableBloodGroups(availableBloodGroups);
        bloodBank.setQuantity(quantity);
        bloodBank.setAdmin(admin);

        bloodBankService.saveBloodBank(bloodBank);
        System.out.println("Blood Bank added successfully.");
    }

    // Method to update Blood Bank
    private static void updateBloodBank() {
        System.out.print("Enter Blood Bank ID to update: ");
        String bloodBankId = sc.nextLine();
        BloodBank bloodBank = bloodBankService.getBloodBankById(bloodBankId);

        if (bloodBank == null) {
            System.out.println("Blood Bank not found.");
            return;
        }

        System.out.print("Enter new Name: ");
        bloodBank.setName(sc.nextLine());
        System.out.print("Enter new Location: ");
        bloodBank.setLocation(sc.nextLine());
        System.out.print("Enter new Available Blood Groups: ");
        bloodBank.setAvailableBloodGroups(sc.nextLine());
        System.out.print("Enter new Quantity: ");
        bloodBank.setQuantity(sc.nextInt());
        sc.nextLine(); // Clear buffer
        System.out.print("Enter Admin ID: ");
        String adminId = sc.nextLine();

        Admin admin = adminService.getAdminById(adminId);
        if (admin == null) {
            System.out.println("Admin not found.");
            return;
        }

        bloodBank.setAdmin(admin);
        bloodBankService.updateBloodBank(bloodBank);
        System.out.println("Blood Bank updated successfully.");
    }

    // Method to delete Blood Bank
    private static void deleteBloodBank() {
        System.out.print("Enter Blood Bank ID to delete: ");
        String bloodBankId = sc.nextLine();
        bloodBankService.deleteBloodBank(bloodBankId);
        System.out.println("Blood Bank deleted successfully.");
    }

    // Method to retrieve Blood Bank by ID
    private static void getBloodBankById() {
        System.out.print("Enter Blood Bank ID: ");
        String bloodBankId = sc.nextLine();
        BloodBank bloodBank = bloodBankService.getBloodBankById(bloodBankId);

        if (bloodBank != null) {
            System.out.println("Blood Bank Details:\n" + bloodBank);
        } else {
            System.out.println("Blood Bank not found.");
        }
    }

    // Method to retrieve all Blood Banks
    private static void getAllBloodBanks() {
        List<BloodBank> bloodBanks = bloodBankService.getAllBloodBanks();
        if (bloodBanks != null && !bloodBanks.isEmpty()) {
            System.out.println("All Blood Banks:");
            for (BloodBank bb : bloodBanks) {
                System.out.println(bb);
            }
        } else {
            System.out.println("No Blood Banks found.");
        }
    }

    // Method to update blood quantity
    private static void updateBloodQuantity() {
        System.out.print("Enter Blood Bank ID: ");
        String bloodBankId = sc.nextLine();
        System.out.print("Enter Quantity to Add/Remove (use negative number to remove): ");
        int quantityChange = sc.nextInt();
        sc.nextLine();

        try {
            bloodBankService.updateBloodQuantity(bloodBankId, quantityChange);
            System.out.println("Blood quantity updated successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
//Blood Operations
public static void bloodOperations() {
  while (true) { // Infinite loop to continuously show the blood operations menu
      // Displaying the Blood Operations menu
      System.out.println("\n=== Blood Operations ===");
      System.out.println("1. Add Blood");
      System.out.println("2. Update Blood");
      System.out.println("3. Delete Blood");
      System.out.println("4. Get Blood by ID");
      System.out.println("5. Get Blood by Blood Bank");
      System.out.println("6. Get all Blood");
      System.out.println("7. Back to Main Menu");

      try {
          int choice = sc.nextInt(); // Taking user input for menu choice
          sc.nextLine(); // Consume newline

          // Switch-case to handle the selected operation
          switch (choice) {
              case 1:
                  addBlood(); // Call method to add blood
                  break;
              case 2:
                  updateBlood(); // Call method to update blood
                  break;
              case 3:
                  deleteBlood(); // Call method to delete blood
                  break;
              case 4:
                  getBloodById(); // Call method to get blood by ID
                  break;
              case 5:
                  getBloodByBloodBank(); // Call method to get blood by blood bank
                  break;
              case 6:
                  getAllBlood(); // Call method to get all blood records
                  break;
              case 7:
                  return; // Return to main menu
              default:
                  System.out.println("Invalid choice."); // Inform user about invalid input
          }
      } catch (InputMismatchException e) { // Catching invalid input exception
          System.out.println("Invalid input."); // Inform user about invalid input
          sc.nextLine(); // Clear the scanner buffer
      }
  }
}

//Method to add new blood
private static void addBlood() {
  // Prompting user for blood details
  System.out.print("Enter Blood ID: ");
  String bloodId = sc.nextLine();
  System.out.print("Enter Blood Group: ");
  String bloodGroup = sc.nextLine();
  System.out.print("Enter Blood Bank ID: ");
  String bloodBankId = sc.nextLine();
  System.out.print("Enter Donor ID : ");
  String donorId = sc.nextLine();
  System.out.print("Enter Patient ID : ");
  String patientId = sc.nextLine();

  // Retrieving the blood bank using blood bank ID
  BloodBank bloodBank = bloodBankService.getBloodBankById(bloodBankId);
  if (bloodBank == null) { // If blood bank not found
      System.out.println("Blood Bank not found.");
      return; // Return to prevent further execution
  }

  // Retrieving the donor using donor ID, if provided
  Donor donor = null;
  if (!donorId.isEmpty()) {
      donor = donorService.getDonorById(donorId);
      if (donor == null) { // If donor not found
          System.out.println("Donor not found.");
          return;
      }
  }

  // Retrieving the patient using patient ID, if provided
  Patient patient = null;
  if (!patientId.isEmpty()) {
      patient = patientService.getPatientById(patientId);
      if (patient == null) { // If patient not found
          System.out.println("Patient not found.");
          return;
      }
  }

  // Creating a new Blood object and setting its properties
  Blood blood = new Blood();
  blood.setBloodId(bloodId);
  blood.setBloodGroup(bloodGroup);
  blood.setStoredIn(bloodBank); // Set the blood bank
  blood.setDonatedBy(donor); // Set the donor
  blood.setReceivedBy(patient); // Set the patient

  // Calling service to save the new blood record
  bloodService.saveBlood(blood);

  System.out.println("Blood added successfully."); // Informing user about success
}

//Method to update an existing blood record
private static void updateBlood() {
  System.out.print("Enter Blood ID to update: ");
  String bloodId = sc.nextLine();
  // Retrieving the blood record by ID
  Blood blood = bloodService.getBloodById(bloodId);

  if (blood != null) { // If blood exists
      // Prompting user for updated blood details
      System.out.print("Enter new Blood Group: ");
      blood.setBloodGroup(sc.nextLine());
      System.out.print("Enter new Blood Bank ID: ");
      String bloodBankId = sc.nextLine();
      // Retrieving the updated blood bank by ID
      BloodBank bloodBank = bloodBankService.getBloodBankById(bloodBankId);
      if (bloodBank == null) { // If blood bank not found
          System.out.println("Blood Bank not found.");
          return;
      }
      blood.setStoredIn(bloodBank); // Set the updated blood bank

      // Prompting user for updated donor ID
      System.out.print("Enter new Donor ID : ");
      String donorId = sc.nextLine();
      Donor donor = null;
      if (!donorId.isEmpty()) {
          donor = donorService.getDonorById(donorId);
          if (donor == null) { // If donor not found
              System.out.println("Donor not found.");
              return;
          }
      }
      blood.setDonatedBy(donor); // Set the updated donor

      // Prompting user for updated patient ID
      System.out.print("Enter new Patient ID (or leave blank): ");
      String patientId = sc.nextLine();
      Patient patient = null;
      if (!patientId.isEmpty()) {
          patient = patientService.getPatientById(patientId);
          if (patient == null) { // If patient not found
              System.out.println("Patient not found.");
              return;
          }
      }
      blood.setReceivedBy(patient); // Set the updated patient

      // Calling service to update the blood record
      bloodService.updateBlood(blood);
      System.out.println("Blood updated successfully."); // Informing user about success
  } else {
      System.out.println("Blood not found."); // If blood record not found
  }
}

//Method to delete a blood record
private static void deleteBlood() {
  System.out.print("Enter Blood ID to delete: ");
  String bloodId = sc.nextLine();
  // Calling service to delete the blood record by ID
  bloodService.deleteBlood(bloodId);
  System.out.println("Blood deleted successfully."); // Informing user about success
}

//Method to get a blood record by ID
private static void getBloodById() {
  System.out.print("Enter Blood ID: ");
  String bloodId = sc.nextLine();
  // Retrieving blood record by ID from the service
  Blood blood = bloodService.getBloodById(bloodId);

  if (blood != null) { // If blood exists
      System.out.println("Blood Details: " + blood); // Displaying blood details
  } else {
      System.out.println("Blood not found."); // If blood not found
  }
}

//Method to get blood records by Blood Bank ID
private static void getBloodByBloodBank() {
  System.out.print("Enter Blood Bank ID: ");
  String bloodBankId = sc.nextLine();
  // Retrieving the list of blood records for a specific blood bank
  List<Blood> bloodList = bloodService.getBloodByBloodBankId(bloodBankId);

  if (bloodList != null && !bloodList.isEmpty()) { // If list is not empty
      System.out.println("Blood Details for Blood Bank ID " + bloodBankId + ":");
      // Iterating through the list of blood records
      for (Blood blood : bloodList) {
          System.out.println(blood); // Displaying each blood record
      }
  } else {
      System.out.println("No blood found for Blood Bank ID " + bloodBankId + "."); // If no blood found
  }
}

//Method to get all blood records
private static void getAllBlood() {
  // Retrieving the list of all blood records
  List<Blood> bloodList = bloodService.getAllBlood();
  if (bloodList != null && !bloodList.isEmpty()) { // If list is not empty
      System.out.println("All Blood Details:");
      // Iterating through the list of all blood records
      for (Blood blood : bloodList) {
          System.out.println(blood); // Displaying each blood record
      }
  } else {
      System.out.println("No blood found."); // If no blood found
  }
}
//Donor Operations
public static void donorOperations() {
 while (true) { // Infinite loop to continuously show the donor operations menu
     // Displaying the Donor Operations menu
     System.out.println("\n=== Donor Operations ===");
     System.out.println("1. Add Donor");
     System.out.println("2. Update Donor");
     System.out.println("3. Delete Donor");
     System.out.println("4. Get Donor by ID");
     System.out.println("5. Get all Donors");
     System.out.println("6. Back to Main Menu");

     try {
         int choice = sc.nextInt(); // Taking user input for menu choice
         sc.nextLine(); // Consume newline

         // Switch-case to handle the selected operation
         switch (choice) {
             case 1:
                 addDonor(); // Call method to add donor
                 break;
             case 2:
                 updateDonor(); // Call method to update donor
                 break;
             case 3:
                 deleteDonor(); // Call method to delete donor
                 break;
             case 4:
                 getDonorById(); // Call method to get donor by ID
                 break;
             case 5:
                 getAllDonors(); // Call method to get all donors
                 break;
             case 6:
                 return; // Return to main menu
             default:
                 System.out.println("Invalid choice."); // Inform user about invalid input
         }
     } catch (InputMismatchException e) { // Catching invalid input exception
         System.out.println("Invalid input."); // Inform user about invalid input
         sc.nextLine(); // Clear the scanner buffer
     }
 }
}

//Method to add a new donor
private static void addDonor() {
 // Prompting user for donor details
 System.out.print("Enter Donor ID: ");
 String donorId = sc.nextLine();
 System.out.print("Enter Donor Name: ");
 String donorName = sc.nextLine();
 System.out.print("Enter Donor Gender: ");
 String donorGender = sc.nextLine();
 System.out.print("Enter Donor Address: ");
 String donorAddress = sc.nextLine();
 System.out.print("Enter Contact No: ");
 String contactNo = sc.nextLine();
 System.out.print("Enter Age: ");
 int age = sc.nextInt();
 sc.nextLine(); // Consume newline
 System.out.print("Enter Blood Group: ");
 String bloodGroup = sc.nextLine();
 System.out.print("Enter Date of Donation (yyyy-MM-dd): ");
 String dateString = sc.nextLine();
 System.out.print("Enter Employee ID: ");
 String empId = sc.nextLine();

 // Retrieving the employee who registered the donor
 EmployeeTeam employeeTeam = employeeTeamService.getEmployeeTeamById(empId);
 if (employeeTeam == null) { // If employee not found
     System.out.println("Employee not found.");
     return; // Return to prevent further execution
 }

 // Parsing the date of donation
 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
 Date dateOfDonation = null;
 try {
     dateOfDonation = dateFormat.parse(dateString); // Parse the date string to Date object
 } catch (ParseException e) {
     System.out.println("Invalid date format."); // If date format is invalid
     return;
 }

 // Creating a new Donor object and setting its properties
 Donor donor = new Donor();
 donor.setDonorId(donorId);
 donor.setDonorName(donorName);
 donor.setDonorGender(donorGender);
 donor.setDonorAddress(donorAddress);
 donor.setContactNo(contactNo);
 donor.setAge(age);
 donor.setBloodGroup(bloodGroup);
 donor.setDateOfDonation(dateOfDonation);
 donor.setRegisteredBy(employeeTeam); // Setting the employee who registered the donor

 // Calling service to save the new donor record
 donorService.saveDonor(donor);

 System.out.println("Donor added successfully."); // Informing user about success
}

//Method to update an existing donor record
private static void updateDonor() {
 System.out.print("Enter Donor ID to update: ");
 String donorId = sc.nextLine();
 // Retrieving the donor record by ID
 Donor donor = donorService.getDonorById(donorId);

 if (donor != null) { // If donor exists
     // Prompting user for updated donor details
     System.out.print("Enter new Donor Name: ");
     donor.setDonorName(sc.nextLine());
     System.out.print("Enter new Donor Gender: ");
     donor.setDonorGender(sc.nextLine());
     System.out.print("Enter new Donor Address: ");
     donor.setDonorAddress(sc.nextLine());
     System.out.print("Enter new Contact No: ");
     donor.setContactNo(sc.nextLine());
     System.out.print("Enter new Age: ");
     donor.setAge(sc.nextInt());
     sc.nextLine(); // Consume newline
     System.out.print("Enter new Blood Group: ");
     donor.setBloodGroup(sc.nextLine());
     System.out.print("Enter new Date of Donation (yyyy-MM-dd): ");
     String dateString = sc.nextLine();
     System.out.print("Enter new Employee ID: ");
     String empId = sc.nextLine();

     // Retrieving the employee who registered the donor
     EmployeeTeam employeeTeam = employeeTeamService.getEmployeeTeamById(empId);
     if (employeeTeam == null) { // If employee not found
         System.out.println("Employee not found.");
         return;
     }

     // Parsing the new date of donation
     SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
     Date dateOfDonation = null;
     try {
         dateOfDonation = dateFormat.parse(dateString); // Parse the date string to Date object
     } catch (ParseException e) {
         System.out.println("Invalid date format."); // If date format is invalid
         return;
     }

     // Updating donor details
     donor.setDateOfDonation(dateOfDonation);
     donor.setRegisteredBy(employeeTeam); // Setting the updated employee

     // Calling service to update the donor record
     donorService.updateDonor(donor);
     System.out.println("Donor updated successfully."); // Informing user about success
 } else {
     System.out.println("Donor not found."); // If donor record not found
 }
}

//Method to delete a donor record
private static void deleteDonor() {
 System.out.print("Enter Donor ID to delete: ");
 String donorId = sc.nextLine();
 // Calling service to delete the donor record by ID
 donorService.deleteDonor(donorId);
 System.out.println("Donor deleted successfully."); // Informing user about success
}

//Method to get a donor record by ID
private static void getDonorById() {
 System.out.print("Enter Donor ID: ");
 String donorId = sc.nextLine();
 // Retrieving donor record by ID from the service
 Donor donor = donorService.getDonorById(donorId);

 if (donor != null) { // If donor exists
     System.out.println("Donor Details: " + donor); // Displaying donor details
 } else {
     System.out.println("Donor not found."); // If donor not found
 }
}

//Method to get all donor records
private static void getAllDonors() {
 // Retrieving the list of all donor records
 List<Donor> donors = donorService.getAllDonors();
 if (donors != null && !donors.isEmpty()) { // If list is not empty
     System.out.println("All Donors:");
     // Iterating through the list of all donors
     for (Donor donor : donors) {
         System.out.println(donor); // Displaying each donor record
     }
 } else {
     System.out.println("No donors found."); // If no donors found
 }
}
//Employee Team Operations
public static void employeeTeamOperations() {
 while (true) { // Infinite loop to continuously show the employee team operations menu
     // Displaying the Employee Team Operations menu
     System.out.println("\n=== Employee Team Operations ===");
     System.out.println("1. Add Employee ");
     System.out.println("2. Update Employee ");
     System.out.println("3. Delete Employee ");
     System.out.println("4. Get Employee by ID");
     System.out.println("5. Get all Employees ");
     System.out.println("6. Back to Main Menu");

     try {
         int choice = sc.nextInt(); // Taking user input for menu choice
         sc.nextLine(); // Consume newline left by nextInt()

         // Switch-case to handle the selected operation
         switch (choice) {
             case 1:
                 addEmployeeTeam(); // Call method to add an employee to the team
                 break;
             case 2:
                 updateEmployeeTeam(); // Call method to update employee details
                 break;
             case 3:
                 deleteEmployeeTeam(); // Call method to delete employee
                 break;
             case 4:
                 getEmployeeTeamById(); // Call method to fetch employee by ID
                 break;
             case 5:
                 getAllEmployeeTeams(); // Call method to list all employees
                 break;
             case 6:
                 return; // Exit to main menu
             default:
                 System.out.println("Invalid choice."); // Inform user of invalid choice
         }
     } catch (InputMismatchException e) { // Catch invalid input
         System.out.println("Invalid input. Please enter a number."); // Inform user
         sc.nextLine(); // Clear the scanner buffer
     }
 }
}

//Method to add a new employee to the team
private static void addEmployeeTeam() {
 // Prompting user for employee details
 System.out.print("Enter Employee ID: ");
 String empId = sc.nextLine(); // Read employee ID

 System.out.print("Enter Employee Name: ");
 String empName = sc.nextLine(); // Read employee name

 System.out.print("Enter Contact Number: ");
 String contactNo = sc.nextLine(); // Read employee contact number

 // Creating and populating a new EmployeeTeam object
 EmployeeTeam employeeTeam = new EmployeeTeam();
 employeeTeam.setEmpId(empId);         // Set employee ID
 employeeTeam.setEmpName(empName);     // Set employee name
 employeeTeam.setContactNo(contactNo); // Set employee contact number

 // Save the new employee using the service
 employeeTeamService.saveEmployeeTeam(employeeTeam);

 System.out.println("Employee added successfully."); // Success message
}

//Method to update an existing employee
private static void updateEmployeeTeam() {
 // Prompt for employee ID to update
 System.out.print("Enter Employee ID to update: ");
 String empId = sc.nextLine(); // Read employee ID

 // Fetch employee from service
 EmployeeTeam employeeTeam = employeeTeamService.getEmployeeTeamById(empId);

 if (employeeTeam != null) { // Check if employee exists
     // Prompt for new values
     System.out.print("Enter new Employee Name: ");
     String newName = sc.nextLine(); // Read new name

     System.out.print("Enter new Contact Number: ");
     String newContact = sc.nextLine(); // Read new contact

     // Set updated values
     employeeTeam.setEmpName(newName);
     employeeTeam.setContactNo(newContact);

     // Update the employee using the service
     employeeTeamService.updateEmployeeTeam(employeeTeam);

     System.out.println("Employee updated successfully."); // Success message
 } else {
     System.out.println("Employee not found."); // If no employee found
 }
}

//Method to delete an employee by ID
private static void deleteEmployeeTeam() {
 System.out.print("Enter Employee ID to delete: ");
 String empId = sc.nextLine(); // Read employee ID

 // Delete employee using the service
 employeeTeamService.deleteEmployeeTeam(empId);

 System.out.println("Employee deleted successfully."); // Success message
}

//Method to get and display employee by ID
private static void getEmployeeTeamById() {
 System.out.print("Enter Employee ID: ");
 String empId = sc.nextLine(); // Read employee ID

 // Fetch employee from service
 EmployeeTeam employeeTeam = employeeTeamService.getEmployeeTeamById(empId);

 if (employeeTeam != null) { // If employee exists
     // Display employee details
     System.out.println("Employee Details:");
     System.out.println("ID: " + employeeTeam.getEmpId());
     System.out.println("Name: " + employeeTeam.getEmpName());
     System.out.println("Contact: " + employeeTeam.getContactNo());
 } else {
     System.out.println("Employee not found."); // If employee not found
 }
}

//Method to display all employees
private static void getAllEmployeeTeams() {
 // Fetch all employees from service
 List<EmployeeTeam> employeeTeams = employeeTeamService.getAllEmployeeTeams();

 if (employeeTeams != null && !employeeTeams.isEmpty()) { // Check if list is not empty
     System.out.println("All Employees:");

     // Loop through and print each employee
     for (EmployeeTeam employeeTeam : employeeTeams) {
         System.out.println("------------------------");
         System.out.println("ID: " + employeeTeam.getEmpId());
         System.out.println("Name: " + employeeTeam.getEmpName());
         System.out.println("Contact: " + employeeTeam.getContactNo());
     }
 } else {
     System.out.println("No employee found."); // If list is empty
 }
}

//Hospital Operations
public static void hospitalOperations() {
 while (true) { // Infinite loop to continuously show the hospital operations menu
     // Displaying the Hospital Operations menu
     System.out.println("\n=== Hospital Operations ===");
     System.out.println("1. Add Hospital");
     System.out.println("2. Update Hospital");
     System.out.println("3. Delete Hospital");
     System.out.println("4. Get Hospital by ID");
     System.out.println("5. Get all Hospitals");
     System.out.println("6. Back to Main Menu");

     try {
         int choice = sc.nextInt(); // Taking user input for menu choice
         sc.nextLine(); // Consume newline

         // Switch-case to handle the selected operation
         switch (choice) {
             case 1:
                 addHospital(); // Call method to add a new hospital
                 break;
             case 2:
                 updateHospital(); // Call method to update an existing hospital
                 break;
             case 3:
                 deleteHospital(); // Call method to delete a hospital
                 break;
             case 4:
                 getHospitalById(); // Call method to get hospital details by ID
                 break;
             case 5:
                 getAllHospitals(); // Call method to get all hospitals
                 break;
             case 6:
                 return; // Return to the main menu
             default:
                 System.out.println("Invalid choice."); // Inform user about invalid input
         }
     } catch (InputMismatchException e) { // Catching invalid input exception
         System.out.println("Invalid input."); // Inform user about invalid input
         sc.nextLine(); // Clear the scanner buffer
     }
 }
}

//Method to add a new hospital to the system
private static void addHospital() {
 // Prompting user for hospital details
 System.out.print("Enter Hospital ID: ");
 String hospitalId = sc.nextLine();
 System.out.print("Enter Hospital Name: ");
 String hospitalName = sc.nextLine();
 System.out.print("Enter Location: ");
 String location = sc.nextLine();
 System.out.print("Enter Blood Bank ID: ");
 String bloodBankId = sc.nextLine();

 // Retrieving the associated blood bank by its ID from the service
 BloodBank bloodBank = bloodBankService.getBloodBankById(bloodBankId);
 if (bloodBank == null) { // If blood bank is not found
     System.out.println("Blood Bank not found.");
     return;
 }

 // Creating a new Hospital object and setting its properties
 Hospital hospital = new Hospital();
 hospital.setHospitalId(hospitalId);
 hospital.setHospitalName(hospitalName);
 hospital.setLocation(location);
 hospital.setContactedBloodBank(bloodBank); // Associating the hospital with a blood bank
 hospitalService.saveHospital(hospital); // Calling service to save the new hospital

 System.out.println("Hospital added successfully."); // Informing user about success
}

//Method to update an existing hospital's details
private static void updateHospital() {
 System.out.print("Enter Hospital ID to update: ");
 String hospitalId = sc.nextLine();
 // Retrieving hospital record by ID from the service
 Hospital hospital = hospitalService.getHospitalById(hospitalId);

 if (hospital != null) { // If hospital exists
     // Prompting user for updated hospital details
     System.out.print("Enter new Hospital Name: ");
     hospital.setHospitalName(sc.nextLine());
     System.out.print("Enter new Location: ");
     hospital.setLocation(sc.nextLine());
     System.out.print("Enter new Blood Bank ID: ");
     String bloodBankId = sc.nextLine();

     // Retrieving the associated blood bank by its ID from the service
     BloodBank bloodBank = bloodBankService.getBloodBankById(bloodBankId);
     if (bloodBank == null) { // If blood bank is not found
         System.out.println("Blood Bank not found.");
         return;
     }

     hospital.setContactedBloodBank(bloodBank); // Associating the hospital with a blood bank
     hospitalService.updateHospital(hospital); // Calling service to update the hospital
     System.out.println("Hospital updated successfully."); // Informing user about success
 } else {
     System.out.println("Hospital not found."); // If hospital record not found
 }
}

//Method to delete a hospital from the system
private static void deleteHospital() {
 System.out.print("Enter Hospital ID to delete: ");
 String hospitalId = sc.nextLine();
 // Calling service to delete the hospital record by ID
 hospitalService.deleteHospital(hospitalId);
 System.out.println("Hospital deleted successfully."); // Informing user about success
}

//Method to get a hospital record by its ID
private static void getHospitalById() {
 System.out.print("Enter Hospital ID: ");
 String hospitalId = sc.nextLine();
 // Retrieving hospital record by ID from the service
 Hospital hospital = hospitalService.getHospitalById(hospitalId);

 if (hospital != null) { // If hospital exists
     System.out.println("Hospital Details: " + hospital); // Displaying hospital details
 } else {
     System.out.println("Hospital not found."); // If hospital not found
 }
}

//Method to get all hospital records
private static void getAllHospitals() {
 // Retrieving the list of all hospital records
 List<Hospital> hospitals = hospitalService.getAllHospitals();
 if (hospitals != null && !hospitals.isEmpty()) { // If list is not empty
     System.out.println("All Hospitals:");
     // Iterating through the list of all hospitals
     for (Hospital hospital : hospitals) {
         System.out.println(hospital); // Displaying each hospital record
     }
 } else {
     System.out.println("No hospitals found."); // If no hospitals found
 }
}
//Patient Operations
public static void patientOperations() {
 while (true) { // Infinite loop to keep showing the patient operations menu
     // Displaying the Patient Operations menu
     System.out.println("\n=== Patient Operations ===");
     System.out.println("1. Add Patient");
     System.out.println("2. Update Patient");
     System.out.println("3. Delete Patient");
     System.out.println("4. Get Patient by ID");
     System.out.println("5. Get all Patients");
     System.out.println("6. Back to Main Menu");

     try {
         int choice = sc.nextInt(); // Taking user input for the menu choice
         sc.nextLine(); // Consume newline after input

         // Switch-case to execute the selected operation
         switch (choice) {
             case 1:
                 addPatient(); // Call method to add a new patient
                 break;
             case 2:
                 updatePatient(); // Call method to update patient details
                 break;
             case 3:
                 deletePatient(); // Call method to delete a patient
                 break;
             case 4:
                 getPatientById(); // Call method to fetch patient details by ID
                 break;
             case 5:
                 getAllPatients(); // Call method to fetch all patients
                 break;
             case 6:
                 return; // Return to the main menu
             default:
                 System.out.println("Invalid choice."); // If an invalid choice is entered
         }
     } catch (InputMismatchException e) { // Catch input mismatch exceptions
         System.out.println("Invalid input."); // Inform user of invalid input
         sc.nextLine(); // Clear the input buffer
     }
 }
}

//Method to add a new patient
private static void addPatient() {
 // Prompting the user to enter patient details
 System.out.print("Enter Patient ID: ");
 String patientId = sc.nextLine();
 System.out.print("Enter Patient Name: ");
 String patientName = sc.nextLine();
 System.out.print("Enter Patient Gender: ");
 String patientGender = sc.nextLine();
 System.out.print("Enter Patient Address: ");
 String patientAddress = sc.nextLine();
 System.out.print("Enter Contact No: ");
 String contactNo = sc.nextLine();
 System.out.print("Enter Blood Group: ");
 String bloodGroup = sc.nextLine();
 System.out.print("Enter Date of Intake (yyyy-MM-dd): ");
 String dateString = sc.nextLine();
 System.out.print("Enter Employee ID: ");
 String empId = sc.nextLine();

 // Fetching employee details associated with the patient
 EmployeeTeam employeeTeam = employeeTeamService.getEmployeeTeamById(empId);
 if (employeeTeam == null) { // If employee not found, return early
     System.out.println("Employee not found.");
     return;
 }

 // Parsing the date string to a Date object
 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
 Date dateOfIntake = null;
 try {
     dateOfIntake = dateFormat.parse(dateString); // Parse the input date
 } catch (ParseException e) { // If parsing fails
     System.out.println("Invalid date format.");
     return;
 }

 // Creating a new Patient object and setting its properties
 Patient patient = new Patient();
 patient.setPatientId(patientId);
 patient.setPatientName(patientName);
 patient.setPatientGender(patientGender);
 patient.setPatientAddress(patientAddress);
 patient.setContactNo(contactNo);
 patient.setBloodGroup(bloodGroup);
 patient.setDateOfIntake(dateOfIntake); // Set the date of intake
 patient.setRegisteredBy(employeeTeam); // Set the employee who registered the patient
 patientService.savePatient(patient); // Saving the new patient in the system

 System.out.println("Patient added successfully."); // Success message
}

//Method to update an existing patient's details
private static void updatePatient() {
 // Prompting for the patient ID to update
 System.out.print("Enter Patient ID to update: ");
 String patientId = sc.nextLine();
 // Fetching the patient details by ID
 Patient patient = patientService.getPatientById(patientId);

 if (patient != null) { // If patient exists, update details
     // Prompting the user to enter updated patient details
     System.out.print("Enter new Patient Name: ");
     patient.setPatientName(sc.nextLine());
     System.out.print("Enter new Patient Gender: ");
     patient.setPatientGender(sc.nextLine());
     System.out.print("Enter new Patient Address: ");
     patient.setPatientAddress(sc.nextLine());
     System.out.print("Enter new Contact No: ");
     patient.setContactNo(sc.nextLine());
     System.out.print("Enter new Blood Group: ");
     patient.setBloodGroup(sc.nextLine());
     System.out.print("Enter new Date of Intake (yyyy-MM-dd): ");
     String dateString = sc.nextLine();
     System.out.print("Enter new Employee ID: ");
     String empId = sc.nextLine();

     // Fetching the employee details associated with the patient
     EmployeeTeam employeeTeam = employeeTeamService.getEmployeeTeamById(empId);
     if (employeeTeam == null) { // If employee not found
         System.out.println("Employee not found.");
         return;
     }

     // Parsing the new date of intake
     SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
     Date dateOfIntake = null;
     try {
         dateOfIntake = dateFormat.parse(dateString); // Parse the date string
     } catch (ParseException e) { // If date parsing fails
         System.out.println("Invalid date format.");
         return;
     }

     // Setting the updated details in the patient object
     patient.setDateOfIntake(dateOfIntake);
     patient.setRegisteredBy(employeeTeam); // Updating the employee
     patientService.updatePatient(patient); // Saving the updated patient details
     System.out.println("Patient updated successfully."); // Success message
 } else {
     System.out.println("Patient not found."); // If patient does not exist
 }
}

//Method to delete a patient
private static void deletePatient() {
 System.out.print("Enter Patient ID to delete: ");
 String patientId = sc.nextLine();
 // Calling service to delete the patient by ID
 patientService.deletePatient(patientId);
 System.out.println("Patient deleted successfully."); // Success message
}

//Method to fetch a patient's details by ID
private static void getPatientById() {
 System.out.print("Enter Patient ID: ");
 String patientId = sc.nextLine();
 // Fetching patient details by ID
 Patient patient = patientService.getPatientById(patientId);

 if (patient != null) { // If patient exists
     System.out.println("Patient Details: " + patient); // Displaying patient details
 } else {
     System.out.println("Patient not found."); // If patient is not found
 }
}

//Method to fetch all patients from the system
private static void getAllPatients() {
 // Fetching the list of all patients
 List<Patient> patients = patientService.getAllPatients();
 if (patients != null && !patients.isEmpty()) { // If patients exist
     System.out.println("All Patients:");
     for (Patient patient : patients) {
         System.out.println(patient); // Displaying each patient's details
     }
 } else {
     System.out.println("No patients found."); // If no patients are found
 }
}
}

