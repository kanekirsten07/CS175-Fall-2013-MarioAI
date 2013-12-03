package ch.idsia.agents.reinforcementlearningcs175;

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

	public void setLevelSeed();
	
	/*
	 * Resets generaton count and clears the agent pool, as if we just started anew.
	 */
	public void resetAgents();

	/*
	 * Returns the top scoring agent.
	 */
	public GeneticManagerAgent getBestAgent();
}
