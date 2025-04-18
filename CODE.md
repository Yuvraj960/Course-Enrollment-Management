# Student Management System - Java

## Table of Contents
- [Student Management System - Java](#student-management-system---java)
  - [Table of Contents](#table-of-contents)
  - [Overview](#overview)
  - [Project Structure](#project-structure)
  - [Detailed Component Breakdown](#detailed-component-breakdown)
    - [Model Classes](#model-classes)
      - [1. Student.java](#1-studentjava)
      - [2. Course.java](#2-coursejava)
      - [3. Enrollment.java](#3-enrollmentjava)
    - [Service Classes](#service-classes)
      - [1. StudentService.java](#1-studentservicejava)
      - [2. CourseService.java](#2-courseservicejava)
      - [3. EnrollmentService.java](#3-enrollmentservicejava)
    - [Utility Classes](#utility-classes)
      - [1. FileUtil.java](#1-fileutiljava)
      - [2. InputValidator.java](#2-inputvalidatorjava)
      - [3. DateUtil.java](#3-dateutiljava)
    - [Main Application](#main-application)
      - [Main.java](#mainjava)
  - [Data Flow](#data-flow)
  - [Key Features](#key-features)
  - [How to Run](#how-to-run)
  - [Future Enhancements](#future-enhancements)

## Overview

This Student Management System is a comprehensive Java application that manages student records, courses, and enrollments through a command-line interface. The system features:

- **CRUD operations** for students, courses, and enrollments
- **CSV-based data persistence** (human-readable format)
- **Robust input validation**
- **Search and sorting capabilities**
- **Modular design** following OOP principles

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   ├── model/          # Data model classes
│   │   ├── service/        # Business logic handlers
│   │   ├── util/           # Utility classes
│   │   ├── Main.java       # Entry point
│   ├── resources/          # Data storage files
```

## Detailed Component Breakdown

### Model Classes

#### 1. Student.java
- **Purpose**: Represents student entities
- **Fields**:
  - `studentId`: Unique identifier (String)
  - `firstName`, `lastName`: Student names (String)
  - `email`: Validated email format (String)
  - `phoneNumber`: Validated phone format (String)
- **Key Methods**:
  - `toString()`: Formats student data for tabular display
  - Constructor validates field presence (non-null)

#### 2. Course.java
- **Purpose**: Represents course offerings
- **Fields**:
  - `courseCode`: Unique identifier (String)
  - `courseName`: Descriptive title (String)
  - `credits`: Numeric value (int)
  - `instructor`: Faculty name (String)
- **Key Methods**:
  - `toString()`: Formats course data for display
  - Constructor enforces positive credit values

#### 3. Enrollment.java
- **Purpose**: Links students to courses
- **Fields**:
  - `enrollmentId`: Composite key (studentId-courseCode)
  - `studentId`: References Student (String)
  - `courseCode`: References Course (String)
  - `enrollmentDate`: Timestamp (java.util.Date)
- **Key Methods**:
  - Generates IDs in format "S001-CS101"
  - Stores dates as milliseconds for CSV compatibility

### Service Classes

#### 1. StudentService.java
- **Core Responsibilities**:
  - Manages in-memory student data (HashMap)
  - Coordinates with FileUtil for persistence
  - Implements search/sort operations
- **Key Features**:
  - Auto-generates student IDs
  - Validates email/phone formats
  - Case-insensitive search across all fields
  - Multiple sort options (ID, name)

#### 2. CourseService.java
- **Core Responsibilities**:
  - Maintains course catalog
  - Handles credit validation (1-10 range)
  - Manages course-student relationships
- **Key Features**:
  - Prevents duplicate course codes
  - Instructor assignment tracking
  - Credit-based sorting

#### 3. EnrollmentService.java
- **Core Responsibilities**:
  - Manages student-course relationships
  - Handles enrollment date tracking
  - Validates student/course existence
- **Key Features**:
  - Prevents duplicate enrollments
  - Timestamp recording
  - Student/course lookup capabilities

### Utility Classes

#### 1. FileUtil.java
- **Core Functions**:
  - CSV serialization/deserialization
  - Special character handling
  - Type-specific formatting
- **Key Algorithms**:
  - Custom CSV parser handles quoted values
  - Escape/unescape for commas in fields
  - Millisecond date conversion
- **File Management**:
  - Auto-creates missing resource files
  - Maintains human-readable headers

#### 2. InputValidator.java
- **Validation Rules**:
  - Email regex: `^[\w-.]+@([\w-]+\.)+[\w-]{2,4}$`
  - Phone regex: `^\+?[0-9]{10,15}$`
  - Non-empty string checks
  - Positive credit values

#### 3. DateUtil.java
- **Functionality**:
  - Standardized date formatting
  - Thread-safe SimpleDateFormat
  - Display format: "yyyy-MM-dd HH:mm:ss"

### Main Application

#### Main.java
- **Control Flow**:
  1. Initializes all services
  2. Loads existing data
  3. Presents main menu
  4. Routes to sub-menus
- **UI Features**:
  - Input validation loops
  - Tabular data display
  - Context-sensitive help
  - Pagination support

## Data Flow

1. **Startup Sequence**:
   - Services initialize → FileUtil loads CSV → Data populates HashMaps

2. **CRUD Operations**:
   - User action → Service validation → HashMap update → CSV sync

3. **Search Flow**:
   - Keyword entered → Case-insensitive filter → Results sorting → Display

4. **Persistence Cycle**:
   - In-memory changes → CSV serialization → File update

## Key Features

1. **Data Integrity**:
   - Automatic ID generation
   - Referential integrity checks
   - Transaction-style file writes

2. **Search Capabilities**:
   - Multi-field search
   - Partial matching
   - Combined sort/filter

3. **User Experience**:
   - Context-aware menus
   - Input validation with retry
   - Clear error messages

4. **CSV Implementation**:
   - Proper quoting/escaping
   - Type preservation
   - Header maintenance

## How to Run

1. **Compilation**:
   ```bash
   javac -d bin src/main/java/model/*.java \
              src/main/java/service/*.java \
              src/main/java/util/*.java \
              src/main/java/Main.java
   ```

2. **Execution**:
   ```bash
   java -cp bin Main
   ```

3. **First Run**:
   - Auto-creates resource files
   - Generates sample data if empty

4. **File Locations**:
   - Data stored in `src/main/resources/`
   - CSV files editable externally

## Future Enhancements

1. **Advanced Features**:
   - Grade management
   - Attendance tracking
   - Course prerequisites

2. **Technical Improvements**:
   - JSON serialization option
   - Database backend
   - GUI interface

3. **Operational Enhancements**:
   - Data import/export
   - Bulk operations
   - Audit logging

This documentation provides comprehensive insight into the system's architecture and implementation details while maintaining clarity for both developers and end-users.