# Quick Start Guide

## TL;DR - Get Running in 5 Minutes

### Prerequisites Check
Make sure Java is installed:
```bash
java -version
javac -version
```

### Step 1: Navigate to Project
```bash
cd learntrack
```

### Step 2: Create bin folder
```bash
mkdir bin
```

### Step 3: Compile
```bash
javac -d bin -sourcepath src src/com/airtribe/learntrack/Main.java
```

### Step 4: Run
```bash
java -cp bin com.airtribe.learntrack.Main
```

### Step 5: Enjoy!
You should see the LearnTrack menu. Start by creating some students and courses!

---

## Detailed Quick Start

### Test Student Data to Try

```
Sample Student 1:
- First Name: Arjun
- Last Name: Kumar
- Email: arjun@example.com
- Batch: Java_Batch_001

Sample Course 1:
- Course Name: Core Java Fundamentals
- Description: Master the basics of Java
- Duration: 8 weeks

Sample Course 2:
- Course Name: Advanced OOP
- Description: Deep dive into Object-Oriented Programming
- Duration: 6 weeks
```

### Common Commands in the Application

**Add a Student:**
1. Select `1` → Student Management
2. Select `1` → Add New Student
3. Enter the details as prompted

**View All Students:**
1. Select `1` → Student Management
2. Select `2` → View All Students

**Enroll Student:**
1. Note the Student ID and Course ID
2. Select `3` → Enrollment Management
3. Select `1` → Enroll Student in Course
4. Enter Student ID and Course ID

**Check Enrollment Status:**
1. Select `3` → Enrollment Management
2. Select `2` → View All Enrollments

---

## Project Structure at a Glance

```
learntrack/
├── src/                    # Source code
│   └── com/airtribe/learntrack/
│       ├── Main.java              # START HERE - Main application
│       ├── entity/                # Data models
│       ├── repository/            # Data access
│       ├── service/               # Business logic
│       ├── exception/             # Custom exceptions
│       └── util/                  # Utilities
│
├── bin/                    # Compiled classes (created after compilation)
│
└── docs/                   # Documentation
    ├── README.md           # Full project documentation
    ├── Setup_Instructions.md
    ├── JVM_Basics.md       # Understanding JVM/JRE/JDK
    └── Design_Notes.md     # Architecture explanation
```

---

## Understanding the Code - Key Files

### Main.java (Entry Point)
- **What:** Menu-driven console application
- **How:** Uses Scanner for input, switch statements for menu
- **Key Methods:** mainMenu(), studentManagementMenu(), courseManagementMenu()

### Entity Classes (entity/)
- **Person.java:** Base class with id, firstName, lastName, email
- **Student.java:** Extends Person, adds batch and active status
- **Course.java:** Course information with duration
- **Enrollment.java:** Links students to courses

### Service Classes (service/)
- **StudentService.java:** Add, update, view students
- **CourseService.java:** Add, update, view courses
- **EnrollmentService.java:** Enroll, track enrollments

### Repository Classes (repository/)
- **StudentRepository.java:** In-memory storage for students (ArrayList)
- **CourseRepository.java:** In-memory storage for courses
- **EnrollmentRepository.java:** In-memory storage for enrollments

### Utilities (util/)
- **IdGenerator.java:** Generates unique IDs (static methods)
- **InputValidator.java:** Validates user input

---

## Marks/Grading Breakdown (100 Total)

The project was designed to evaluate:

| Area | Marks | Demonstrated in |
|------|-------|-----------------|
| Environment Setup & JVM | 10 | docs/Setup_Instructions.md, docs/JVM_Basics.md |
| Package Structure & Basics | 10 | Proper package organization, access modifiers |
| Core OOP Implementation | 40 | Entity classes, inheritance, static members |
| Menu UI & Logic | 25 | Main.java with menu-driven interface |
| Exception Handling | 10 | Custom exceptions, try-catch blocks |
| Documentation & Code | 5 | README.md, Design_Notes.md, clean code |

---

## Key Concepts Demonstrated

### ✓ Object-Oriented Programming
- **Classes & Objects**: Student, Course, Enrollment classes
- **Constructors**: Default, parameterized, overloaded
- **Inheritance**: Student extends Person
- **Encapsulation**: Private fields, public getters/setters
- **Polymorphism**: Method overriding (getDisplayName())
- **Static**: IdGenerator with static methods and variables

### ✓ Collections
- **ArrayList**: Dynamic array for storing students, courses, enrollments
- **Iteration**: Using enhanced for loops
- **Methods**: add(), get(), remove(), clear()

### ✓ Exception Handling
- **Custom Exceptions**: EntityNotFoundException, InvalidInputException
- **Try-Catch**: Input parsing and validation
- **Error Messages**: User-friendly error display

### ✓ Control Flow
- **Loops**: while (true), for loops
- **Conditionals**: if-else, switch statements
- **Input Validation**: Checking for invalid menu options

---

## Compilation & Execution Explained

### What Happens When You Compile?
```bash
javac -d bin -sourcepath src src/com/airtribe/learntrack/Main.java
```

1. **javac**: Java compiler
2. **-d bin**: Output compiled .class files to `bin` folder
3. **-sourcepath src**: Look for source files in `src` folder
4. **src/com/airtribe/learntrack/Main.java**: Main file to compile

**Result:** All `.java` files are converted to `.class` files (bytecode)

### What Happens When You Run?
```bash
java -cp bin com.airtribe.learntrack.Main
```

1. **java**: Java launcher
2. **-cp bin**: Classpath - where to find .class files
3. **com.airtribe.learntrack.Main**: Fully qualified class name to run

**Result:** JVM loads and executes the application

---

## Troubleshooting

| Problem | Solution |
|---------|----------|
| "javac not found" | Java not installed or not in PATH |
| "class not found" | Check classpath with `-cp bin` |
| "no main method" | Main must be in Main.java |
| "empty input" | Enter values without leading/trailing spaces |
| "invalid option" | Menu options are 1-4, 1-7, 1-8, etc. |

---

## Feature Walk-Through

### Student Management
1. **Add Student** → Creates new student with unique ID
2. **View All** → Shows table of all students
3. **Search** → Find student by ID
4. **Update** → Modify student info
5. **Deactivate** → Marks student as inactive (soft delete)
6. **Activate** → Reactivate a student

### Course Management
Similar to Student Management

### Enrollment Management
1. **Enroll** → Links student to course
2. **View All** → Shows all enrollments
3. **By Student** → See which courses a student is enrolled in
4. **By Course** → See which students are in a course
5. **Update Status** → Change to ACTIVE, COMPLETED, or CANCELLED
6. **Complete** → Marks enrollment as COMPLETED
7. **Cancel** → Marks enrollment as CANCELLED

---

## Data Flow Example

### Adding a Student:

```
User Input (Main.java)
    ↓ firstName, lastName, email, batch
User Interface
    ↓ calls addStudent()
StudentService
    ↓ validates input using InputValidator
    ↓ generates ID using IdGenerator
    ↓ creates Student object
StudentRepository
    ↓ stores in ArrayList
Database (in-memory)
    ↓
✓ Student added!
```

---

## Testing the Application

### Test Case 1: Add and View Student
1. Select 1 → Student Management
2. Select 1 → Add New Student
3. Enter details (e.g., John, Doe, john@example.com, Batch1)
4. Select 2 → View All Students
5. Should see your student in the list

### Test Case 2: Add Course
1. Select 2 → Course Management
2. Select 1 → Add New Course
3. Enter details (e.g., Java, Learn Java, 8)
4. Select 2 → View All Courses
5. Should see your course

### Test Case 3: Enroll Student
1. Note Student ID (from student list)
2. Note Course ID (from course list)
3. Select 3 → Enrollment Management
4. Select 1 → Enroll Student in Course
5. Enter Student ID and Course ID
6. Should see "Student enrolled successfully!"

---

## Tips & Tricks

- **ID Format**: Student IDs start at 1001, Course IDs at 2001, Enrollment IDs at 3001
- **Soft Delete**: "Deactivating" a student sets active=false but keeps data
- **Status Codes**: Enrollments use ACTIVE, COMPLETED, CANCELLED
- **No Data Persistence**: All data is lost when application exits
- **Case Sensitive**: Package names and class names matter

---

## Next Steps After Getting It Running

1. **Explore the Code**: Open entity/ classes and understand structure
2. **Read JVM_Basics.md**: Understand how Java runs
3. **Read Design_Notes.md**: Understand architecture decisions
4. **Try Modifications**: Add a new field to Student class
5. **Run Tests**: Add multiple students/courses/enrollments and verify

---

## FAQ

**Q: Why can't I persist data after exiting?**
A: This is an in-memory application. Add file/database persistence for real persistence.

**Q: Can I use an IDE?**
A: Yes! Import the project into IntelliJ, Eclipse, or VS Code.

**Q: How do I add more features?**
A: Create new service methods, add new entity fields, extend the menu.

**Q: Is this production-ready?**
A: No, it's a learning project. Production would need database, validation framework, logging, etc.

---

**Happy Learning! 🚀**

If you have any questions, refer to the detailed documentation in the `docs/` folder.
