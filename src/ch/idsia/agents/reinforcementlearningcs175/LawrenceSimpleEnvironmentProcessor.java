package ch.idsia.agents.reinforcementlearningcs175;

import java.util.ArrayList;

import ch.idsia.agents.controllers.BasicMarioAIAgent;
import ch.idsia.benchmark.mario.engine.sprites.Sprite;

public class LawrenceSimpleEnvironmentProcessor implements IEnvironmentProcessor
{
	public float[] processEnvironment(BasicMarioAIAgent agent, int mariocol, int mariorow) 
	{
		float[] toReturn = new float[2];
		toReturn[0] = (agent.isMarioAbleToShoot) ? 1 : 0;
		toReturn[1] = (agent.isMarioAbleToJump) ? 1 : 0;
		return toReturn;
	}

	public int getInputSize()
	{
		return 2;
	}
}
