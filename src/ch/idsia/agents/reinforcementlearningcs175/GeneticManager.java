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
		GeneticManagerAgent toReturn = agents.get(0);
		for(GeneticManagerAgent agent : agents)
		{
			if(toReturn.getAgentScore() < agent.getAgentScore())
			{
				toReturn = agent;
			}
		}
		
		return toReturn;
	}
	
	public ArrayList<GeneticManagerAgent> getTopAgents()
	{
		ArrayList<GeneticManagerAgent> topAgents = new ArrayList<GeneticManagerAgent>();
		int numAgents = (int)(agents.size() * topPercentageToSave);
		
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
