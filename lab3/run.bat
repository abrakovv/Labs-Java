@echo off
setlocal
cd /d "%~dp0"

if not exist target mkdir target
if exist target\classes rmdir /s /q target\classes
mkdir target\classes

dir /s /b src\main\java\*.java > target\sources.txt
javac --release 17 -encoding UTF-8 -Xlint:all -Werror -d target\classes @target\sources.txt
if errorlevel 1 (
    echo.
    echo Compilation failed.
    exit /b 1
)

java -cp target\classes ru.abrakov.main.Main %*
endlocal
