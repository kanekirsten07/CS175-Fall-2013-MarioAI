dir /s /B *.java > build.dat
javac -cp lib/asm-all-3.3.jar;lib/jdom.jar;lib/junit-4.8.2.jar;lib/testng.jar -d bin @build.dat
pause