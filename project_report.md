# Project Report: Cyber Crime Complaint Management System

## 1. Introduction
The **Cyber Crime Complaint Management System** is a comprehensive software solution designed to streamline the reporting, tracking, and management of cybercrime incidents. Originally conceptualized as a command-line interface (CLI) application, this project has been significantly upgraded into a **full-stack modern web application**. 

This transition provides a highly accessible, user-friendly, and visually appealing platform for registering cybercrime complaints (such as Phishing, Financial Fraud, and Identity Theft) while allowing administrators to track and update the lifecycle of each case efficiently.

## 2. Objectives
*   **Accessibility:** To provide an easy-to-use web interface for users to report cybercrimes without needing technical CLI knowledge.
*   **Case Tracking:** To enable the generation of unique, standardized complaint IDs (e.g., `CYB1001`) for seamless case tracking.
*   **Data Persistence:** To migrate from flat-file storage to a relational database for robust data integrity and querying.
*   **Analytics:** To provide a real-time dashboard reflecting system statistics, priority cases, and resolution metrics.

## 3. Technology Stack
The application employs a modern, lightweight, and efficient technology stack:

### Backend
*   **Framework:** Spring Boot 3.3.x (Java 17)
*   **Data Access:** Spring Data JPA / Hibernate
*   **Database:** H2 In-Memory Relational Database (Configured for easy testing and schema auto-generation)
*   **Build Tool:** Maven

### Frontend
*   **Structure & Logic:** Vanilla HTML5 and JavaScript (ES6)
*   **Styling:** Vanilla CSS3
*   **Design Paradigm:** Premium Dark Mode, Glassmorphism, Micro-animations, and Responsive Web Design
*   **Typography:** Google Fonts (Inter)

## 4. System Architecture
The system follows a standard **Model-View-Controller (MVC)** pattern via RESTful APIs:
1.  **Frontend (View):** A Single Page Application (SPA) located in the `src/main/resources/static` directory. It uses JavaScript `fetch()` to communicate asynchronously with the backend.
2.  **Controller Layer:** `ComplaintController.java` exposes REST endpoints (GET, POST, PUT) to handle HTTP requests.
3.  **Service Layer:** `ComplaintService.java` encapsulates business logic, including custom ID generation and statistical calculations for the dashboard.
4.  **Repository Layer:** `ComplaintRepository.java` extends `JpaRepository` to interface directly with the H2 Database.
5.  **Model Layer:** `Complaint.java` defines the JPA Entity mapped to the database table.

## 5. Key Features & Modules

### 5.1 Real-time Analytics Dashboard
A dynamic dashboard that provides a bird's-eye view of the system. It calculates and displays:
*   Total Complaints Registered
*   Open / Pending Complaints
*   Resolved Cases
*   High-Priority Incidents
*   The most frequently reported crime category

### 5.2 Complaint Registration
A user-friendly, glassmorphic form allowing victims to submit details of the incident. 
*   **Fields captured:** Name, Contact Number, Crime Type, Priority Level, Financial Loss, and Incident Description.
*   **Automation:** Upon submission, the system automatically tags the complaint with a `REGISTERED` status, records the creation timestamp, and auto-generates a unique `CYBxxxx` ID.

### 5.3 Search and Lifecycle Management
Administrators can search for any case using its unique Complaint ID.
*   **Data Retrieval:** Instantly fetches the full details of the case.
*   **Status Updates:** Allows authorized personnel to update the case status through its lifecycle (`REGISTERED` &rarr; `UNDER REVIEW` &rarr; `ASSIGNED` &rarr; `UNDER INVESTIGATION` &rarr; `RESOLVED` &rarr; `CLOSED`). Visual badges automatically adapt their colors based on the severity of the status.

### 5.4 Demo Dataset Integration
To facilitate immediate demonstration and testing, the system boots with a predefined `data.sql` script. This injects 10 realistic, diverse cybercrime cases into the database upon startup, immediately populating the dashboard and search indexes.

## 6. UI/UX Design Aesthetics
A major focus of the upgrade was creating a "Wow" factor. The UI strictly avoids generic templates, opting instead for:
*   **Deep Cyber Aesthetic:** A deep blue/slate background with subtle radial gradients (`#0b0f19`).
*   **Glassmorphism:** Navigation sidebars and forms utilize semi-transparent backgrounds with background-blur (`backdrop-filter: blur(10px)`), giving a frosted glass effect.
*   **Micro-interactions:** Buttons and cards feature smooth transform scaling and box-shadow glowing effects (`rgba(59, 130, 246, 0.5)`) on hover to encourage user interaction.

## 7. Setup and Execution
The project is entirely self-contained and requires no external database server installations.
1.  Ensure Java 17+ is installed.
2.  Navigate to the project root directory.
3.  Run the application using the Maven wrapper:
    ```bash
    ./mvnw spring-boot:run
    ```
4.  Access the web interface at `http://localhost:8080`.
5.  *(Optional)* Access the database console at `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:cybercrime_db`).

## 8. Conclusion and Future Enhancements
The migration to a Spring Boot Web Application successfully modernizes the Cyber Crime Complaint Management System. It establishes a robust, scalable foundation.

**Proposed Future Enhancements:**
*   **Authentication & Authorization:** Implement Spring Security to separate Complainant and Investigator roles.
*   **File Uploads:** Allow users to upload digital evidence (screenshots, emails, PDFs).
*   **Email Notifications:** Integrate JavaMailSender to notify victims when their case status changes.
*   **Persistent Database:** Swap the H2 in-memory database for PostgreSQL or MySQL for production deployment.
