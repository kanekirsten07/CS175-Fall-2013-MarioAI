package ch.idsia.agents.reinforcementlearningcs175;

import java.util.ArrayList;

import ch.idsia.agents.controllers.BasicMarioAIAgent;
import ch.idsia.benchmark.mario.engine.sprites.Sprite;

public class SimpleGridEnvironmentProcessor implements IEnvironmentProcessor
{
	private float[] environment;
	private float[] enemies;
	
	public int getInputSize()
	{
		return 19*19*2 + 4;
	}
	
	public float[] processEnvironment(BasicMarioAIAgent agent, int col, int row)
	{
		environment = new float[agent.receptiveFieldWidth * agent.receptiveFieldHeight];
		enemies = new float[agent.receptiveFieldWidth * agent.receptiveFieldHeight];
		for (int x = 0; x < agent.receptiveFieldWidth; x++)
		{
			for (int y = 0; y < agent.receptiveFieldHeight; y++)
			{
				environment[x + y * agent.receptiveFieldWidth] = agent.getReceptiveFieldCellValue(x, y) != 0 ? 1 : 0;
				enemies[x + y * agent.receptiveFieldWidth] = agent.getEnemiesCellValue(x, y) != 0 ? 1 : 0;
			}
		}
		
		float[] ret = new float[getInputSize()];
		for (int c = 0; c < environment.length; c++)
		{
			ret[c] = environment[c];
			ret[c + environment.length] = enemies[c];
		}
		
		ret[2 * agent.receptiveFieldWidth * agent.receptiveFieldHeight + 0] = agent.isMarioOnGround ? 1 : 0;
		ret[agent.receptiveFieldWidth * agent.receptiveFieldHeight + 1] = agent.isMarioAbleToShoot ? 1 : 0;
		ret[agent.receptiveFieldWidth * agent.receptiveFieldHeight + 2] = agent.isMarioAbleToJump ? 1 : 0;
		ret[2 * agent.receptiveFieldWidth * agent.receptiveFieldHeight + 3] = agent.isMarioCarrying ? 1 : 0;
		
		return ret;
	}
}