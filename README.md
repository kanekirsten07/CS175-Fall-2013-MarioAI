CS175-Fall-2013-MarioAI
=======================

Mario AI for CS 175 at UC Irvine, Fall 2013

Alexandra Rose
Brian MacIntosh
Evan Burke
Kenneth Chong
Kirsten Kane
Lawrence Yu

This is a mario AI based on code from the 2012 Mario AI championship furnished for the project course CS 175: Project
in Artificial Intelligence at University of California, Irvine, Fall 2013.

All of our edited code is contained in the src folder ch.idsia.agents.reinforcementlearningcs175.

To run this project, navigate to the source folder CS175-Fall-2013-MarioAI


ON WINDOWS
****************
Running the AI:
run the batch files 

build_all.bat
then play_agent_gen.bat

Play yourself:

run the batch file

Play.bat

ON UNIX (MAC OR LINUX)
************************

to build this project on Unix based systems, you need to have apache ant installed. You can install this from the command 
prompt using the command

sudo port install apache-ant

If you would prefer to install in manually, you can find it at:

http://ant.apache.org/

after ant is intalled, run the following command in the root directory

ant -buildfile build.xml

then run the bash file using

./PlayOurs.sh

If it is the case that this turns out to be non-executable, make it executeable with the following command:

chmod +x PlayOurs.sh

CONFIGURATIONS
*****************
This project is defaulted to run at 60 generations each of which has 15 runs of the level, to change this, you may config 
that under GeneticNNMain.java.

To change the environment processor that the AI uses, you may change it under GeneticManager.java. You may change it
to any class that implements the IEnvironmentProcessor interface.

each of these files are located under ch.idsia.agents.reinforcementlearningcs175
 
 



