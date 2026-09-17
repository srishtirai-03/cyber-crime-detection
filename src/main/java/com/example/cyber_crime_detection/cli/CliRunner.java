package com.example.cyber_crime_detection.cli;

import com.example.cyber_crime_detection.model.Complaint;
import com.example.cyber_crime_detection.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import org.springframework.context.annotation.Profile;

@Component
@Profile("!test")
public class CliRunner implements CommandLineRunner {

    @Autowired
    private ComplaintService complaintService;

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        System.out.println("=================================================");
        System.out.println("  Welcome to Cyber Crime Detection System (CLI)  ");
        System.out.println("=================================================");

        while (!exit) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Register a new Complaint");
            System.out.println("2. View all Complaints");
            System.out.println("3. Check Complaint Status by ID");
            System.out.println("4. Update Complaint Status");
            System.out.println("5. View System Report");
            System.out.println("6. Exit");
            System.out.print("Enter your choice (1-6): ");

            String choiceStr = scanner.nextLine();
            int choice = -1;
            try {
                choice = Integer.parseInt(choiceStr.trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 6.");
                continue;
            }

            switch (choice) {
                case 1:
                    registerComplaint(scanner);
                    break;
                case 2:
                    viewAllComplaints();
                    break;
                case 3:
                    checkStatusById(scanner);
                    break;
                case 4:
                    updateComplaintStatus(scanner);
                    break;
                case 5:
                    viewReport();
                    break;
                case 6:
                    exit = true;
                    System.out.println("Exiting the system. Stay safe!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        }
        
        System.exit(0);
    }

    private void registerComplaint(Scanner scanner) {
        System.out.println("\n--- Register New Complaint ---");
        Complaint complaint = new Complaint();

        System.out.print("Enter Complainant Name: ");
        complaint.setComplainantName(scanner.nextLine());

        System.out.print("Enter Contact Number: ");
        complaint.setContactNumber(scanner.nextLine());

        System.out.print("Enter Crime Type (e.g., PHISHING, FRAUD, HARASSMENT): ");
        complaint.setCrimeType(scanner.nextLine());

        System.out.print("Enter Description: ");
        complaint.setDescription(scanner.nextLine());

        System.out.print("Enter Financial Loss Amount: ");
        try {
            double loss = Double.parseDouble(scanner.nextLine().trim());
            complaint.setFinancialLoss(loss);
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount. Setting financial loss to 0.0.");
            complaint.setFinancialLoss(0.0);
        }

        System.out.print("Enter Priority (LOW, MEDIUM, HIGH): ");
        complaint.setPriority(scanner.nextLine().toUpperCase());

        Complaint saved = complaintService.registerComplaint(complaint);
        System.out.println("Complaint registered successfully!");
        System.out.println("Your Complaint ID is: " + saved.getGeneratedId());
    }

    private void viewAllComplaints() {
        System.out.println("\n--- All Complaints ---");
        List<Complaint> complaints = complaintService.getAllComplaints();
        if (complaints.isEmpty()) {
            System.out.println("No complaints found.");
        } else {
            for (Complaint c : complaints) {
                System.out.printf("[%s] %s | Type: %s | Status: %s | Priority: %s\n",
                        c.getGeneratedId(), c.getComplainantName(), c.getCrimeType(), c.getStatus(), c.getPriority());
            }
        }
    }

    private void checkStatusById(Scanner scanner) {
        System.out.print("\nEnter Complaint ID (e.g., CYB1001): ");
        String id = scanner.nextLine().trim();
        Optional<Complaint> opt = complaintService.getComplaintByGeneratedId(id);
        
        if (opt.isPresent()) {
            Complaint c = opt.get();
            System.out.println("\n--- Complaint Details ---");
            System.out.println("ID: " + c.getGeneratedId());
            System.out.println("Name: " + c.getComplainantName());
            System.out.println("Contact: " + c.getContactNumber());
            System.out.println("Crime Type: " + c.getCrimeType());
            System.out.println("Description: " + c.getDescription());
            System.out.println("Financial Loss: " + c.getFinancialLoss());
            System.out.println("Priority: " + c.getPriority());
            System.out.println("Status: " + c.getStatus());
            System.out.println("Date: " + c.getCreatedAt());
        } else {
            System.out.println("Complaint not found with ID: " + id);
        }
    }

    private void updateComplaintStatus(Scanner scanner) {
        System.out.print("\nEnter Complaint ID to update (e.g., CYB1001): ");
        String id = scanner.nextLine().trim();
        
        System.out.print("Enter new status (e.g., IN_PROGRESS, RESOLVED, CLOSED): ");
        String newStatus = scanner.nextLine().trim().toUpperCase();

        try {
            Complaint updated = complaintService.updateStatus(id, newStatus);
            System.out.println("Complaint status updated successfully! Current status: " + updated.getStatus());
        } catch (RuntimeException e) {
            System.out.println("Failed to update status. Error: " + e.getMessage());
        }
    }

    private void viewReport() {
        System.out.println("\n--- System Report ---");
        Map<String, Object> report = complaintService.getReport();
        System.out.println("Total Complaints: " + report.get("totalComplaints"));
        System.out.println("Open Complaints: " + report.get("openComplaints"));
        System.out.println("Resolved Complaints: " + report.get("resolvedComplaints"));
        System.out.println("High Priority Cases: " + report.get("highPriorityCases"));
        System.out.println("Most Reported Category: " + report.get("mostReportedCategory"));
    }
}
