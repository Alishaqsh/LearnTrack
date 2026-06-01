# LearnTrack - Student & Course Management System

![LearnTrack](https://img.shields.io/badge/Platform-Java-brightgreen) ![Status](https://img.shields.io/badge/Status-Active-blue) ![License](https://img.shields.io/badge/License-Educational-yellow)

## 📋 Project Description

**LearnTrack** is a comprehensive console-based Student & Course Management System built using **Core Java**. It demonstrates essential Java concepts including OOP principles, Collections, exception handling, and layered architecture patterns.

This system enables administrators to efficiently manage:
- 👨‍🎓 **Students** - Add, view, update, and manage student records
- 📚 **Courses** - Create and maintain course catalog with descriptions
- 📝 **Enrollments** - Track student enrollments and manage enrollment status

---

## 🏗️ System Architecture & Class Diagram

### Class Structure and Relationships

```
┌─────────────────────────────────────────────────────────────────┐
│                        ENTITY LAYER                              │
├─────────────────────────────────────────────────────────────────┤
│                                                                   │
│  ┌──────────────┐                                                │
│  │    Person    │ (Abstract Base Class)                          │
│  ├──────────────┤                                                │
│  │ - id: int    │                                                │
│  │ - name: String │                                              │
│  └──────────────┘                                                │
│         ▲                                                         │
│         │ (inherits)                                             │
│         │                                                         │
│  ┌──────────────┐      ┌──────────────┐      ┌──────────────┐  │
│  │   Student    │      │    Course    │      │ Enrollment   │  │
│  ├──────────────┤      ├──────────────┤      ├──────────────┤  │
│  │ - id: int    │      │ - id: int    │      │ - id: int    │  │
│  │ - name: String │    │ - name: String │    │ - studentId  │  │
│  │ - email      │      │ - duration   │      │ - courseId   │  │
│  │ - batch      │      │ - isActive   │      │ - status     │  │
│  │ - isActive   │      └──────────────┘      │ - enrollDate │  │
│  └──────────────┘             ▲              │ - completionDate │
│         ▲                      │              └──────────────┘  │
│         │ (has many)           │ (has many)        ▲           │
│         └──────────────────────┼────────────────────┘           │
│                                │                                 │
│                                │ (links)                        │
│                                │                                 │
└─────────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────────┐
│                    REPOSITORY LAYER (Data Access)                │
├─────────────────────────────────────────────────────────────────┤
│                                                                   │
│  ┌────────────────────┐  ┌────────────────────┐                 │
│  │ StudentRepository  │  │  CourseRepository  │                 │
│  ├────────────────────┤  ├────────────────────┤                 │
│  │ + add()            │  │ + add()            │                 │
│  │ + findById()       │  │ + findById()       │                 │
│  │ + update()         │  │ + update()         │                 │
│  │ + getAll()         │  │ + getAll()         │                 │
│  │ + delete()         │  │ + delete()         │                 │
│  └────────────────────┘  └────────────────────┘                 │
│                                                                   │
│                   ┌────────────────────┐                        │
│                   │EnrollmentRepository│                        │
│                   ├────────────────────┤                        │
│                   │ + add()            │                        │
│                   │ + findById()       │                        │
│                   │ + getByStudent()   │                        │
│                   │ + getByCourse()    │                        │
│                   │ + getAll()         │                        │
│                   │ + update()         │                        │
│                   └────────────────────┘                        │
│                                                                   │
└─────────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────────┐
│                    SERVICE LAYER (Business Logic)                │
├─────────────────────────────────────────────────────────────────┤
│                                                                   │
│  ┌──────────────────┐  ┌──────────────────┐                     │
│  │ StudentService   │  │  CourseService   │                     │
│  ├──────────────────┤  ├──────────────────┤                     │
│  │ + addStudent()   │  │ + addCourse()    │                     │
│  │ + viewStudents() │  │ + viewCourses()  │                     │
│  │ + validateEmail()│  │ + updateCourse() │                     │
│  │ + deactivate()   │  │ + deactivate()   │                     │
│  │ + activate()     │  │ + activate()     │                     │
│  └──────────────────┘  └──────────────────┘                     │
│                                                                   │
│              ┌────────────────────────┐                         │
│              │  EnrollmentService     │                         │
│              ├────────────────────────┤                         │
│              │ + enrollStudent()      │                         │
│              │ + viewEnrollments()    │                         │
│              │ + updateStatus()       │                         │
│              │ + validateEnrollment() │                         │
│              │ + markCompleted()      │                         │
│              │ + markCancelled()      │                         │
│              └────────────────────────┘                         │
│                                                                   │
└─────────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────────┐
│                    UI LAYER (Console Interface)                  │
├─────────────────────────────────────────────────────────────────┤
│                                                                   │
│                    ┌──────────────┐                             │
│                    │   Main.java  │                             │
│                    ├──────────────┤                             │
│                    │ + main()     │                             │
│                    │ + displayMenu()│                            │
│                    │ + getUserInput()│                           │
│                    └──────────────┘                             │
│                                                                   │
└─────────────────────────────────────────────────────────────────┘
```

---

## 📁 Project Structure

```
LearnTrack/
│
├── src/com/airtribe/learntrack/
│   │
│   ├── Main.java                           # Application entry point
│   │
│   ├── entity/                             # Data models
│   │   ├── Person.java                    # Abstract base class
│   │   ├── Student.java                   # Student entity
│   │   ├── Course.java                    # Course entity
│   │   └── Enrollment.java                # Enrollment entity
│   │
│   ├── repository/                         # Data access layer
│   │   ├── StudentRepository.java         # Student CRUD operations
│   │   ├── CourseRepository.java          # Course CRUD operations
│   │   └── EnrollmentRepository.java      # Enrollment CRUD operations
│   │
│   ├── service/                            # Business logic layer
│   │   ├── StudentService.java            # Student business logic
│   │   ├── CourseService.java             # Course business logic
│   │   └── EnrollmentService.java         # Enrollment business logic
│   │
│   ├── exception/                          # Custom exceptions
│   │   ├── EntityNotFoundException.java   # Entity not found error
│   │   └── InvalidInputException.java     # Input validation error
│   │
│   └── util/                               # Utility classes
│       ├── IdGenerator.java               # Auto ID generation
│       └── InputValidator.java            # Input validation
│
├── out/                                    # Compiled bytecode
├── docs/                                   # Documentation
├── compile.sh                              # Linux/Mac compilation script
├── compile.bat                             # Windows compilation script
└── README.md                               # This file
```

---

## 🔧 Technology Stack

| Component | Technology |
|-----------|-----------|
| **Language** | Java (JDK 11+) |
| **Build Tool** | Command-line compilation |
| **Data Storage** | In-memory (ArrayList) |
| **IDE Support** | IntelliJ IDEA, VS Code, Eclipse |
| **Architecture** | Layered Architecture Pattern |

---

## 🎯 Key Features

### 👨‍🎓 Student Management
- ✅ Add new students with validation
- ✅ View all students in formatted table
- ✅ Search students by ID
- ✅ Update student information (name, email, batch)
- ✅ Deactivate/Activate student accounts
- ✅ Display batch information

### 📚 Course Management
- ✅ Create new courses with detailed information
- ✅ View all available courses
- ✅ Search courses by ID
- ✅ Update course details
- ✅ Deactivate/Activate courses
- ✅ Track course duration in weeks

### 📝 Enrollment Management
- ✅ Enroll students in courses with validation
- ✅ View all enrollments
- ✅ Filter enrollments by student
- ✅ Filter enrollments by course
- ✅ Update enrollment status (ACTIVE, COMPLETED, CANCELLED)
- ✅ Track enrollment dates

---

## 🚀 Getting Started

### Prerequisites
- **Java Development Kit (JDK)** 11 or higher
- Command line/Terminal access
- Text editor or IDE (IntelliJ IDEA recommended)

### Installation & Compilation

#### Option 1: Using Provided Scripts

**For Linux/Mac:**
```bash
cd LearnTrack
chmod +x compile.sh
./compile.sh
```

**For Windows:**
```bash
cd LearnTrack
compile.bat
```

#### Option 2: Manual Compilation
```bash
cd LearnTrack
javac -d bin -sourcepath src src/com/airtribe/learntrack/ui/Main.java
```

### Running the Application

```bash
java -cp bin com.airtribe.learntrack.ui.Main
```

The console menu will appear with options to manage students, courses, and enrollments.

---

## 📖 Usage Examples

### Add a New Student
```
Menu → Student Management → Add New Student
Enter Name: John Doe
Enter Email: john.doe@example.com
Enter Batch: Batch-2026-01
✓ Student created with ID: 1000
```

### Create a Course
```
Menu → Course Management → Add New Course
Enter Course Name: Advanced Java
Enter Description: Master Java fundamentals
Enter Duration (weeks): 12
✓ Course created with ID: 2000
```

### Enroll a Student
```
Menu → Enrollment Management → Enroll Student
Enter Student ID: 1000
Enter Course ID: 2000
✓ Enrollment created with ID: 3000
```

---

## 🏗️ Design Patterns & Principles

### Architecture Layers

1. **Entity Layer** - Data models and their behavior
    - `Person` (abstract base class)
    - `Student`, `Course`, `Enrollment`

2. **Repository Layer** - Data persistence and access
    - Abstracts data storage (in-memory ArrayList)
    - Provides CRUD operations

3. **Service Layer** - Business logic and validation
    - Implements business rules
    - Validates input data
    - Orchestrates operations

4. **UI Layer** - Console interface
    - Displays menu to user
    - Handles user input
    - Calls service methods

### SOLID Principles Applied

- **S - Single Responsibility** - Each class has one reason to change
- **O - Open/Closed** - Open for extension, closed for modification
- **L - Liskov Substitution** - Student properly extends Person
- **I - Interface Segregation** - Classes implement only needed methods
- **D - Dependency Inversion** - Services depend on abstractions

---

## ✨ Learning Outcomes

This project demonstrates and teaches:

### Java Fundamentals
- ✓ Variables, data types, and scopes
- ✓ Control flow (if-else, switch, loops)
- ✓ Type casting and conversions

### Object-Oriented Programming
- ✓ Classes and Objects
- ✓ Constructors (default, parameterized)
- ✓ Encapsulation (private/public)
- ✓ Inheritance (`extends` keyword)
- ✓ Polymorphism (method overriding)
- ✓ Static members and methods

### Collections Framework
- ✓ ArrayList for dynamic collections
- ✓ Iteration over collections
- ✓ Searching and filtering

### Exception Handling
- ✓ Custom exceptions
- ✓ Try-catch blocks
- ✓ Input validation
- ✓ Error messages

### Clean Code Practices
- ✓ Meaningful naming conventions
- ✓ Separation of concerns
- ✓ Code organization
- ✓ DRY principle

---

## 📊 Data Validation

All inputs are validated:
- ✓ Non-empty string validation
- ✓ Email format validation
- ✓ Positive integer validation
- ✓ Enrollment status validation (ACTIVE, COMPLETED, CANCELLED)
- ✓ Duplicate enrollment prevention

---

## ⚠️ Error Handling

The system includes robust error handling:

| Exception | Scenario |
|-----------|----------|
| `EntityNotFoundException` | Accessing non-existent student/course |
| `InvalidInputException` | Invalid user input |
| `NumberFormatException` | Non-numeric ID input |
| `Custom Validation Errors` | Business rule violations |

---

## 🔮 Future Enhancements

- 📂 File-based persistence (CSV, JSON)
- 🗄️ Database integration (MySQL, PostgreSQL)
- 🔐 User authentication & authorization
- 🔍 Advanced search and filtering
- 📈 Grade tracking system
- 🔗 Course prerequisites
- 📊 Reporting and analytics
- 🎓 Batch operations
- 📱 Web interface

---

## 📝 Sample Data IDs

Auto-generated IDs follow this pattern:
- **Students**: 1000, 1001, 1002, ...
- **Courses**: 2000, 2001, 2002, ...
- **Enrollments**: 3000, 3001, 3002, ...

---

## ⚠️ Important Notes

- 💾 All data is **in-memory** - data is lost when application exits
- 🎯 Designed for **educational purposes**
- 🔄 Ideal for learning OOP and design patterns
- 🚀 Can be extended with database integration

---

## 👨‍💻 Author & Course Information

| Detail | Information |
|--------|-------------|
| **Author** | Alisha Qureshi |
| **Course** | Core Java Fundamentals |
| **Platform** | AirTribe |
| **Created** | 2026 |
| **Language** | Java |

---

## 📜 License

This project is provided for **educational purposes**. Feel free to use, modify, and learn from it!

---

## 🤝 Contributing

This is an educational project. Suggestions and improvements are welcome!


---

**Last Updated**: June 2026

---

<div align="center">

**⭐ If you found this helpful, please star the repository!**

[GitHub Repository](https://github.com/Alishaqsh/LearnTrack) | [Report Issues](https://github.com/Alishaqsh/LearnTrack/issues)

</div>