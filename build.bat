@echo off
cd /d "%~dp0"
call mvn clean package -DskipTests
echo.
echo ✓ Build complete! JAR file ready in target/ directory
echo ✓ Copy target/Homes-1.0.jar to your server's plugins folder
pause
