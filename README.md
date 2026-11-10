# 💉 VaxiCare — Smart Clinical Vaccination Management System
> **KTU S3 Computer Science & Engineering — Java OOP Mini Project**

A comprehensive, full-stack clinical immunization management platform designed to automate multi-dose vaccine scheduling, monitor cold-chain inventory, triage adverse side-effects (AEFI), and provide an interactive real-time patient and hospital portal.

🌐 **Live Web Demo**: [https://group-5-oop-project.github.io/vaxicare-ktu-java/](https://group-5-oop-project.github.io/vaxicare-ktu-java/)  
📂 **GitHub Repository**: [https://github.com/Group-5-oop-project/vaxicare-ktu-java](https://github.com/Group-5-oop-project/vaxicare-ktu-java)

---

## 👥 Project Team (Group 5)
| Sl No. | Name | Register Number / Roll No. | Contribution / Role |
|:---:|:---|:---|:---|
| 1 | **ARYA PRAKASH** | JEC25CS061 | Architecture & Full-Stack Integration |
| 2 | **AMRUTHAVARSHINI KM** | JEC25CS037 | Core Java OOP & Business Logic |
| 3 | **Aarcha BS** | JEC25CS005 | Web UI/UX & Responsive Front-End |
| 4 | **Ashline Mary Rajesh** | JEC25CS064 | Database Persistence & Testing |

---

## 📌 About the Project

**VaxiCare** is a clinical vaccination management platform designed to help users maintain their vaccination records, manage appointment slots, track upcoming vaccinations, and receive reminders for due or overdue vaccinations.

The project demonstrates the practical application of **Object-Oriented Programming (OOP) in Java**, along with database connectivity (JDBC), REST API persistence, and modular software design.

---

## 🎯 Objectives

* Maintain and manage clinical vaccination records
* Book and update vaccination appointments
* Search and view patient vaccination history
* Calculate upcoming vaccination due dates and enforce clinical interval gaps
* Multithreaded emergency triage queue for adverse effects (AEFI)
* Generate digitally signed XML & PDF vaccination certificates
* Hospital / Admin Inoculation desk for medical officers to verify and complete appointments
* Demonstrate core OOP concepts through a real-world clinical application

---

## 🛠️ Tech Stack

| Technology | Purpose |
|:---|:---|
| ☕ **Java 17 / 21** | Application Development & OOP Business Logic |
| 🗄️ **MySQL / SQLite** | Relational Database & Offline Storage |
| 🔗 **JDBC** | Java–Database Connectivity (PreparedStatement, ACID Transactions) |
| 🌐 **HTML5, CSS3, JS** | Responsive Glassmorphic Patient Portal & Hospital Admin Desk |
| 🗺️ **Leaflet.js** | Interactive City Map with Designated Immunization Hubs |
| 📜 **Java DOM Parser** | XML Catalog Parsing & Dynamic Digital Certificate Generation |
| 🐙 **Git & GitHub** | Collaborative Branching & Version Control |

---

## 🧠 OOP Concepts Demonstrated

The project demonstrates core Java OOP principles required for the KTU S3 Lab Exam & Viva:

* **Classes and Objects**: Model entities (`Patient`, `Vaccine`, `Appointment`, `Admin`, `AefiReport`)
* **Inheritance**: Base class `Person` extended by `Patient` and `Admin`; Base class `Vaccine` extended by `ViralVectorVaccine`, `InactivatedVaccine`, and `ProteinSubunitVaccine`
* **Polymorphism**: Dynamic method dispatch across vaccine formulations for cold-chain storage and dosage gap calculations
* **Abstraction & Interfaces**: Contracts including `Schedulable`, `Alertable`, and `XmlExportable`
* **Encapsulation**: Private fields protected by getters, setters, and clinical validation
* **Custom Exception Handling**: User-defined domain exceptions (`InsufficientGapException`, `SlotUnavailableException`)
* **Multithreading & Thread Synchronization**: Priority triage worker queue (`AefiTriageService`)
* **Database Connectivity**: Robust JDBC data access layer (`AppointmentDAO`, `UserDAO`, `VaccineDAO`, `CentreDAO`, `AefiDAO`)

---

## 💻 How to Run the Project

### Option A: 1-Click Launch (Web Portal & REST Server)
1. Double-click **`start-server.bat`** in the project directory.
2. The server starts on `http://localhost:8080/` and opens in your default browser.
3. Access:
   * **Patient Portal**: `http://localhost:8080/`
   * **Hospital / Admin Desk**: `http://localhost:8080/admin.html` (Demo login: `admin` / `admin123`)

### Option B: Core Java Console & KTU Viva Verification Suite
1. Open the project in VS Code / IntelliJ IDEA.
2. Run `src/main/java/com/vaxicare/MainApp.java`.
3. Choose **Option 7** to run the complete automated KTU OOP verification suite.

---

## 📚 Academic Project Information

* **Course**: Object-Oriented Programming (CST 205 / CSL 203)
* **Degree**: B.Tech in Computer Science & Engineering (Semester 3)
* **University**: APJ Abdul Kalam Technological University (KTU)
* **Institution**: Jyothi Engineering College, Cheruthuruthy, Thrissur

---

⭐ *Built with Java, teamwork, and a little bit of debugging pain.*
