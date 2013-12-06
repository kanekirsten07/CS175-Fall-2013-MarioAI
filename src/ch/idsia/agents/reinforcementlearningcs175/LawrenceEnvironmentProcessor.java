package ch.idsia.agents.reinforcementlearningcs175;

import java.util.ArrayList;

import ch.idsia.agents.controllers.BasicMarioAIAgent;
import ch.idsia.benchmark.mario.engine.sprites.Sprite;

public class LawrenceEnvironmentProcessor implements IEnvironmentProcessor
{
	public float[] processEnvironment(BasicMarioAIAgent agent, int mariocol, int mariorow) 
	{
		int fieldHeight = agent.receptiveFieldHeight;
		int fieldWidth = agent.receptiveFieldWidth;

		float[] toReturn = new float[fieldHeight * (fieldWidth - mariocol)];
		float[][] almostToReturn = new float[fieldWidth-mariocol][fieldHeight];
		boolean[][] hasEnemy = new boolean[fieldWidth-mariocol][fieldHeight];
		boolean[][] isStompable = new boolean[fieldWidth-mariocol][fieldHeight];
		boolean[][] obstructed = new boolean[fieldWidth-mariocol][fieldHeight];
		//System.err.println("========="+toReturn.length);

		for(int row = 0; row < fieldHeight; row++)
		{
			//for(int col = mariocol; col < fieldWidth; col++)
			for(int col = 0; col < fieldWidth-mariocol; col++)
			{
				int cellValue = agent.getReceptiveFieldCellValue(col, row);
				int enemyValue = agent.getReceptiveFieldCellValue(col, row);

				almostToReturn[col][row] = 1;

				isStompable[col][row] = isStompableEnemy(enemyValue);
				hasEnemy[col][row] = isEnemy(enemyValue);
				obstructed[col][row] = isObstructed(cellValue);
			}
		}

		for(int row = 0; row < fieldHeight; row++)
		{
			//for(int col = mariocol; col < fieldWidth; col++)
			for(int col = 0; col < fieldWidth - mariocol; col++)
			{
				if(hasEnemy[col][row])
				{
					if((col - 1) >= mariocol)
					{
						for(int i = 0 ; i < fieldHeight; i++)
						{
							//almostToReturn[col-1][i] += -1;
							almostToReturn[col-1][i] = 0;
						}
					}
					if((col + 1) < fieldWidth)
					{
						for(int i = 0 ; i < fieldHeight; i++)
						{
							//almostToReturn[col+1][i] += -1;
							almostToReturn[col+1][i] = 0;
						}
					}
				}
				/*
				if(isStompable[col][row])
				{
					if((row+1) < fieldHeight)
					{
						almostToReturn[col][row+1] += 1;
					}
				}
				*/
				if(obstructed[col][row])
				{
					/*
					if((row+1) < fieldHeight)
					{
						if(!obstructed[col][row+1])
						{
							almostToReturn[col][row+1] +=1;
						}
					}
					*/
					almostToReturn[col][row] = 0;

				}
			}
		}
		int index = 0;
		for(int row = 0; row < fieldHeight; row++)
		{
			for(int col = 0; col < fieldWidth - mariocol; col++)
			{
				toReturn[index++] = almostToReturn[col][row];
			}
		}
		return toReturn;
	}
	private boolean isStompableEnemy(int enemyValue)
	{
		boolean toReturn = false;
		switch(enemyValue)
		{
			case(Sprite.KIND_BULLET_BILL):
				toReturn = true;
				break;
			case(Sprite.KIND_ENEMY_FLOWER):
				break;
			case(Sprite.KIND_GOOMBA):
				toReturn = true;
				break;
			case(Sprite.KIND_GOOMBA_WINGED):
				toReturn = true;
				break;
			case(Sprite.KIND_WAVE_GOOMBA):
				break;
			case(Sprite.KIND_GREEN_KOOPA):
				toReturn = true;
				break;
			case(Sprite.KIND_GREEN_KOOPA_WINGED):
				toReturn = true;
				break;
			case(Sprite.KIND_RED_KOOPA):
				toReturn = true;
				break;
			case(Sprite.KIND_RED_KOOPA_WINGED):
				toReturn = true;
				break;
			case(Sprite.KIND_SHELL):
				break;
			default:
				break;
		}
		return toReturn;
	}
	private boolean isEnemy(int enemyValue)
	{
		boolean toReturn = false;;
		switch(enemyValue)
		{
			case(Sprite.KIND_BULLET_BILL):
				toReturn = true;
				break;
			case(Sprite.KIND_ENEMY_FLOWER):
				toReturn = true;
				break;
			case(Sprite.KIND_GOOMBA):
				toReturn = true;
				break;
			case(Sprite.KIND_GOOMBA_WINGED):
				toReturn = true;
				break;
			case(Sprite.KIND_WAVE_GOOMBA):
				toReturn = true;
				break;
			case(Sprite.KIND_GREEN_KOOPA):
				toReturn = true;
				break;
			case(Sprite.KIND_GREEN_KOOPA_WINGED):
				toReturn = true;
				break;
			case(Sprite.KIND_RED_KOOPA):
				toReturn = true;
				break;
			case(Sprite.KIND_RED_KOOPA_WINGED):
				toReturn = true;
				break;
			case(Sprite.KIND_SHELL):
				toReturn = true;
				break;
			default:
				break;
		}
		return toReturn;
	}
	private boolean isObstructed(int environmentValue)
	{
		boolean toReturn = false;
		switch(environmentValue)
		{
		       case(-60):
				//grass, unbreakable brick
				toReturn = true;
				break;
		       case(-24):
				// breakable stuff, like a question mark box or brick
				toReturn = true;
				break;
		       case(-85):
				// tube (A whole series of them! No, but really)
				toReturn = true;
				break;
			default:
				break;
		}
		return toReturn;
	}
	private int processEnemyCellValue(int enemyValue)
	{
		int toReturn = 0;
		switch(enemyValue)
		{
			case(Sprite.KIND_BULLET_BILL):
				toReturn++;
				break;
			case(Sprite.KIND_ENEMY_FLOWER):
				toReturn++;
				break;
			case(Sprite.KIND_GOOMBA):
				toReturn++;
				break;
			case(Sprite.KIND_GOOMBA_WINGED):
				toReturn++;
				break;
			case(Sprite.KIND_WAVE_GOOMBA):
				toReturn++;
				break;
			case(Sprite.KIND_GREEN_KOOPA):
				toReturn++;
				break;
			case(Sprite.KIND_GREEN_KOOPA_WINGED):
				toReturn++;
				break;
			case(Sprite.KIND_RED_KOOPA):
				toReturn++;
				break;
			case(Sprite.KIND_RED_KOOPA_WINGED):
				toReturn++;
				break;
			case(Sprite.KIND_SHELL):
				toReturn++;
				break;
			default:
				break;
		}
		return toReturn;
	}

	public int getInputSize()
	{
		return 190;
	}
}
