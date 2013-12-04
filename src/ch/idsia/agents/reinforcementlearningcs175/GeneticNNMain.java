package ch.idsia.agents.reinforcementlearningcs175;

public final class GeneticNNMain
{
	public static void main(String[] args)
	{
		IProjectManager projectManager = new ProjectManager();
		
		float[] best = new float[119];
		float best_score = Float.MIN_VALUE;

		// Config me if you want!!
		int generations_to_run = 60;
		int size_of_generation = 15;
		int level_seed = 50;


		projectManager.setLevelSeed(level_seed);
		for(int i = 0; i < generations_to_run; i++)
		{
			//projectManager.setGUIEnabled(false);
			//projectManager.setGUIEnabled(true);
			projectManager.genAndRunNewAgents(size_of_generation);
			System.err.println("Generation " + i + " TopScore: " + projectManager.getBestAgent().getAgentScore());
			if(projectManager.getBestAgent().getAgentScore() > best_score)
			{
				best_score = projectManager.getBestAgent().getAgentScore();
				best = projectManager.getBestAgent().getAgentWeights();
			}
			projectManager.purgeCurrentGeneration();
		}

		System.err.println("Best score: " + best_score);
		projectManager.setGUIEnabled(true);
		projectManager.runWeights(best);

		System.exit(0);
	}
}
