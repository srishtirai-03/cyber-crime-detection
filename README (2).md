# Cyber Crime Complaint Management System

A Java-based command-line application to register, search, track, and report on cybercrime complaints. Built as an academic project for the **Programming in Java** course.

> **Disclaimer:** This is an educational prototype only. It is not an official cybercrime reporting or law-enforcement platform.

---

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Compile](#compile)
  - [Run](#run)
- [Usage](#usage)
  - [Main Menu](#main-menu)
  - [Registering a Complaint](#registering-a-complaint)
  - [Searching a Complaint](#searching-a-complaint)
  - [Updating Status](#updating-status)
  - [Generating a Report](#generating-a-report)
- [Complaint Status Lifecycle](#complaint-status-lifecycle)
- [Data Validation](#data-validation)
- [Testing](#testing)
- [Limitations](#limitations)
- [Security Considerations](#security-considerations)
- [Future Enhancements](#future-enhancements)
- [References](#references)
- [Declaration](#declaration)

---

## Overview

The Cyber Crime Complaint Management System provides a simple, structured way to manage cybercrime complaint records — registration, classification, priority assignment, search, status tracking, and basic reporting — all through a terminal interface, with persistent local file storage.

## Features

- Complaint registration with auto-generated unique complaint IDs
- Cybercrime type classification (Phishing, Online Financial Fraud, Identity Theft, Cyberbullying, Account Hacking, Online Harassment, Malware, Other)
- Priority assignment (`HIGH`, `MEDIUM`, `LOW`)
- Search by Complaint ID, complainant name, crime type, or status
- Status tracking through the complaint lifecycle
- Basic statistical report generation
- Persistent local file-based storage
- Input validation and exception handling

## Tech Stack

- **Language:** Java
- **Core Java features used:** OOP (classes, encapsulation, inheritance, polymorphism, abstraction), Collections Framework (`ArrayList`, `HashMap`), Exception Handling, File I/O, String Handling, `java.time` (Date/Time API), Interfaces
- **Storage:** Local file-based storage (no external database required)

## Project Structure

```
CyberCrimeComplaintSystem/
│
├── src/
│   ├── Main.java
│   ├── Complaint.java
│   ├── Complainant.java
│   ├── Investigator.java
│   ├── ComplaintManager.java
│   ├── FileManager.java
│   ├── ReportGenerator.java
│   ├── InputValidator.java
│   └── exceptions/
│       └── InvalidComplaintException.java
│
├── data/
│   └── complaints.txt
│
├── README.md
│
└── Project_Report.pdf
```

> The exact structure should match your final implementation.

## Getting Started

### Prerequisites

- JDK 8 or later installed
- Verify your installation:

  ```bash
  java -version
  javac -version
  ```

### Installation

Clone the repository:

```bash
git clone <YOUR-GITHUB-REPOSITORY-URL>
cd CyberCrimeComplaintSystem
```

### Compile

For a simple source structure:

```bash
javac -d out src/*.java
```

### Run

```bash
java -cp out Main
```

> Update these commands if your final package/folder structure differs.

## Usage

### Main Menu

```
========================================
   CYBER CRIME COMPLAINT MANAGEMENT
========================================

1. Register Complaint
2. View All Complaints
3. Search Complaint
4. Update Complaint Status
5. Assign Priority
6. Generate Report
7. Exit

Enter your choice:
```

### Registering a Complaint

```
===== REGISTER COMPLAINT =====

Enter complainant name: Rahul Sharma
Enter contact number: 9876543210

Select Crime Type:
1. Phishing
2. Online Financial Fraud
3. Identity Theft
4. Cyberbullying
5. Account Hacking
6. Other

Enter choice: 2

Enter incident description:
Received fraudulent payment link.

Enter financial loss: 5000

Complaint Registered Successfully!

Complaint ID : CYB1001
Priority     : HIGH
Status       : REGISTERED
```

### Searching a Complaint

```
===== SEARCH COMPLAINT =====

Enter Complaint ID: CYB1001

Complaint Found

Complaint ID : CYB1001
Name         : Rahul Sharma
Crime Type   : Online Financial Fraud
Loss         : ₹5000
Priority     : HIGH
Status       : REGISTERED
```

### Updating Status

```
Enter Complaint ID: CYB1001

Current Status: REGISTERED

Select New Status:
1. UNDER REVIEW
2. ASSIGNED
3. UNDER INVESTIGATION
4. RESOLVED
5. CLOSED

Enter choice: 3

Status updated successfully.
```

### Generating a Report

```
========== COMPLAINT REPORT ==========

Total Complaints       : 25
Open Complaints        : 12
Resolved Complaints    : 10
High Priority Cases    : 7

Most Reported Category : Online Fraud
=======================================
```

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
| Empty name                | Empty input       | Validation error      |
| Invalid menu               | 10                | Error message          |
| Search existing ID         | CYB1001           | Complaint displayed   |
| Search invalid ID          | CYB9999           | "Complaint not found" |
| Update status               | Valid ID          | Status updated        |
| Negative financial loss    | -500              | Validation error      |
| View complaints              | Multiple records  | All records displayed |
| Generate report               | Existing records  | Statistics displayed  |

## Limitations

- Command-line only; no graphical interface
- Uses local file storage instead of a centralized database
- Does not connect to law-enforcement systems
- Does not independently verify complaint authenticity
- Does not perform forensic investigation
- Authentication and role-based security are limited in this basic version

## Security Considerations

Complaint records may contain sensitive information. A production-grade version of this system should add:

- Authentication and authorization
- Password protection and encryption
- Secure database storage
- Access logging and input sanitization
- Backup and recovery
- Protection of personally identifiable information (PII)

This academic project focuses on Java implementation and complaint-record management, not production-level cybersecurity infrastructure.

## Future Enhancements

- **Database integration** — replace local file storage with MySQL or similar
- **Authentication** — separate accounts for Administrator, Investigator, and Complainant roles
- **Web application** — connect this Java backend to a web-based interface
- **Email notifications** — notify users of complaint status updates
- **Advanced analytics** — charts and statistics on complaint trends
- **Evidence management** — metadata for submitted digital evidence, with appropriate security controls
- **Audit logs** — record important operations for accountability

## References

1. [Oracle Java SE Documentation](https://docs.oracle.com/en/java/)
2. [Oracle Java Tutorials — Object-Oriented Programming Concepts](https://docs.oracle.com/javase/tutorial/java/concepts/)
3. [Oracle Java Documentation — Collections Framework](https://docs.oracle.com/javase/tutorial/collections/)
4. [Oracle Java Documentation — Java I/O and File Handling](https://docs.oracle.com/javase/tutorial/essential/io/)
5. [Oracle Java Documentation — Date and Time API](https://docs.oracle.com/javase/tutorial/datetime/)

## Declaration

This project, **"Cyber Crime Complaint Management System,"** was developed as part of the Programming in Java course evaluation. The implementation, testing, and documentation represent the work carried out for this academic project. It is intended for educational purposes only and does not represent an official cybercrime reporting or law-enforcement platform.
