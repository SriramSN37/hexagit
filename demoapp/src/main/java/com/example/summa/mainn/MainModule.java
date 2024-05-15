package com.example.summa.mainn;

import com.example.summa.dao.HospitalServiceImpl;
import com.example.summa.entity.Appointment;
import com.example.summa.myexceptions.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MainModule {
    public static void main(String[] args) throws PatientNumberNotFoundException {
        HospitalServiceImpl hospitalService = new HospitalServiceImpl();
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("Menu:");
            System.out.println("1. Search by appointment ID");
            System.out.println("2. Search by patient ID");
            System.out.println("3. Search by doctor ID");
            System.out.println("4. Schedule an appointment");
            System.out.println("5. Update an appointment");
            System.out.println("6. Cancel an appointment");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter appointment ID: ");
                        int appointmentId = scanner.nextInt();
                        Appointment appointmentById = hospitalService.getAppointmentById(appointmentId);
                        System.out.println("Appointment by ID: " + appointmentById);
                        break;
                    case 2:
                        System.out.print("Enter patient ID: ");
                        int patientId = scanner.nextInt();
                        if(patientId < 4){
                            
                                List <Appointment> appointmentsForPatient = hospitalService.getAppointmentsForPatient(patientId);
                                System.out.println("Appointments for Patient: " + appointmentsForPatient);
                        }
                        else{
                            throw new PatientNumberNotFoundException("enter a valid patient Id");
                        }
                        break;
                    case 3:
                        System.out.print("Enter doctor ID: ");
                        int doctorId = scanner.nextInt();
                        List<Appointment> appointmentsForDoctor = hospitalService.getAppointmentsForDoctor(doctorId);
                        System.out.println("Appointments for Doctor: " + appointmentsForDoctor);
                        break;
                    case 4:
                        // Schedule an appointment
                        Appointment newAppointment = new Appointment();
                        scanner.nextLine(); // Consume newline character
                        System.out.print("Enter appointment ID: ");
                        newAppointment.setAppointmentId(scanner.nextInt());
                        System.out.print("Enter patient ID: ");
                        newAppointment.setPatientId(scanner.nextInt());
                        System.out.print("Enter doctor ID: ");
                        newAppointment.setDoctorId(scanner.nextInt());
                        System.out.print("Enter appointment date (YYYY-MM-DD): ");
                        newAppointment.setAppointmentDate(scanner.next());
                        scanner.nextLine(); // Consume newline character
                        System.out.print("Enter appointment description: ");
                        newAppointment.setDescription(scanner.nextLine());
                        boolean isScheduled = hospitalService.scheduleAppointment(newAppointment);
                        System.out.println("Appointment scheduled: " + isScheduled);
                        break;
                    case 5:
                        // Update an appointment
                        System.out.print("Enter appointment ID to update: ");
                        int appointmentIdToUpdate = scanner.nextInt();
                        Appointment appointmentToUpdate = hospitalService.getAppointmentById(appointmentIdToUpdate);
                        if (appointmentToUpdate != null) {
                            scanner.nextLine(); // Consume newline character
                            System.out.print("Enter new description: ");
                            appointmentToUpdate.setDescription(scanner.nextLine());
                            boolean isUpdated = hospitalService.updateAppointment(appointmentToUpdate);
                            System.out.println("Appointment updated: " + isUpdated);
                        } else {
                            System.out.println("Appointment not found.");
                        }
                        break;
                    case 6:
                        // Cancel an appointment
                        System.out.print("Enter appointment ID to cancel: ");
                        int appointmentIdToCancel = scanner.nextInt();
                        boolean isCancelled = hospitalService.cancelAppointment(appointmentIdToCancel);
                        System.out.println("Appointment cancelled: " + isCancelled);
                        break;
                    case 7:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 7.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle SQLException appropriately
            }
        } while (choice != 7);

        // Close scanner
        scanner.close();
    }
}


