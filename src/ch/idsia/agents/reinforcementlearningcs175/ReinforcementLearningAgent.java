package ch.idsia.agents.reinforcementlearningcs175;
import java.util.ArrayList;

import ch.idsia.agents.Agent;
import ch.idsia.agents.LearningAgent;
import ch.idsia.agents.controllers.BasicMarioAIAgent;
import ch.idsia.benchmark.mario.engine.sprites.Mario;
import ch.idsia.benchmark.mario.engine.sprites.Sprite;
import ch.idsia.benchmark.mario.environments.Environment;
import ch.idsia.benchmark.tasks.LearningTask;

//public class ReinforcementLearningAgent extends BasicMarioAIAgent implements Agent{
public class ReinforcementLearningAgent extends BasicMarioAIAgent 
{
	private IEnvironmentProcessor envProcessor;
	private NeuralNet NN;

	public ReinforcementLearningAgent()
	{
		this(GeneticManager.EnvironmentProcessor, GeneticManager.NetWeights);
	}

	public ReinforcementLearningAgent(IEnvironmentProcessor envProcessor, int[] NNlayerSizes) 
	{
		super("Reinforcement Learning Agent");
		reset();
		
		this.envProcessor = envProcessor;
		NNlayerSizes[0] = this.envProcessor.getInputSize();
		this.NN = new NeuralNet(NNlayerSizes);
	}

	public void setNNWeights(float[] weights) throws IllegalArgumentException
	{
		NN.SetWeights(weights);
	}

	public float[] getNNWeights()
	{
		return NN.GetWeights();
	}

	public IEnvironmentProcessor getEnvironmentProcessor()
	{
		return envProcessor;
	}

	public void setEnvironmentProcessor(IEnvironmentProcessor envProcessor)
	{
		this.envProcessor = envProcessor;
	}

	public boolean[] getAction()
	{
		// Determines the action to be taken by 
		// 1. Using the IEnvironmentProcessor to determine the inputs to the NN.
		
		float[] NNInputs = envProcessor.processEnvironment(this, this.marioEgoCol, this.marioEgoRow);

		// 2. Pass the inputs from 1 to the NN.
		
		action = NN.Solve(NNInputs);

		// 3. Return the output of the NN.
		
		return action;
	}
}
