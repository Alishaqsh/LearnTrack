# LearnTrack - Getting Started Guide

## 🎉 Welcome to LearnTrack!

You've just received a complete, professional Java project demonstrating core Java fundamentals. This guide will help you get started quickly.

---

## ⚡ The 5-Minute Start

### 1. Open Terminal/Command Prompt
Navigate to the learntrack folder:
```bash
cd learntrack
```

### 2. Compile the Project
Choose based on your operating system:

**On macOS/Linux:**
```bash
chmod +x compile.sh
./compile.sh
```

**On Windows:**
```bash
compile.bat
```

**Or manually (all platforms):**
```bash
mkdir bin
javac -d bin -sourcepath src src/com/airtribe/learntrack/Main.java
```

### 3. Run the Application
```bash
java -cp bin com.airtribe.learntrack.Main
```

### 4. Start Using It!
The menu will guide you. Try:
- Add a student
- Add a course
- Enroll the student in the course
- View the enrollment

---

## 📚 What's in This Package?

### Your Complete Toolkit Includes:

```
✅ 15 Java source files (3,500+ lines)
   ├── 1 Main application file
   ├── 4 Entity classes
   ├── 3 Repository classes
   ├── 3 Service classes
   ├── 2 Exception classes
   └── 2 Utility classes

✅ 6 Documentation files (50+ pages)
   ├── Quick start guide
   ├── Installation instructions
   ├── Architecture explanation
   ├── Java concepts guide
   ├── Complete README
   └── Documentation index

✅ 2 Compilation scripts
   ├── For Windows
   └── For Unix/Linux/macOS

✅ Project summaries & manifests
```

---

## 🗺️ Documentation Roadmap

Choose based on your situation:

### "I Just Want to Run It" (5 minutes)
1. Open: `docs/QUICKSTART.md`
2. Follow steps 1-4
3. Enjoy!

### "I'm New to Java" (1-2 hours)
1. Read: `docs/Setup_Instructions.md`
2. Read: `docs/JVM_Basics.md`
3. Follow: `docs/QUICKSTART.md`
4. Read: `docs/README.md`
5. Study: Source code

### "I Know Java But New to Project" (30 minutes)
1. Skim: `docs/README.md`
2. Study: `docs/Design_Notes.md`
3. Run: Follow QUICKSTART
4. Review: Source code

### "I'm Familiar with Everything" (15 minutes)
1. Run: Use compile scripts and run
2. Test: Try all features
3. Explore: Review source code
4. Extend: Add your own features

---

## 📖 Document Guide

| Document | Best For | Time | Key Topics |
|----------|----------|------|------------|
| **QUICKSTART.md** | Getting running fast | 15 min | 5-min setup, features, testing |
| **README.md** | Full understanding | 20 min | Overview, features, structure |
| **Setup_Instructions.md** | Installation help | 20 min | Java install, compilation, IDE setup |
| **JVM_Basics.md** | Java concepts | 25 min | JVM/JRE/JDK, bytecode, WORA |
| **Design_Notes.md** | Code understanding | 30 min | Architecture, OOP, patterns |
| **INDEX.md** | Navigation | 5 min | Where to go, reading order |

---

## 🎯 What You'll Learn

### Fundamentals
- Java variables, data types, control flow
- How to write classes and objects
- Constructors and method overloading
- Public/private and encapsulation

### Object-Oriented Programming
- **Inheritance**: Student extends Person
- **Polymorphism**: Method overriding
- **Abstraction**: Layered architecture
- **Encapsulation**: Getters/setters

### Collections & Data Structures
- Using ArrayList for dynamic storage
- Adding, removing, searching elements
- Iteration and filtering

### Error Handling
- Writing custom exceptions
- Try-catch blocks
- Meaningful error messages

### Architecture
- Layered design (Entity → Repository → Service → UI)
- Separation of concerns
- Clean code practices

---

## 🚀 Quick Feature Test

After running the application, try this:

### Test 1: Add a Student
```
Menu → 1: Student Management
       → 1: Add New Student
Name: John Doe
Email: john@example.com
Batch: Java_001
✓ Student created with ID 1001
```

### Test 2: Add a Course
```
Menu → 2: Course Management
       → 1: Add New Course
Name: Core Java
Duration: 8 weeks
✓ Course created with ID 2001
```

### Test 3: Enroll Student
```
Menu → 3: Enrollment Management
       → 1: Enroll Student
Student ID: 1001
Course ID: 2001
✓ Student enrolled successfully (ID 3001)
```

### Test 4: View Enrollments
```
Menu → 3: Enrollment Management
       → 2: View All Enrollments
✓ See your enrollment listed
```

---

## 📁 Project Structure Overview

```
learntrack/                    Main project folder
│
├── src/                       Source code (Java files)
│   └── com/airtribe/learntrack/
│       ├── Main.java                    ← Start here
│       ├── entity/                      ← Data models
│       ├── repository/                  ← Data storage
│       ├── service/                     ← Business logic
│       ├── exception/                   ← Custom exceptions
│       └── util/                        ← Helper utilities
│
├── docs/                      Documentation (read these!)
│   ├── INDEX.md               ← Documentation guide
│   ├── QUICKSTART.md          ← 5-minute setup
│   ├── README.md              ← Full overview
│   ├── Setup_Instructions.md  ← Installation
│   ├── JVM_Basics.md          ← Java concepts
│   └── Design_Notes.md        ← Architecture
│
├── bin/                       Compiled files (created after compilation)
│
├── compile.sh                 For Unix/Linux/macOS
├── compile.bat                For Windows
├── PROJECT_SUMMARY.md         Project statistics
└── FILE_MANIFEST.md          Complete file list
```

---

## 🔧 Troubleshooting

### "javac not found"
**Solution**: Java is not installed or not in PATH.
→ See `docs/Setup_Instructions.md` → "Step 1: Install JDK"

### "Compilation failed"
**Solution**: Check error messages. Common causes:
- Missing src files
- Wrong file names
- Java version issue
→ See `docs/Setup_Instructions.md` → "Troubleshooting"

### "ClassNotFoundException"
**Solution**: Classpath is wrong. Use:
```bash
java -cp bin com.airtribe.learntrack.Main
```

### "No main method"
**Solution**: Main.java must be in the right location.
Location: `src/com/airtribe/learntrack/Main.java` ✓

---

## 💡 Usage Tips

1. **Menu Options**: Type the number and press Enter
2. **Empty Fields**: Enter data without leading/trailing spaces
3. **IDs**: 
   - Students start at 1001
   - Courses start at 2001
   - Enrollments start at 3001
4. **Soft Delete**: "Deactivate" doesn't delete, just marks as inactive
5. **Status Types**: ACTIVE, COMPLETED, CANCELLED

---

## 🎓 Learning Path

### Week 1: Setup & Basics
- [ ] Install Java (docs/Setup_Instructions.md)
- [ ] Run the application (docs/QUICKSTART.md)
- [ ] Understand what it does (docs/README.md)
- [ ] Learn Java basics (docs/JVM_Basics.md)

### Week 2: Code Understanding
- [ ] Read design (docs/Design_Notes.md)
- [ ] Study entity classes (Person.java, Student.java)
- [ ] Understand repositories (StudentRepository.java)
- [ ] Learn services (StudentService.java)

### Week 3: Coding Practice
- [ ] Add a new field to Student
- [ ] Add a new method to StudentService
- [ ] Modify the menu to use new feature
- [ ] Test your changes

### Week 4: Advanced
- [ ] Add a new entity (e.g., Grade)
- [ ] Create full CRUD for new entity
- [ ] Create new menu options
- [ ] Extend the application

---

## 🎁 What Makes This Project Great

✅ **Complete** - Everything needed to learn
✅ **Professional** - Real-world patterns
✅ **Well-Documented** - 50+ pages of guides
✅ **Well-Organized** - Clear package structure
✅ **Practical** - Working application you can run
✅ **Extensible** - Easy to add new features
✅ **Educational** - Teaches best practices
✅ **No Dependencies** - Pure Java, no libraries

---

## 🚀 Next Steps

### Immediate (Now)
1. Compile the project
2. Run the application
3. Test basic features

### Short Term (Today)
1. Read QUICKSTART.md
2. Read README.md
3. Explore the code

### Medium Term (This Week)
1. Read JVM_Basics.md
2. Read Design_Notes.md
3. Study source code
4. Try modifying code

### Long Term (This Month)
1. Add new features
2. Refactor code
3. Add file persistence
4. Extend to web application

---

## 🎉 You're Ready!

Everything is set up and ready to go. Here's what to do right now:

### Option 1: Quick Start (5 minutes)
```bash
cd learntrack
./compile.sh        # or compile.bat on Windows
java -cp bin com.airtribe.learntrack.Main
```

### Option 2: Detailed Start (30 minutes)
1. Read `docs/INDEX.md`
2. Read `docs/QUICKSTART.md`
3. Follow the compilation and run steps
4. Try the features
5. Read `docs/README.md`

### Option 3: Complete Learning (2-3 hours)
1. Read all documentation
2. Compile and run
3. Study the source code
4. Take notes
5. Try modifications

---

## 📞 Help & Resources

### In This Package
- **Installation Help**: `docs/Setup_Instructions.md`
- **Java Concepts**: `docs/JVM_Basics.md`
- **Architecture**: `docs/Design_Notes.md`
- **Quick Reference**: `docs/README.md`
- **Getting Started**: `docs/QUICKSTART.md`
- **Documentation Index**: `docs/INDEX.md`

### Online Resources
- Java Official Docs: https://docs.oracle.com/javase
- Java Tutorials: https://docs.oracle.com/javase/tutorial
- Object-Oriented Programming: Any Java textbook

---

## ✨ Key Files to Know

| File | Purpose | Why Important |
|------|---------|---------------|
| Main.java | Application | Where everything starts |
| Person.java | Base class | Shows inheritance |
| Student.java | Student entity | Shows polymorphism |
| StudentService.java | Business logic | Shows architecture |
| StudentRepository.java | Data storage | Shows ArrayList usage |
| docs/JVM_Basics.md | Learn Java | Understand how Java works |
| docs/Design_Notes.md | Learn architecture | Understand project design |

---

## 🏆 Success Indicators

You'll know you're successful when:

✅ Application compiles without errors
✅ Application runs and shows menu
✅ You can add a student
✅ You can add a course
✅ You can enroll student in course
✅ You understand the code structure
✅ You can make small modifications
✅ You can explain the architecture

---

## 🎯 Final Checklist

Before you start, make sure you have:

- [x] Downloaded/extracted the project
- [x] Opened the learntrack folder
- [x] Java installed (check: `java -version`)
- [x] Read this Getting Started guide
- [x] Located the docs/ folder
- [x] Located the src/ folder
- [x] Ready to compile and run

**You're all set! 🚀 Begin with:**

```bash
cd learntrack
./compile.sh          # macOS/Linux
compile.bat           # Windows
java -cp bin com.airtribe.learntrack.Main
```

---

**Happy Learning! Enjoy exploring LearnTrack! 🎉**

*For detailed information, check out the comprehensive documentation in the `docs/` folder.*
