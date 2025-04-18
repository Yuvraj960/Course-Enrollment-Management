# Course Enrollment Management

![Java](https://img.shields.io/badge/Java-17-blue)
![OOP](https://img.shields.io/badge/OOP-Design-success)

A comprehensive command-line Student-Course Management System built with Java that handles student records, course management, and enrollment tracking with persistent CSV storage.

## Table of Contents
- [Student Management System](#student-management-system)
  - [Table of Contents](#table-of-contents)
  - [Features](#features)
    - [Core Functionality](#core-functionality)
    - [Advanced Features](#advanced-features)
  - [Tech Stack](#tech-stack)
  - [Installation](#installation)
    - [Prerequisites](#prerequisites)
    - [Setup Instructions](#setup-instructions)
  - [Usage](#usage)
    - [Basic Commands](#basic-commands)
  - [Data Structures and Algorithms](#data-structures-and-algorithms)
    - [Implemented Data Structures](#implemented-data-structures)
    - [Key Algorithms](#key-algorithms)
  - [File Structure](#file-structure)

## Features

### Core Functionality
- **Student Management**:
  - Add/update/delete student profiles
  - Search students by ID, name, or email
  - View all students with sorting options

- **Course Management**:
  - Create and modify course offerings
  - Set credit values and instructors
  - Comprehensive course catalog

- **Enrollment System**:
  - Enroll students in courses
  - Drop enrollments
  - View enrollment history

### Advanced Features
- **Data Persistence**: All records saved in human-readable CSV format
- **Input Validation**: Robust validation for emails, phone numbers, etc.
- **Search & Sort**: Flexible searching with multiple sorting options
- **User-Friendly CLI**: Intuitive menu-driven interface

## Tech Stack

**Core Technologies**:
- Java 17
- Object-Oriented Programming
- File I/O Operations

**Design Patterns**:
- Service Layer pattern
- Repository pattern (via FileUtil)
- Singleton pattern (service instances)

## Installation

### Prerequisites
- Java JDK 17 or later
- Maven (for building)

### Setup Instructions
1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/Course-Enrollment-Management.git
   ```

2. Navigate to project directory:
   ```bash
   cd Course-Enrollment-Management
   ```

3. Compile the project:
   ```bash
   javac -d bin src/main/java/**/*.java src/main/java/Main.java
   ```

4. Run the application:
   ```bash
   java -cp bin Main
   ```

## Usage

### Basic Commands
1. **Main Menu**:
   ```
   ===== Student Management System =====
   1. Manage Students
   2. Manage Courses
   3. Manage Enrollments
   4. Exit
   ```

2. **Sample Workflow**:
   - Add students through the student management menu
   - Create courses in the course management section
   - Enroll students in courses via enrollment menu
   - All data automatically saves to CSV files

## Data Structures and Algorithms

### Implemented Data Structures

1. **HashMap**:
   - **Usage**: Primary storage in all Service classes
   - **Purpose**: O(1) access time for student/course lookups
   - **Location**: `StudentService`, `CourseService`, `EnrollmentService`

2. **ArrayList**:
   - **Usage**: Returning search results and sorted lists
   - **Purpose**: Dynamic arrays for flexible data handling
   - **Location**: All service classes' search/sort methods

3. **Custom CSV Parser**:
   - **Usage**: FileUtil's parseCsvLine method
   - **Purpose**: Handle quoted CSV values with commas
   - **Implementation**: State machine with quote tracking

### Key Algorithms

1. **Search Algorithms**:
   - Linear Search (O(n)) for flexible field searching
   - Case-insensitive string matching

2. **Sorting Algorithms**:
   - Collections.sort() with custom Comparators
   - Multiple sort keys (ID, name, date, etc.)

3. **Data Serialization**:
   - Custom CSV formatting with proper escaping
   - Type conversion handling (especially for dates)

4. **Input Validation**:
   - Regex patterns for email/phone validation
   - Boundary checking for numeric inputs

## File Structure

```
Course-Enrollment-Management/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── model/          # Data models
│   │   │   │   ├── Student.java
│   │   │   │   ├── Course.java
│   │   │   │   └── Enrollment.java
│   │   │   ├── service/        # Business logic
│   │   │   │   ├── StudentService.java
│   │   │   │   ├── CourseService.java
│   │   │   │   └── EnrollmentService.java
│   │   │   ├── util/           # Utilities
│   │   │   │   ├── FileUtil.java
│   │   │   │   ├── InputValidator.java
│   │   │   │   └── DateUtil.java
│   │   │   └── Main.java       # Entry point
│   │   └── resources/          # Data storage
│   │       ├── students.txt
│   │       ├── courses.txt
│   │       └── enrollments.txt
├── README.md
└── CODE.md
```
