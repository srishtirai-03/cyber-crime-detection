# Cyber Crime Complaint Management System

A Java-based Command-Line Interface (CLI) application built with **Spring Boot** to register, search, track, and report on cybercrime complaints. Built as an academic project for the **Programming in Java** course.

> **Disclaimer:** This is an educational prototype only. It is not an official cybercrime reporting or law-enforcement platform.

---

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Complaint Status Lifecycle](#complaint-status-lifecycle)
- [Data Validation](#data-validation)
- [Testing](#testing)
- [Limitations](#limitations)
- [Security Considerations](#security-considerations)
- [References](#references)
- [Declaration](#declaration)

---

## Overview

The Cyber Crime Complaint Management System provides a structured way to manage cybercrime complaint records — registration, classification, priority assignment, search, status tracking, and basic reporting — all through an interactive terminal interface, utilizing an embedded H2 relational database.

## Features

- **Interactive Menu:** Navigate through an easy-to-use terminal menu.
- **Complaint Registration:** Report incidents such as phishing, financial fraud, and identity theft.
- **Automated Case IDs:** Generates standard tracking IDs (e.g., `CYB1001`) automatically upon registration.
- **Lifecycle Tracking:** Search for cases by ID and update their status.
- **System Reports:** View total complaints, open cases, resolved cases, and the most reported crime categories.
- **Demo Dataset:** Pre-populated with realistic test cases for immediate testing and demonstration.

## Tech Stack

- **Framework:** Spring Boot 3.3.x (Java 17) using `CommandLineRunner`
- **Data Access:** Spring Data JPA / Hibernate
- **Database:** H2 In-Memory Relational Database
- **Build Tool:** Maven Wrapper (`mvnw`)

## Project Structure

- `src/main/java/.../model/`: Contains the `Complaint` JPA entity.
- `src/main/java/.../repository/`: Spring Data JPA interface for database queries.
- `src/main/java/.../service/`: Business logic, stats calculation, and ID generation.
- `src/main/java/.../cli/`: Contains the `CliRunner` which drives the interactive terminal menu.
- `src/main/resources/data.sql`: The script that injects the demo dataset on startup.

## Getting Started

### Prerequisites

- **Java Development Kit (JDK) 17** or higher installed.

### Installation & Execution

1. **Clone the repository**:
   ```bash
   git clone https://github.com/srishtirai-03/cyber-crime-detection.git
   cd cyber-crime-detection
   ```

2. **Run the Application Locally:**
   Use the included Maven wrapper to start the interactive application.
   
   On Windows:
   ```cmd
   .\mvnw.cmd spring-boot:run
   ```
   On macOS/Linux:
   ```bash
   ./mvnw spring-boot:run
   ```

3. **Running with Docker (Optional):**
   ```bash
   docker build -t cyber-crime-cli .
   docker run -it cyber-crime-cli
   ```

## Usage

Once the application starts, you will see a main menu in your terminal. 

### Main Menu
```
=================================================
  Welcome to Cyber Crime Detection System (CLI)  
=================================================

--- Main Menu ---
1. Register a new Complaint
2. View all Complaints
3. Check Complaint Status by ID
4. Update Complaint Status
5. View System Report
6. Exit
Enter your choice (1-6): 
```

Follow the on-screen prompts to register complaints, view reports, or update statuses.

## Complaint Status Lifecycle

```
REGISTERED
     ↓
UNDER REVIEW
     ↓
ASSIGNED
     ↓
UNDER INVESTIGATION
     ↓
RESOLVED / CLOSED
```

## Data Validation

| Field            | Rule                                        |
|------------------|----------------------------------------------|
| Name             | Must not be empty                            |
| Phone number     | Must contain a valid number of digits        |
| Financial loss   | Must be a non-negative numeric value         |
| Complaint type   | Must be selected from the available categories |
| Menu choice      | Must be within the available menu options    |

## Testing

| Test Case               | Input             | Expected Result       |
|--------------------------|-------------------|------------------------|
| Register complaint       | Valid details     | Complaint created     |
| Invalid menu             | 10                | Error message          |
| Search existing ID       | CYB1001           | Complaint displayed   |
| Search invalid ID        | CYB9999           | "Complaint not found" |
| Update status            | Valid ID          | Status updated        |
| Generate report          | Existing records  | Statistics displayed  |

## Limitations

- Command-line only; no graphical interface.
- Uses an in-memory database which resets on application restart.
- Does not connect to law-enforcement systems or verify complaint authenticity.
- Authentication and role-based security are not implemented in this basic version.

## Security Considerations

Complaint records may contain sensitive information. A production-grade version of this system should add:

- Authentication and authorization
- Password protection and encryption
- Secure database storage (e.g., PostgreSQL or MySQL)
- Access logging and input sanitization

This academic project focuses on Spring Boot CLI implementation and complaint-record management, not production-level cybersecurity infrastructure.

## References

1. [Spring Boot Documentation](https://spring.io/projects/spring-boot)
2. [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
3. [Oracle Java SE Documentation](https://docs.oracle.com/en/java/)

## Declaration

This project, **"Cyber Crime Complaint Management System,"** was developed as part of the Programming in Java course evaluation. The implementation, testing, and documentation represent the work carried out for this academic project. It is intended for educational purposes only and does not represent an official cybercrime reporting or law-enforcement platform.
