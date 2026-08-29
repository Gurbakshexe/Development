#!/bin/bash
cd "$(dirname "$0")"
mvn clean package -DskipTests
echo ""
echo "✓ Build complete! JAR file ready in target/ directory"
echo "✓ Copy target/Homes-1.0.jar to your server's plugins folder"
