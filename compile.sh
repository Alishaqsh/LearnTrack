#!/bin/bash

# LearnTrack Compilation Script
# Usage: ./compile.sh

echo "=========================================="
echo "  LearnTrack Compilation Script"
echo "=========================================="
echo ""

# Check if Java compiler is available
if ! command -v javac &> /dev/null; then
    echo "❌ Error: javac not found!"
    echo "Please install JDK 11 or higher."
    exit 1
fi

echo "✓ Java compiler found"
java_version=$(javac -version 2>&1)
echo "  Using: $java_version"
echo ""

# Create bin directory if it doesn't exist
if [ ! -d "bin" ]; then
    echo "📁 Creating bin directory..."
    mkdir -p bin
else
    echo "✓ bin directory exists"
fi

echo ""
echo "📦 Compiling LearnTrack..."
echo ""

# Compile all Java files
javac -d bin -sourcepath src \
    src/com/airtribe/learntrack/Main.java \
    src/com/airtribe/learntrack/entity/Person.java \
    src/com/airtribe/learntrack/entity/Student.java \
    src/com/airtribe/learntrack/entity/Course.java \
    src/com/airtribe/learntrack/entity/Enrollment.java \
    src/com/airtribe/learntrack/repository/StudentRepository.java \
    src/com/airtribe/learntrack/repository/CourseRepository.java \
    src/com/airtribe/learntrack/repository/EnrollmentRepository.java \
    src/com/airtribe/learntrack/service/StudentService.java \
    src/com/airtribe/learntrack/service/CourseService.java \
    src/com/airtribe/learntrack/service/EnrollmentService.java \
    src/com/airtribe/learntrack/exception/EntityNotFoundException.java \
    src/com/airtribe/learntrack/exception/InvalidInputException.java \
    src/com/airtribe/learntrack/util/IdGenerator.java \
    src/com/airtribe/learntrack/util/InputValidator.java

if [ $? -eq 0 ]; then
    echo ""
    echo "✅ Compilation successful!"
    echo ""
    echo "📊 Compiled files:"
    find bin -name "*.class" | wc -l
    echo "   .class files created in bin/ directory"
    echo ""
    echo "🚀 To run the application:"
    echo "   java -cp bin com.airtribe.learntrack.Main"
    echo ""
else
    echo ""
    echo "❌ Compilation failed!"
    echo "Please check the error messages above."
    exit 1
fi
