package ch.idsia.agents.reinforcementlearningcs175;
import java.util.ArrayList;
import java.util.Random;

public class GeneticManager 
{	
	private ArrayList<GeneticManagerAgent> agents;
	private int currentAgentCount;

	private float[] parentWeights1;
	private float[] parentWeights2;
	private GeneticAlgorithm geneticAlgorithm;

	private int generationNumber;

	/*
	 * Select top x percentage of agents to generate the next generation.
	 */
	private float topPercentageToSave;
	
	public GeneticManager(float topPercentageToSave, int initialNumOfWeights)
	{
		parentWeights1 = createRandomWeights(initialNumOfWeights);
		parentWeights2 = createRandomWeights(initialNumOfWeights);

		generationNumber = 1;
		currentAgentCount = 0;
		agents = new ArrayList<GeneticManagerAgent>();
		this.topPercentageToSave = topPercentageToSave;
	}
	
	public void Reset()
	{
		currentAgentCount = 0;
		generationNumber = 1;
		agents.clear();
	}

	public int getGenerationNumber()
	{
		return generationNumber;
	}
	
	public void saveAgent(float[] weights, float score)
	{
		agents.add(new GeneticManagerAgent(currentAgentCount, weights, score));
		++currentAgentCount;
	}
	
	public GeneticManagerAgent getAgent(int agentID)
	{
		if(agentID < currentAgentCount)
			return agents.get(agentID);
		else
			return null;
	}
	
	public float[] getAgentWeights(int agentID)
	{
		GeneticManagerAgent agent = getAgent(agentID);
		if(agent != null)		
			return agent.getAgentWeights();		
		else
			return null;
	}
	
	public float getAgentScore(int agentID)
	{
		GeneticManagerAgent agent = getAgent(agentID);
		if(agent != null)		
			return agent.getAgentScore();		
		else
			return -1;
	}
	
	public GeneticManagerAgent getHighestScoringAgent()
	{
		return getAgent(true);
	}
	
	public GeneticManagerAgent getLowestScoringAgent()
	{
		return getAgent(false);
	}
	
	private GeneticManagerAgent getAgent(boolean getHighest)
	{
		GeneticManagerAgent toReturn = agents.get(0);
		
		for(GeneticManagerAgent agent : agents)
		{
			if(getHighest)
			{
				if(toReturn.getAgentScore() < agent.getAgentScore())
				{
					toReturn = agent;
				}
			}
			else
			{
				if(toReturn.getAgentScore() > agent.getAgentScore())
				{
					toReturn = agent;
				}
			}
		}
		
		return toReturn;
	}
	
	public float getAverageScore()
	{
		float total = 0;
		
		for(GeneticManagerAgent agent : agents)
		{
			total += agent.getAgentScore();
		}
		
		return total / agents.size();
	}
	
	public ArrayList<GeneticManagerAgent> getTopAgents()
	{
		int numAgents = (int)(agents.size() * topPercentageToSave);
		
		return getTopXAgents(numAgents);
	}

	public int getGenerationSize()
	{
		return agents.size();
	}
	
	public ArrayList<GeneticManagerAgent> getTopXAgents(float numAgents)
	{
		ArrayList<GeneticManagerAgent> topAgents = new ArrayList<GeneticManagerAgent>();
		
		while(topAgents.size() < numAgents && !agents.isEmpty())
		{
			GeneticManagerAgent topAgent = getHighestScoringAgent();
			topAgents.add(topAgent);
			agents.remove(topAgent.getAgentID());			
		}
		
		return topAgents;
	}
	
	public float getTopPercentageToSave()
	{
		return topPercentageToSave;
	}

	public void setTopPercentageToSave(float topPercentageToSave) 
	{
		this.topPercentageToSave = topPercentageToSave;
	}

	// Take the top two scoring agents, and assign their weights to parentWeights1 and parentWeights2.
	// Then clears the set of agents.
	// Increments the generation number by 1.
	// Returns true if succeeded, false if failed (less than two agents in the set)
	public boolean clearAllExceptTopTwo()
	{
		if(agents.size() < 2)
		{
			return false;
		}
		GeneticManagerAgent topAgent1 = getHighestScoringAgent();
		agents.remove(topAgent1.getAgentID());
		parentWeights1 = topAgent1.getAgentWeights();

		GeneticManagerAgent topAgent2 = getHighestScoringAgent();
		agents.remove(topAgent2.getAgentID());
		parentWeights2 = topAgent2.getAgentWeights();

		agents.clear();
		generationNumber++;
		return true;
	}

	// Uses parentWeights1 and parentWeights2, and the GeneticAlgorithm instance to generate a new child
	// Simply needs to call the GeneticAlgorithm's createChild method and return the float[]
	public float[] generateNewChild()
	{
		return geneticAlgorithm.createChild(parentWeights1, parentWeights2);
	}

	private float[] createRandomWeights(int size)
	{
		//HACK: do this somewhere else
		//Randomize weights
		float[] tempweights = new float[size];
		Random rand = new Random(System.currentTimeMillis());
		for (int c = 0; c < size; c++)
			tempweights[c] = rand.nextFloat() * 10 - 5;
		return tempweights;
	}
}
