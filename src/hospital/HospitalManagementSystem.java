package hospital;

import java.util.Scanner;

public class HospitalManagementSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static PatientBST patientBST = new PatientBST();
    private static EmergencyQueue emergencyQueue = new EmergencyQueue();
    private static TreatmentStack treatmentStack = new TreatmentStack();

    public static void main(String[] args) {
        loadSampleData();

        int choice;

        do {
            displayMenu();
            choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    registerPatient();
                    break;
                case 2:
                    searchPatient();
                    break;
                case 3:
                    deletePatient();
                    break;
                case 4:
                    patientBST.displayInOrder();
                    break;
                case 5:
                    addEmergencyPatient();
                    break;
                case 6:
                    treatNextPatient();
                    break;
                case 7:
                    emergencyQueue.display();
                    break;
                case 8:
                    completeTreatment();
                    break;
                case 9:
                    removeLastTreatment();
                    break;
                case 10:
                    treatmentStack.display();
                    break;
                case 11:
                    addVisit();
                    break;
                case 12:
                    removeVisit();
                    break;
                case 13:
                    searchVisit();
                    break;
                case 14:
                    displayVisitHistory();
                    break;
                case 0:
                    System.out.println("Thank you for using the Mini Hospital Emergency Management System.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n==============================================");
        System.out.println("   MINI HOSPITAL EMERGENCY MANAGEMENT SYSTEM");
        System.out.println("==============================================");
        System.out.println("1. Register new patient (BST Insert)");
        System.out.println("2. Search patient (BST Search)");
        System.out.println("3. Delete patient (BST Delete)");
        System.out.println("4. Display patients (BST In-order)");
        System.out.println("5. Add emergency patient (Queue Enqueue)");
        System.out.println("6. Treat next patient (Queue Dequeue)");
        System.out.println("7. Display emergency queue");
        System.out.println("8. Complete treatment (Stack Push)");
        System.out.println("9. Remove last treatment (Stack Pop)");
        System.out.println("10. Display treatment records");
        System.out.println("11. Add patient visit (Linked List)");
        System.out.println("12. Remove patient visit (Linked List)");
        System.out.println("13. Search patient visit (Linked List)");
        System.out.println("14. Display patient visit history");
        System.out.println("0. Exit");
        System.out.println("==============================================");
    }

    private static void registerPatient() {
        System.out.println("\n--- Register New Patient ---");
        int id = readInt("Patient ID: ");
        if (patientBST.search(id) != null) {
            System.out.println("A patient with this ID already exists.");
            return;
        }

        String name = readText("Patient Name: ");
        int age = readInt("Age: ");
        String contact = readText("Contact Number: ");
        String condition = readText("Medical Condition: ");

        Patient patient = new Patient(id, name, age, contact, condition);
        patientBST.insert(patient);

        System.out.println("Patient registered successfully.");
    }

    private static void searchPatient() {
        System.out.println("\n--- Search Patient ---");
        int id = readInt("Enter Patient ID: ");
        Patient patient = patientBST.search(id);

        if (patient == null) {
            System.out.println("Patient not found.");
        } else {
            System.out.println("Patient found:");
            System.out.println(patient);
        }
    }

    private static void deletePatient() {
        System.out.println("\n--- Delete Patient ---");
        int id = readInt("Enter Patient ID to delete: ");

        if (patientBST.search(id) == null) {
            System.out.println("Patient not found.");
        } else {
            patientBST.delete(id);
            System.out.println("Patient deleted successfully.");
        }
    }

    private static void addEmergencyPatient() {
        System.out.println("\n--- Add Emergency Patient ---");
        int id = readInt("Enter existing Patient ID: ");
        Patient patient = patientBST.search(id);

        if (patient == null) {
            System.out.println("Patient not found. Register the patient first.");
        } else {
            emergencyQueue.enqueue(patient);
            System.out.println(patient.getPatientName() + " added to the emergency queue.");
        }
    }

    private static void treatNextPatient() {
        System.out.println("\n--- Treat Next Patient ---");
        Patient patient = emergencyQueue.dequeue();

        if (patient == null) {
            System.out.println("No patients are waiting in the emergency queue.");
        } else {
            System.out.println("Now treating: " + patient);
        }
    }

    private static void completeTreatment() {
        System.out.println("\n--- Complete Treatment ---");
        int id = readInt("Patient ID: ");
        Patient patient = patientBST.search(id);

        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }

        String doctor = readText("Doctor Name: ");
        String treatment = readText("Treatment: ");
        String date = readText("Treatment Date: ");

        TreatmentRecord record = new TreatmentRecord(
                patient.getPatientId(),
                patient.getPatientName(),
                doctor,
                treatment,
                date
        );

        treatmentStack.push(record);
        System.out.println("Treatment completed and added to the stack.");
    }

    private static void removeLastTreatment() {
        System.out.println("\n--- Remove Last Treatment ---");
        TreatmentRecord record = treatmentStack.pop();

        if (record == null) {
            System.out.println("Treatment stack is empty.");
        } else {
            System.out.println("Removed treatment record:");
            System.out.println(record);
        }
    }

    private static void addVisit() {
        System.out.println("\n--- Add Patient Visit ---");
        int patientId = readInt("Patient ID: ");
        Patient patient = patientBST.search(patientId);

        if (patient == null) {
            System.out.println("Patient not found. Register the patient first.");
            return;
        }

        int visitId = readInt("Visit ID: ");
        String date = readText("Visit Date: ");
        String doctor = readText("Doctor Name: ");
        String diagnosis = readText("Diagnosis: ");
        String treatment = readText("Treatment: ");

        patient.getVisitHistory().addVisit(new Visit(visitId, date, doctor, diagnosis, treatment));
        System.out.println("Visit added successfully to this patient's history.");
    }

    private static Patient getPatientForVisit() {
        int patientId = readInt("Patient ID: ");
        Patient patient = patientBST.search(patientId);

        if (patient == null) {
            System.out.println("Patient not found.");
        }

        return patient;
    }

    private static void removeVisit() {
        System.out.println("\n--- Remove Patient Visit ---");
        Patient patient = getPatientForVisit();

        if (patient == null) {
            return;
        }

        int visitId = readInt("Visit ID to remove: ");

        if (patient.getVisitHistory().removeVisit(visitId)) {
            System.out.println("Visit removed successfully.");
        } else {
            System.out.println("Visit not found for this patient.");
        }
    }

    private static void searchVisit() {
        System.out.println("\n--- Search Patient Visit ---");
        Patient patient = getPatientForVisit();

        if (patient == null) {
            return;
        }

        int visitId = readInt("Visit ID to search: ");
        Visit visit = patient.getVisitHistory().searchVisit(visitId);

        if (visit == null) {
            System.out.println("Visit not found.");
        } else {
            System.out.println("Visit found:");
            System.out.println(visit);
        }
    }

    private static void displayVisitHistory() {
        System.out.println("\n--- Display Patient Visit History ---");
        Patient patient = getPatientForVisit();

        if (patient != null) {
            System.out.println("History for " + patient.getPatientName() + ":");
            patient.getVisitHistory().displayHistory();
        }
    }

    private static int readInt(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static String readText(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    private static void loadSampleData() {
        Patient p103 = new Patient(103, "Ahamed", 45, "0771234567", "Chest pain");
        Patient p101 = new Patient(101, "Fathima", 30, "0712345678", "Fever");
        Patient p105 = new Patient(105, "Nimal", 52, "0756789012", "Fracture");
        Patient p102 = new Patient(102, "Hassan", 25, "0763456789", "Asthma");
        Patient p104 = new Patient(104, "Sara", 38, "0789876543", "Migraine");

        patientBST.insert(p103);
        patientBST.insert(p101);
        patientBST.insert(p105);
        patientBST.insert(p102);
        patientBST.insert(p104);

        emergencyQueue.enqueue(p103);
        emergencyQueue.enqueue(p101);

        treatmentStack.push(new TreatmentRecord(
                101, "Fathima", "Dr. Perera", "Medication", "2026-09-05"
        ));

        p103.getVisitHistory().addVisit(new Visit(
                1, "2026-08-20", "Dr. Perera", "Chest pain", "Medication"
        ));
        p103.getVisitHistory().addVisit(new Visit(
                2, "2026-08-28", "Dr. Silva", "Follow-up", "Review"
        ));
    }
}
