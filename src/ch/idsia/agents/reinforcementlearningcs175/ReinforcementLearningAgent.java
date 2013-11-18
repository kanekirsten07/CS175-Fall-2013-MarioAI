package ch.idsia.agents.reinforcementlearningcs175;
import ch.idsia.agents.Agent;
import ch.idsia.agents.controllers.BasicMarioAIAgent;
import ch.idsia.benchmark.mario.engine.sprites.Mario;
import ch.idsia.benchmark.mario.environments.Environment;

public class ReinforcementLearningAgent implements Agent{

	private String name;
	
	
	public void integrateObservation(Environment environment)
	{
	  

	   

	    

	}

	
	public void giveIntermediateReward(float intermediateReward) {
		// TODO Auto-generated method stub
		
	}

	
	public void setObservationDetails(int rfWidth, int rfHeight, int egoRow,
			int egoCol) {
		// TODO Auto-generated method stub
		
	}

	
	public String getName() { return name; }

	public void setName(String Name) { this.name = Name; }


	@Override
	public boolean[] getAction() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

}
