package ch.idsia.agents.reinforcementlearningcs175;

public class GeneticManagerAgent 
{
	private int 		agentID;
	private float[]		agentWeights;
	private float		agentScore;
	
	public GeneticManagerAgent(int agentID, float[] weights, float score)
	{
		this.agentID = agentID;
		this.agentWeights = weights;
		this.agentScore = score;
	}

	public float[] getAgentWeights() 
	{
		return agentWeights;
	}

	public float getAgentScore() 
	{
		return agentScore;
	}

	public int getAgentID() 
	{
		return agentID;
	}
}
