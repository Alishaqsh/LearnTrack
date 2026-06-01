@echo off
REM LearnTrack Compilation Script for Windows
REM Usage: compile.bat

echo.
echo ==========================================
echo   LearnTrack Compilation Script
echo ==========================================
echo.

REM Check if Java compiler is available
javac -version >nul 2>&1
if errorlevel 1 (
    echo Error: javac not found!
    echo Please install JDK 11 or higher.
    pause
    exit /b 1
)

echo Checking Java compiler...
javac -version
echo.

REM Create bin directory if it doesn't exist
if not exist "bin" (
    echo Creating bin directory...
    mkdir bin
    echo Directory created: bin
) else (
    echo bin directory exists
)

echo.
echo Compiling LearnTrack...
echo.

REM Compile all Java files
javac -d bin -sourcepath src src\com\airtribe\learntrack\ui\Main.java

if errorlevel 1 (
    echo.
    echo Compilation failed!
    echo Please check the error messages above.
    pause
    exit /b 1
) else (
    echo.
    echo Compilation successful!
    echo.
    echo To run the application, use:
    echo java -cp bin com.airtribe.learntrack.ui.Main
    echo.
)

pause
