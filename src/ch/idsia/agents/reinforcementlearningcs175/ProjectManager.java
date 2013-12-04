package ch.idsia.agents.reinforcementlearningcs175;

import ch.idsia.benchmark.tasks.BasicTask;
import ch.idsia.tools.MarioAIOptions;

import java.util.List;

public class ProjectManager implements IProjectManager
{
	private ReinforcementLearningAgent agent;
	private GeneticManager geneticManager;
	private boolean GUIEnabled;

	private MarioAIOptions marioAIOptions;
	private BasicTask task;


	public ProjectManager()
	{
		agent = new ReinforcementLearningAgent();

		marioAIOptions = new MarioAIOptions();
		marioAIOptions.setAgent(agent);
		// Initialize with visualization off
		marioAIOptions.setTimeLimit(100);
		marioAIOptions.setVisualization(false);

		task = new BasicTask(marioAIOptions);
		task.setOptionsAndReset(marioAIOptions);
		
		geneticManager = new GeneticManager();
	}

	/*
	 * Causes numberOfAgents new agents to be 
	 * 	1. Created (via the genetic algorithm) 
	 * 	2. Evaluated on the level
	 * 	3. Stored in the current pool of agents
	 */
	public void genAndRunNewAgents(int numberOfAgents)
	{
		for(int i = 0; i < numberOfAgents; i++)
		{
			task.setOptionsAndReset(marioAIOptions);

			float[] weights = geneticManager.generateNewChild();
			agent.setNNWeights(weights);
			//task.doEpisodes(1,true,1);
			boolean haveValidResult = false;
			while(!haveValidResult)
			{
				task.setOptionsAndReset(marioAIOptions);
				haveValidResult = task.runSingleEpisode(1);
			}

			int score = task.getEnvironment().getEvaluationInfo().computeWeightedFitness();
			System.err.println("	"+(float)score);
			geneticManager.saveAgent(weights, (float) score);
		}
	}
	
	/*
	 * Returns the number of agents are in the current generation
	 */
	public int getGenerationSize()
	{
		return geneticManager.getGenerationSize();
	}

	/*
	 * Returns the current generation number
	 */
	public int getGenerationNumber()
	{
		return geneticManager.getGenerationNumber();
	}

	/*
	 * Purges the current pool of agents, keeping the top 2 agents to generate the next generation.
	 * Increases the generation number by 1.
	 */
	//public int purgeCurrentGeneration(int numberOfAgentsToKeep);
	public boolean purgeCurrentGeneration()
	{
		return geneticManager.clearAllExceptTopTwo();
	}

	public int getLevelSeed()
	{
		return marioAIOptions.getLevelRandSeed();
	}

	public void setLevelSeed(int seed)
	{
		marioAIOptions.setLevelRandSeed(seed);
		task.setOptionsAndReset(marioAIOptions);
	}
	
	/*
	 * Resets generaton count and clears the agent pool, as if we just started anew.
	 */
	public void reset()
	{
		//TODO
	}

	/*
	 * Returns the top scoring agent.
	 */
	public GeneticManagerAgent getBestAgent()
	{
		return geneticManager.getHighestScoringAgent();
	}

	/*
	 * Setters and getters for whether the GUI (of Mario running through the level) is enabled.
	 */
	public boolean getGUIEnabled()
	{
		return marioAIOptions.isVisualization();
	}

	public void setGUIEnabled(boolean enabled)
	{
		marioAIOptions.setVisualization(enabled);
		task.setOptionsAndReset(marioAIOptions);
	}

	/*
	 * Runs a previous agent through the level.
	 */
	public void runPreviousAgent(int agentID)
	{
		GeneticManagerAgent tempAgent = geneticManager.getAgent(agentID);
		
		task.setOptionsAndReset(marioAIOptions);

		float[] weights = tempAgent.getAgentWeights();
		agent.setNNWeights(weights);
		task.runSingleEpisode(1);
	}

	public void runWeights(float[] weights)
	{
		task.setOptionsAndReset(marioAIOptions);
		agent.setNNWeights(weights);
		task.runSingleEpisode(1);
		System.err.println(task.getEnvironment().getEvaluationInfo().computeWeightedFitness());
	}

	public boolean agentExists(int agentID)
	{
		return (geneticManager.getAgent(agentID) != null);
	}

	/*
	 * Returns the agents in the current generation.
	 */
	public List<GeneticManagerAgent> getCurrentGeneration()
	{
		//TODO
		return null;
	}

	/*
	 * Return the top "numberOfAgents" scoring agents of the current generation.
	 */
	public List<GeneticManagerAgent> getBestAgents(int numberOfAgents)
	{
		// TODO
		return null;
	}
}
