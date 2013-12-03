cd bin/
java -cp ../lib/asm-all-3.3.jar:. ch.idsia.scenarios.Main $@

# Example usage (use our agent, and use level seed of 5)
# ./Play.sh -ag ch.idsia.agents.reinforcementlearningcs175.ReinforcementLearningAgent -ls 5
