# Cyber Crime Complaint Management System (CLI)

A command-line based Java application designed to streamline the reporting, tracking, and management of cybercrime incidents. This application runs entirely in your terminal, providing an interactive, text-based interface.

## Features

- **Interactive Menu:** Navigate through an easy-to-use terminal menu.
- **Complaint Registration:** Report incidents such as phishing, financial fraud, and identity theft.
- **Automated Case IDs:** Generates standard tracking IDs (e.g., `CYB1001`) automatically upon registration.
- **Lifecycle Tracking:** Search for cases by ID and update their status (Registered &rarr; Under Review &rarr; Investigation &rarr; Resolved).
- **System Reports:** View total complaints, open cases, resolved cases, and the most reported crime categories.
- **Demo Dataset:** Pre-populated with 10 realistic test cases for immediate testing and demonstration.

## Tech Stack

- **Backend:** Java 17, Spring Boot (Command Line Runner), Spring Data JPA
- **Database:** H2 In-Memory Relational Database
- **Build Tool:** Maven Wrapper (`mvnw`)

## Prerequisites

- **Java Development Kit (JDK) 17** or higher installed.

## Getting Started

1. **Clone the repository**:
   ```bash
   git clone https://github.com/srishtirai-03/cyber-crime-detection.git
   cd cyber-crime-detection
   ```

2. **Run the Application Locally:**
   Use the included Maven wrapper to start the interactive application. You do not need to install Maven globally.
   
   On Windows:
   ```cmd
   .\mvnw.cmd spring-boot:run
   ```
   On macOS/Linux:
   ```bash
   ./mvnw spring-boot:run
   ```

3. **Interact with the CLI:**
   Once the application starts, you will see a main menu in your terminal. Follow the on-screen prompts to register complaints, view reports, or update statuses.

## Running with Docker

You can also run the CLI application inside a Docker container. 

1. **Build the image:**
   ```bash
   docker build -t cyber-crime-cli .
   ```

2. **Run the container interactively:**
   ```bash
   docker run -it cyber-crime-cli
   ```

## Project Structure

- `src/main/java/.../model/`: Contains the `Complaint` JPA entity.
- `src/main/java/.../repository/`: Spring Data JPA interface for database queries.
- `src/main/java/.../service/`: Business logic, stats calculation, and ID generation.
- `src/main/java/.../cli/`: Contains the `CliRunner` which drives the interactive terminal menu.
- `src/main/resources/data.sql`: The script that injects the demo dataset on startup.

## License
This project was developed for educational purposes.
