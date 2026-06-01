#!/bin/bash

echo "=========================================="
echo "  LearnTrack Compilation Script"
echo "=========================================="
echo ""

if ! command -v javac &> /dev/null; then
    echo "❌ Error: javac not found!"
    echo "Please install JDK 11 or higher."
    exit 1
fi

echo "✓ Java compiler found"
java_version=$(javac -version 2>&1)
echo "  Using: $java_version"
echo ""

if [ ! -d "bin" ]; then
    echo "📁 Creating bin directory..."
    mkdir -p bin
else
    echo "✓ bin directory exists"
fi

echo ""
echo "📦 Compiling LearnTrack..."
echo ""

javac -d bin -sourcepath src $(find src -name "*.java")

if [ $? -eq 0 ]; then
    echo ""
    echo "✅ Compilation successful!"
    echo ""
    echo "📊 Compiled files:"
    find bin -name "*.class" | wc -l
    echo "   .class files created in bin/ directory"
    echo ""
    echo "🚀 To run the application:"
    echo "   java -cp bin com.airtribe.learntrack.ui.Main"
    echo ""
else
    echo ""
    echo "❌ Compilation failed!"
    echo "Please check the error messages above."
    exit 1
fi
