# Cyber Crime Complaint Management System

A full-stack web application designed to streamline the reporting, tracking, and management of cybercrime incidents. Upgraded from a simple CLI tool, this modern web application provides a visually stunning, glassmorphic UI and a robust RESTful backend.

## Features

- **Real-time Dashboard:** View total complaints, open cases, resolved cases, and the most reported crime categories.
- **Complaint Registration:** User-friendly form to report phishing, financial fraud, identity theft, and more.
- **Automated Case IDs:** Generates standard tracking IDs (e.g., `CYB1001`) automatically upon registration.
- **Lifecycle Tracking:** Search for cases by ID and update their status (Registered &rarr; Under Review &rarr; Investigation &rarr; Resolved).
- **Demo Dataset:** Pre-populated with 10 realistic test cases for immediate testing and demonstration.
- **Premium UI/UX:** Built with a dark-mode cyber aesthetic featuring glassmorphism and subtle animations.

## Tech Stack

- **Backend:** Java 17, Spring Boot 3.3.x, Spring Data JPA, Hibernate
- **Database:** H2 In-Memory Relational Database
- **Frontend:** Vanilla HTML5, CSS3, JavaScript (ES6)
- **Build Tool:** Maven Wrapper (`mvnw`)

## Prerequisites

- **Java Development Kit (JDK) 17** or higher installed.

## Getting Started

1. **Clone the repository** (if you haven't already):
   ```bash
   git clone https://github.com/srishtirai-03/cyber-crime-detection.git
   cd cyber-crime-detection
   ```

2. **Run the Application:**
   Use the included Maven wrapper to start the Spring Boot server. You do not need to install Maven globally.
   
   On Windows:
   ```cmd
   .\mvnw.cmd spring-boot:run
   ```
   On macOS/Linux:
   ```bash
   ./mvnw spring-boot:run
   ```

3. **Access the Web Interface:**
   Open your browser and navigate to: [http://localhost:8080](http://localhost:8080)

4. **Access the Database Console (Optional):**
   - URL: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
   - JDBC URL: `jdbc:h2:mem:cybercrime_db`
   - Username: `sa`
   - Password: *(leave blank)*

## Live Demo

A live version of this application can be hosted temporarily using LocalTunnel. 
If the tunnel is currently active, it can be accessed here:
**[https://cyber-shield-demo.loca.lt](https://cyber-shield-demo.loca.lt)**

*(Note: When accessing the live link, you may be prompted to enter your public IP address for security purposes.)*

## Project Structure

- `src/main/java/.../model/`: Contains the `Complaint` JPA entity.
- `src/main/java/.../repository/`: Spring Data JPA interface for database queries.
- `src/main/java/.../service/`: Business logic, stats calculation, and ID generation.
- `src/main/java/.../controller/`: REST APIs handling frontend requests.
- `src/main/resources/static/`: Contains the `index.html`, `style.css`, and `app.js` for the frontend SPA.
- `src/main/resources/data.sql`: The script that injects the demo dataset on startup.

## License
This project was developed for educational purposes.
