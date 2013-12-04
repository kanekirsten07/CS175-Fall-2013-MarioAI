package ch.idsia.agents.reinforcementlearningcs175;
import java.util.ArrayList;
import java.util.Arrays;
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
	
	public GeneticManager()
	{
		//HACK
		this(5, (119 * 300) + (300 * 300) + (300 * 6));
	}

	public GeneticManager(float topPercentageToSave, int initialNumOfWeights)
	{
		geneticAlgorithm = new GeneticAlgorithm();
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
		agents.add(new GeneticManagerAgent(currentAgentCount, Arrays.copyOf(weights,weights.length), score));
		++currentAgentCount;
	}
	
	public GeneticManagerAgent getAgent(int agentID)
	{
		/*
		if(agentID < currentAgentCount)
			return agents.get(agentID);
		else
			return null;
			*/
		for(GeneticManagerAgent temp_agent : agents)
		{
			if(temp_agent.getAgentID() == agentID)
			{
				return temp_agent;
			}
		}
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
		// First find highest score
		float highest_score = agents.get(0).getAgentScore();
		int highest_score_index = 0;
		GeneticManagerAgent top_agent;
		for(int i = 0 ; i < agents.size(); i++)
		{
			if(agents.get(i).getAgentScore() > highest_score)
			{
				highest_score_index = i;
				highest_score = agents.get(i).getAgentScore();
			}
		}
		top_agent = agents.get(highest_score_index);
		agents.remove(highest_score_index);
		parentWeights1 = top_agent.getAgentWeights();
		System.err.println("Top score: "+top_agent.getAgentScore());

		// Find second highest score
		highest_score = agents.get(0).getAgentScore();
		highest_score_index = 0;
		for(int i = 0 ; i < agents.size(); i++)
		{
			if(agents.get(i).getAgentScore() > highest_score)
			{
				highest_score_index = i;
				highest_score = agents.get(i).getAgentScore();
			}
		}
		top_agent = agents.get(highest_score_index);
		agents.remove(highest_score_index);
		parentWeights2 = top_agent.getAgentWeights();
		System.err.println("2nd Top score: "+top_agent.getAgentScore());
		
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
		{
			// Testing different generation of weights turned into a sillier way to generate weights
			switch(c%4)
			{
				case 0:
					tempweights[c] = rand.nextFloat() * -5;
					break;
				case 1:
					tempweights[c] = rand.nextFloat() * 5 - 5;
					break;
				case 2:
					tempweights[c] = rand.nextFloat() * 10 - 5;
					break;
				case 3:
					tempweights[c] = rand.nextFloat() * -20 - 5;
					break;
				default:
					tempweights[c] = rand.nextFloat() * 2 - 2;
			}
		}
		return tempweights;
	}
}
