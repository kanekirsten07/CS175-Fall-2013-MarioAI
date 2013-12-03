package ch.idsia.agents.reinforcementlearningcs175;

import java.util.List;

public interface IProjectManager
{
	/*
	 * Causes numberOfAgents new agents to be 
	 * 	1. Created (via the genetic algorithm) 
	 * 	2. Evaluated on the level
	 * 	3. Stored in the current pool of agents
	 */
	public void genAndRunNewAgents(int numberOfAgents);
	
	/*
	 * Returns the number of agents are in the current generation
	 */
	public int getGenerationSize();

	/*
	 * Returns the current generation number
	 */
	public int getGenerationNumber();

	/*
	 * Purges the current pool of agents, keeping the top numberOfAgentsToKeep agents for the next generation.
	 * Increases the generation number by 1.
	 */
	public int purgeCurrentGeneration(int numberOfAgentsToKeep);

	public int getLevelSeed();

	public void setLevelSeed(long seed);
	
	/*
	 * Resets generaton count and clears the agent pool, as if we just started anew.
	 */
	public void reset();

	/*
	 * Returns the top scoring agent.
	 */
	public GeneticManagerAgent getBestAgent();

	/*
	 * Setters and getters for whether the GUI (of Mario running through the level) is enabled.
	 */
	public boolean getGUIEnabled();

	public void setGUIEnabled(boolean enabled);

	/*
	 * Runs a previous agent through the level.
	 */
	public void runPreviousAgent(int agentID);

	public boolean agentExists(int agentID);

	/*
	 * Returns the agents in the current generation.
	 */
	public List<GeneticManagerAgent> getCurrentGeneration();

	/*
	 * Return the top "numberOfAgents" scoring agents of the current generation.
	 */
	public List<GeneticManagerAgent> getBestAgents(int numberOfAgents);
}
