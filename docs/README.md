# LearnTrack - Student & Course Management System

## Project Description

LearnTrack is a console-based Student & Course Management System built using Core Java. It demonstrates fundamental Java concepts including OOP principles, Collections, exception handling, and clean code practices.

The system allows administrators to:
- Manage students (add, view, update, deactivate/activate)
- Manage courses (add, view, update, deactivate/activate)
- Manage enrollments (enroll students, track enrollment status)

## Technology Stack

- **Language**: Java (JDK 11 or higher)
- **Build Tool**: Command line compilation (No build system required)
- **Data Storage**: In-memory (ArrayList)

## Project Structure

```
learntrack/
├── src/
│   └── com/airtribe/learntrack/
│       ├── Main.java                    # Main application entry point
│       ├── entity/
│       │   ├── Person.java             # Base class
│       │   ├── Student.java            # Student entity
│       │   ├── Course.java             # Course entity
│       │   └── Enrollment.java         # Enrollment entity
│       ├── repository/
│       │   ├── StudentRepository.java  # Data access layer
│       │   ├── CourseRepository.java
│       │   └── EnrollmentRepository.java
│       ├── service/
│       │   ├── StudentService.java     # Business logic layer
│       │   ├── CourseService.java
│       │   └── EnrollmentService.java
│       ├── exception/
│       │   ├── EntityNotFoundException.java
│       │   └── InvalidInputException.java
│       ├── util/
│       │   ├── IdGenerator.java        # ID generation utility
│       │   └── InputValidator.java     # Input validation utility
│       └── constants/
│           └── (For future use)
└── docs/
    ├── Setup_Instructions.md
    ├── JVM_Basics.md
    └── Design_Notes.md
```

## How to Compile and Run

### Prerequisites
- Java Development Kit (JDK) 11 or higher installed
- Command line/Terminal access

### Compilation

1. Navigate to the project root directory:
```bash
cd learntrack
```

2. Compile all Java files:
```bash
javac -d bin src/com/airtribe/learntrack/*.java src/com/airtribe/learntrack/entity/*.java src/com/airtribe/learntrack/repository/*.java src/com/airtribe/learntrack/service/*.java src/com/airtribe/learntrack/exception/*.java src/com/airtribe/learntrack/util/*.java
```

Or compile with a simpler approach:
```bash
javac -d bin -sourcepath src src/com/airtribe/learntrack/Main.java
```

### Running the Application

```bash
java -cp bin com.airtribe.learntrack.Main
```

## Learning Objectives Covered

### ✓ Java Basics
- Variables, data types, and scopes
- Control flow (if-else, switch, loops)
- Type casting and conversions

### ✓ Object-Oriented Programming
- **Classes and Objects**: Entity classes for Student, Course, Enrollment
- **Constructors**: Default, parameterized, and overloaded constructors
- **Encapsulation**: Private fields with public getters/setters
- **Inheritance**: Person base class extended by Student
- **Polymorphism**: Method overriding (getDisplayName())
- **Static Members**: IdGenerator utility with static counters and methods

### ✓ Collections
- ArrayList for dynamic data storage
- Different scenarios: ArrayList of Students, Courses, Enrollments

### ✓ Exception Handling
- Custom exceptions (EntityNotFoundException, InvalidInputException)
- Try-catch blocks for error handling
- Input validation and error messages

### ✓ Clean Code Practices
- Meaningful method and variable names
- Separation of concerns (entity, repository, service, UI layers)
- Small, focused methods
- Proper use of access modifiers

## Features

### Student Management
- Add new students with validation
- View all students in formatted table
- Search students by ID
- Update student information
- Deactivate/Activate students
- Display student details with batch information

### Course Management
- Add new courses with duration in weeks
- View all courses
- Search courses by ID
- Update course information
- Deactivate/Activate courses
- Track course duration and availability

### Enrollment Management
- Enroll students in courses with validation
- View all enrollments
- View enrollments filtered by student
- View enrollments filtered by course
- Update enrollment status (ACTIVE, COMPLETED, CANCELLED)
- Mark enrollments as completed or cancelled

## Design Patterns & Architecture

### Layered Architecture
1. **Entity Layer**: Data models (Student, Course, Enrollment)
2. **Repository Layer**: Data access and persistence (in-memory)
3. **Service Layer**: Business logic and validation
4. **UI Layer**: Console interface (Main.java)

### Design Principles Applied
- **Single Responsibility Principle**: Each class has one reason to change
- **Dependency Injection**: Services receive their dependencies
- **Validation at Service Level**: All data validation happens in services

## Sample Usage

```
1. Create a new student:
   - Go to Student Management → Add New Student
   - Enter name, email, batch
   - System generates unique ID

2. Create a new course:
   - Go to Course Management → Add New Course
   - Enter course name, description, duration

3. Enroll student:
   - Go to Enrollment Management → Enroll Student in Course
   - Enter student ID and course ID
   - Track enrollment status

4. View all enrollments for a student:
   - Go to Enrollment Management → View Enrollments by Student
   - Enter student ID
```

## Data Validation

The application validates all inputs:
- Non-empty string validation
- Email format validation
- Positive integer validation
- Numeric parsing with error handling
- Enrollment status validation (ACTIVE, COMPLETED, CANCELLED)

## Error Handling

- **EntityNotFoundException**: Thrown when accessing non-existent entities
- **InvalidInputException**: Thrown when user input fails validation
- User-friendly error messages displayed in console
- Application continues running after errors

## Future Enhancements

- File-based persistence (CSV or JSON)
- Database integration (SQL)
- User authentication
- Advanced search and filtering
- Grade tracking for students
- Course prerequisites
- Batch operations
- Reporting features

## Notes

- All data is stored in-memory and will be lost when the application exits
- IDs are auto-generated starting from 1000 (students), 2000 (courses), 3000 (enrollments)
- The console UI uses Scanner for input and System.out for output

## Author & Course

**Course**: Core Java Fundamentals  
**Created**: 2024  
**Platform**: AirTribe

## License

This project is provided for educational purposes.
