package ch.idsia.agents.reinforcementlearningcs175;

import ch.idsia.agents.controllers.BasicMarioAIAgent;

public interface IEnvironmentProcessor
{
	public float[] processEnvironment(BasicMarioAIAgent agent, int col, int row);
	
	/// Returns the size of the input array we output
	public int getInputSize();
}
