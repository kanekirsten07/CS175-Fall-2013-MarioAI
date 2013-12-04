package ch.idsia.agents.reinforcementlearningcs175;

public final class GeneticNNMain
{
	public static void main(String[] args)
	{
		IProjectManager projectManager = new ProjectManager();
		

		for(int i = 0; i < 10; i++)
		{
			projectManager.setGUIEnabled(false);
			projectManager.genAndRunNewAgents(20);
			System.err.println("Generation " + i + " TopScore: " + projectManager.getBestAgent().getAgentScore());
			projectManager.purgeCurrentGeneration();
		}
		projectManager.genAndRunNewAgents(1);
		projectManager.setGUIEnabled(true);
		projectManager.runPreviousAgent(projectManager.getBestAgent().getAgentID());

		System.exit(0);
	}
}
