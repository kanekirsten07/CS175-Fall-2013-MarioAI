package ch.idsia.agents.reinforcementlearningcs175;
import java.util.ArrayList;

public class GeneticManager 
{
	private ArrayList<GeneticManagerAgent> agents;
	private int currentAgentCount;
	
	public GeneticManager()
	{
		currentAgentCount = 0;
		agents = new ArrayList<GeneticManagerAgent>();
	}
	
	public void saveAgent(float[][][] weights, float score)
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
	
	public float[][][] getAgentWeights(int agentID)
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
}
