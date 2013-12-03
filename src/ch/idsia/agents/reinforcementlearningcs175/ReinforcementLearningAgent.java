package ch.idsia.agents.reinforcementlearningcs175;
import java.util.ArrayList;

import ch.idsia.agents.Agent;
import ch.idsia.agents.LearningAgent;
import ch.idsia.agents.controllers.BasicMarioAIAgent;
import ch.idsia.benchmark.mario.engine.sprites.Mario;
import ch.idsia.benchmark.mario.engine.sprites.Sprite;
import ch.idsia.benchmark.mario.environments.Environment;
import ch.idsia.benchmark.tasks.LearningTask;

public class ReinforcementLearningAgent extends BasicMarioAIAgent implements Agent{

	public ReinforcementLearningAgent() {
		super("Reinforcement Learning Agent");
		reset();
		 e = new EnvironmentProcessor();
	}

	public static final int KIND_BLOCK = -24;
	public static final int KIND_GRASS = -60;
	private String name;
	int zLevelScene = 1;
	int zLevelEnemies = 0;
	EnvironmentProcessor e;
	//To be used to store the moves mario makes, can then manipulate to implement genetic algorithm
		
	
	public String getName() { return name; }

	public void setName(String Name) { this.name = Name; }



	public boolean[] getAction()
	{
	    //action[Mario.KEY_SPEED] = action[Mario.KEY_JUMP] = isMarioAbleToJump || !isMarioOnGround;

		action[Mario.KEY_RIGHT] = true;
		
		action[Mario.KEY_JUMP] = isMarioAbleToJump || !isMarioOnGround;
		//System.out.println( getReceptiveFieldCellValue(marioEgoRow +1 , marioEgoCol));
	    e.processEnvironment(this, this.marioEgoCol, this.marioEgoRow);
	    
	    return action;
	    
		
	    //
	}

	public void reset()
	{
		action = new boolean[Environment.numberOfKeys];
	    action[Mario.KEY_SPEED] = false;
	}
}
