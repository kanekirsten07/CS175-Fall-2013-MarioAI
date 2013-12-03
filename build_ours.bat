dir src\ch\idsia\agents\reinforcementlearningcs175\*.java /B /S > build.dat
javac -cp bin;lib/asm-all-3.3.jar;lib/jdom.jar;lib/junit-4.8.2.jar;lib/testng.jar -d bin @build.dat
pause