# Setup Instructions for LearnTrack

## System Requirements

- Operating System: Windows, macOS, or Linux
- Memory: Minimum 512MB RAM
- Disk Space: Minimum 100MB
- Java Development Kit (JDK) 11 or higher

## Step 1: Install Java Development Kit (JDK)

### Windows:
1. Download JDK from [oracle.com/java/technologies/downloads](https://www.oracle.com/java/technologies/downloads/)
2. Run the installer (.exe file)
3. Follow the installation wizard (accept default settings)
4. Set JAVA_HOME environment variable:
   - Right-click "This PC" → Properties → Advanced system settings
   - Click "Environment Variables"
   - Click "New" under System Variables
   - Variable name: `JAVA_HOME`
   - Variable value: `C:\Program Files\Java\jdk-11` (or your installation path)
   - Click OK

### macOS:
```bash
# Using Homebrew
brew install openjdk@11

# Or download from oracle.com
```

### Linux (Ubuntu/Debian):
```bash
sudo apt-get update
sudo apt-get install openjdk-11-jdk
```

## Step 2: Verify Java Installation

Open Command Prompt/Terminal and run:
```bash
java -version
javac -version
```

You should see output similar to:
```
java version "11.0.12" 2021-07-20 LTS
Java(TM) SE Runtime Environment 18.9 (build 11.0.12+8-LTS-237)
Java HotSpot(TM) 64-Bit Server VM 18.9 (build 11.0.12+8-LTS-237, mixed mode)
```

## Step 3: Setup LearnTrack Project

1. Extract the LearnTrack project folder to your preferred location
2. Open Command Prompt/Terminal
3. Navigate to the project directory:
   ```bash
   cd path/to/learntrack
   ```

## Step 4: Create Output Directory

Create a `bin` directory for compiled classes:

### Windows:
```bash
mkdir bin
```

### macOS/Linux:
```bash
mkdir bin
```

## Step 5: Compile the Project

Compile all Java files:

### Option 1 (Recommended):
```bash
javac -d bin -sourcepath src src/com/airtribe/learntrack/Main.java
```

This will compile Main.java and all its dependencies.

### Option 2 (Manual compilation of all files):
```bash
javac -d bin src/com/airtribe/learntrack/*.java \
              src/com/airtribe/learntrack/entity/*.java \
              src/com/airtribe/learntrack/repository/*.java \
              src/com/airtribe/learntrack/service/*.java \
              src/com/airtribe/learntrack/exception/*.java \
              src/com/airtribe/learntrack/util/*.java
```

**Expected Output**: No output means successful compilation. Check that `bin` folder now contains `.class` files.

## Step 6: Run the Application

```bash
java -cp bin com.airtribe.learntrack.Main
```

You should see the welcome screen:
```
========================================
  Welcome to LearnTrack
  Student & Course Management System
========================================

--- MAIN MENU ---
1. Student Management
2. Course Management
3. Enrollment Management
4. Exit
Choose an option (1-4):
```

## Troubleshooting

### Issue: "javac is not recognized as an internal or external command"
**Solution**: Java is not installed or JAVA_HOME is not set correctly. Reinstall JDK or set environment variables.

### Issue: "Exception in thread "main" java.lang.NoClassDefFoundError"
**Solution**: Classpath is incorrect. Make sure you're using the `-cp bin` flag correctly.

### Issue: "No such file or directory"
**Solution**: Make sure you're in the correct project directory and the source files exist.

### Issue: Compilation fails with "package not found"
**Solution**: Run compilation with `-sourcepath src` flag to allow automatic compilation of dependencies.

## Project Structure Verification

Before running, verify your directory structure:
```
learntrack/
├── src/
│   └── com/airtribe/learntrack/
│       ├── Main.java
│       ├── entity/
│       │   ├── Person.java
│       │   ├── Student.java
│       │   ├── Course.java
│       │   └── Enrollment.java
│       ├── repository/
│       ├── service/
│       ├── exception/
│       └── util/
├── docs/
└── bin/ (created after compilation)
```

## Running "Hello World" Test

To verify Java installation works:

1. Create a file named `HelloWorld.java`:
```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
```

2. Compile:
```bash
javac HelloWorld.java
```

3. Run:
```bash
java HelloWorld
```

Expected output:
```
Hello World!
```

## IDE Setup (Optional)

If you want to use an IDE instead of command line:

### IntelliJ IDEA:
1. Open IntelliJ
2. File → Open → Select learntrack folder
3. Mark `src` as Sources Root
4. Run → Run 'Main'

### Eclipse:
1. File → New → Java Project → Name: "learntrack"
2. Uncheck "Use default location" → Browse to learntrack folder
3. Next → Select source folder as `src`
4. Finish
5. Right-click project → Run As → Java Application

### VS Code:
1. Install Extension Pack for Java
2. File → Open Folder → Select learntrack folder
3. Click on Main.java
4. Click "Run" button or Ctrl+Shift+D

## Next Steps

1. Refer to the README.md for project overview
2. Check Design_Notes.md for architecture details
3. Read JVM_Basics.md for JVM concepts
4. Start using the application!

## Tips

- Keep the terminal/command prompt window open while running the application
- Type carefully when entering menu options
- To exit the application, choose option 4 from main menu
- All data is lost when you exit the application (in-memory storage)

## Questions or Issues?

- Check the docs/ folder for more information
- Review the source code comments
- Ensure all steps were followed correctly

Good luck! Happy learning! 🚀
