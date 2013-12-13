package ch.idsia.agents.reinforcementlearningcs175;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GeneticManager 
{	
	private ArrayList<GeneticManagerAgent> agents;
	private ArrayList<GeneticManagerAgent> tournamentPool;
	private int currentAgentCount;

	private float[] parentWeights1;
	private float[] parentWeights2;
	private GeneticAlgorithm geneticAlgorithm;
	
	// First weight is now just a placeholder, filled in by EnvironmentProcessor later
	public static int[] NetWeights = new int[] { 0, 20, 6 };
	public static IEnvironmentProcessor EnvironmentProcessor = new EnvironmentProcessor();

	private int generationNumber;
	
	private GeneticManagerAgent bestOverallAgent;
	
	/*
	 * Select top x percentage of agents to generate the next generation.
	 */
	private float topPercentageToSave;
	
	public GeneticManager()
	{
		this(5, NeuralNet.TheoreticalSizeOf(NetWeights));
	}

	public GeneticManager(float topPercentageToSave, int initialNumOfWeights)
	{
		geneticAlgorithm = new GeneticAlgorithm();
		parentWeights1 = createRandomWeights(initialNumOfWeights);
		parentWeights2 = createRandomWeights(initialNumOfWeights);

		generationNumber = 1;
		currentAgentCount = 0;
		agents = new ArrayList<GeneticManagerAgent>();
		tournamentPool = new ArrayList<GeneticManagerAgent>();
		this.topPercentageToSave = topPercentageToSave;
		
		bestOverallAgent = null;
	}
	
	public void setInitialParents(float[] weights1, float[] weights2)
	{
		parentWeights1 = weights1;
		parentWeights2 = weights2;
	}
	
	public void Reset()
	{
		currentAgentCount = 0;
		generationNumber = 1;
		agents.clear();
		tournamentPool.clear();
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
	
	public GeneticManagerAgent getBestOverallAgent()
	{
		return bestOverallAgent;
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

	// This was originally used to select the top two agents of a generation.
	// Now, it uses tournament selection.
	public boolean clearAllExceptTopTwo()
	{
		return tournamentSelection();
	}
	
	private boolean tournamentSelection()
	{
		if(agents.size() < 2)
			return false;
		
		// Make sure we have the best agent overall (from all generations) saved.
		GeneticManagerAgent bestAgentInGeneration = getHighestScoringAgent();
		if(bestOverallAgent == null || bestOverallAgent.getAgentScore() < bestAgentInGeneration.getAgentScore())
		{
			bestOverallAgent = bestAgentInGeneration;
		}
		
		// Start adding agents to the tournament pool.		
		tournamentPool.clear();
		for (GeneticManagerAgent a : agents)
		{
			GeneticManagerAgent b = a;
			
			while (b.getAgentID() == a.getAgentID())
			{
				b = agents.get((int)(Math.random() * agents.size()));
			}
			
			if(a.getAgentScore() > b.getAgentScore())
				tournamentPool.add(a);
			else
				tournamentPool.add(b);
		}		
		
		agents.clear();		
		generationNumber++;
		
		return true;
	}
	
	private GeneticManagerAgent getRandomTournamentAgent()
	{
		return tournamentPool.get((int)(Math.random() * tournamentPool.size()));
	}
	
	// Uses parentWeights1 and parentWeights2, and the GeneticAlgorithm instance to generate a new child
	// Simply needs to call the GeneticAlgorithm's createChild method and return the float[]
	public float[] generateNewChild()
	{			
		if(tournamentPool.size() > 0)
		{
			GeneticManagerAgent parentAgent = getRandomTournamentAgent();
			float[] tournamentWeights1 = Arrays.copyOf(parentAgent.getAgentWeights(), parentAgent.getAgentWeights().length);
			//System.err.println("Score 1: " + parentAgent.getAgentScore());
			
			parentAgent = getRandomTournamentAgent();
			float[] tournamentWeights2 = Arrays.copyOf(parentAgent.getAgentWeights(), parentAgent.getAgentWeights().length);
			//System.err.println("Score 2: " + parentAgent.getAgentScore());		
			
			GeneticManagerAgent currentOverallBest = getBestOverallAgent();
			float[] currOverallBestWeights = Arrays.copyOf(currentOverallBest.getAgentWeights(), currentOverallBest.getAgentWeights().length);
			return geneticAlgorithm.createChild(tournamentWeights1, tournamentWeights2);
		}		
		else
		{
			return geneticAlgorithm.createChild(parentWeights1, parentWeights2);
		}
	}

	private float[] createRandomWeights(int size)
	{
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
