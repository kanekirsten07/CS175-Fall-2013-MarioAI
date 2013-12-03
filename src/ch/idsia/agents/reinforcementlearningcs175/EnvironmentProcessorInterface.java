package ch.idsia.agents.reinforcementlearningcs175;

import ch.idsia.agents.controllers.BasicMarioAIAgent;

public interface EnvironmentProcessorInterface {
	
	public float[] processEnvironment(BasicMarioAIAgent agent, int col, int row);

}
