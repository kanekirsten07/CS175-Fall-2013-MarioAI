package ch.idsia.agents.reinforcementlearningcs175;

public final class GeneticNNMain
{
	public static void main(String[] args)
	{
		IProjectManager projectManager = new ProjectManager();
		
		float[] best = new float[119];
		float best_score = Float.MIN_VALUE;

		projectManager.setLevelSeed(50);
		for(int i = 0; i < 40; i++)
		{
			//projectManager.setGUIEnabled(false);
			//projectManager.setGUIEnabled(true);
			projectManager.genAndRunNewAgents(15);
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
