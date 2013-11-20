package ch.idsia.agents.reinforcementlearningcs175;
import java.util.ArrayList;

import ch.idsia.agents.Agent;
import ch.idsia.agents.LearningAgent;
import ch.idsia.agents.controllers.BasicMarioAIAgent;
import ch.idsia.benchmark.mario.engine.sprites.Mario;
import ch.idsia.benchmark.mario.engine.sprites.Sprite;
import ch.idsia.benchmark.mario.environments.Environment;
import ch.idsia.benchmark.tasks.LearningTask;

public class ReinforcementLearningAgent extends BasicMarioAIAgent implements Agent, LearningAgent {

	public ReinforcementLearningAgent() {
		super("Reinforcement Learning Agent");
		reset();
	}


	private String name;
	int zLevelScene = 1;
	int zLevelEnemies = 0;
	//To be used to store the moves mario makes, can then manipulate to implement genetic algorithm
	private ArrayList<Boolean> possibleMoves = new ArrayList<Boolean>();

	public void getImmediateObservation()
	{	
		int enemyNum;
		int environmentNum;
		
        //iterate this each time around mario to search for enemies
		
		for(int col = marioEgoCol - 20; col < marioEgoCol + 20; col++)
		{			
			for(int row = marioEgoRow; row < marioEgoRow + 20; row++)
			{
				enemyNum = getEnemiesCellValue(row, col) ;	
				
				switch(enemyNum)
				{
				case(Sprite.KIND_BULLET_BILL):
					break;
				case(Sprite.KIND_ENEMY_FLOWER):
					break;
				case(Sprite.KIND_GOOMBA):
					System.out.println("Goomba detected at " + col + " " + row);
					
					if(!action[Mario.KEY_SPEED])
						action[Mario.KEY_SPEED] = true;
					else
						action[Mario.KEY_SPEED] = false;
					break;
			    case(Sprite.KIND_GOOMBA_WINGED):
				    break;
			    case(Sprite.KIND_WAVE_GOOMBA):
			    	break;
			    case(Sprite.KIND_GREEN_KOOPA):
				    break;
			    case(Sprite.KIND_GREEN_KOOPA_WINGED):
			    	break;
			    case(Sprite.KIND_RED_KOOPA):
			    	break;
			    case(Sprite.KIND_RED_KOOPA_WINGED):
			    	break;
			    case(Sprite.KIND_SHELL):
			    	break;
			    default:
			    	break;
			   }
			}		
		}
		
       
       
       // Search above, diagonal, to right etc.
       
       //Same as above, just with environment elements
       environmentNum = getReceptiveFieldCellValue(marioEgoRow , marioEgoCol);
       switch(environmentNum)
       {
       case(Sprite.KIND_COIN_ANIM):
    	   break;
       case(Sprite.KIND_NONE):
    	   break;
       case(Sprite.KIND_PRINCESS):
    	   break;
       }
	}

	
	public String getName() { return name; }

	public void setName(String Name) { this.name = Name; }



public boolean[] getAction()
{
    //action[Mario.KEY_SPEED] = action[Mario.KEY_JUMP] = isMarioAbleToJump || !isMarioOnGround;

	action[Mario.KEY_RIGHT] = true;
	
	action[Mario.KEY_JUMP] = isMarioAbleToJump || !isMarioOnGround;
	
    getImmediateObservation();
    
    return action;
    
	
    //
}

public void reset()
{
	action = new boolean[Environment.numberOfKeys];
    action[Mario.KEY_SPEED] = false;
}


@Override
public void learn() {
	// TODO Auto-generated method stub
	
}


@Override
public void giveReward(float reward) {
	// TODO Auto-generated method stub
	
}


@Override
public void newEpisode() {
	// TODO Auto-generated method stub
	
}


@Override
public void setLearningTask(LearningTask learningTask) {
	// TODO Auto-generated method stub
	
}


@Override
public void setEvaluationQuota(long num) {
	// TODO Auto-generated method stub
	
}


@Override
public Agent getBestAgent() {
	// TODO Auto-generated method stub
	return null;
}


@Override
public void init() {
	// TODO Auto-generated method stub
	
}

}
