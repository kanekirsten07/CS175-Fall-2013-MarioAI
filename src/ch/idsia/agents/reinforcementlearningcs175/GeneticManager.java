package ch.idsia.agents.reinforcementlearningcs175;
import java.util.ArrayList;

public class GeneticManager 
{	
	private ArrayList<GeneticManagerAgent> agents;
	private int currentAgentCount;
	
	/*
	 * Select top x percentage of agents to generate the next generation.
	 */
	private float topPercentageToSave;
	
	public GeneticManager(float topPercentageToSave)
	{
		currentAgentCount = 0;
		agents = new ArrayList<GeneticManagerAgent>();
		this.topPercentageToSave = topPercentageToSave;
	}
	
	public void Reset()
	{
		currentAgentCount = 0;
		agents.clear();
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
}
