package ch.idsia.agents.reinforcementlearningcs175;

import java.util.ArrayList;

import ch.idsia.agents.controllers.BasicMarioAIAgent;
import ch.idsia.benchmark.mario.engine.sprites.Sprite;

public class LawrenceEnvironmentProcessor implements IEnvironmentProcessor
{
	public float[] processEnvironment(BasicMarioAIAgent agent, int mariocol, int mariorow) 
	{
		float[] toReturn = new float[4];
		toReturn[0] = 0;
		toReturn[1] = 0;
		toReturn[2] = 0;
		toReturn[3] = 0;

		for(int i = 3; i >= 0; i--)
		{
			int block1 = agent.getReceptiveFieldCellValue(mariocol-i, mariorow + 1);
			int block2 = agent.getReceptiveFieldCellValue(mariocol-(i+1), mariorow + 1);
			if(passable(block1) && passable(block2))
			{
				toReturn[0] = 0;
				toReturn[1] = 0;
				toReturn[2] = 0;
				toReturn[3] = 0;

				toReturn[i] = 1;
			}
		}
		return toReturn;

	}
	private boolean passable(int id)
	{
		if(id == 0)
			return true;
		if(id == 2)
			return true;
		if(id == -62)
			return true;

		return false;
	}

	private boolean isEnemy(int id)
	{
		if(id == 0)
			return false;
		if(id == 25)
			return false;

		return true;
	}

	public int getInputSize()
	{
		return 4;
	}
}
