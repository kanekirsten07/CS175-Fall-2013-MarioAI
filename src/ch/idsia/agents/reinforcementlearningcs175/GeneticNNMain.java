package ch.idsia.agents.reinforcementlearningcs175;
import java.io.IOException;
import java.util.Arrays;
import java.io.*;

public final class GeneticNNMain
{
	private static final String outputFile = "result.dat";
	
	public static void main(String[] args)
	{
		IProjectManager projectManager = new ProjectManager();
		
		//Just show last agent TODO: more features
		if (args.length > 0 && args[0].equals("-replay"))
		{
			try
			{
				BufferedReader reader = new BufferedReader(new FileReader(outputFile));
				float[] readweights = new float[Integer.parseInt(reader.readLine())];
				for (int c = 0; c < readweights.length; c++)
					readweights[c] = Float.parseFloat(reader.readLine());
				reader.close();
				projectManager.setGUIEnabled(true);
				projectManager.runWeights(readweights);
			}
			catch (FileNotFoundException e)
			{
				System.out.println(e.toString());
			}
			catch (IOException e)
			{
				System.out.println(e.toString());
			}
			return;
		}
		
		// Config me if you want!!
		int generations_to_run = 40;
		int size_of_generation = 5;
		int level_seed = 30;
		
		projectManager.setLevelSeed(level_seed);
		for(int i = 0; i < generations_to_run; i++)
		{
			//projectManager.setGUIEnabled(false);
			//projectManager.setGUIEnabled(true);
			projectManager.genAndRunNewAgents(size_of_generation);
			System.err.println("Generation " + i + " TopScore: " + projectManager.getBestAgent().getAgentScore());
			if (i < generations_to_run - 1)
				projectManager.purgeCurrentGeneration();
		}
		
		//Save out best weight set
		try
		{
			PrintStream stream = new PrintStream(outputFile);
			float[] bestweights = projectManager.getBestAgent().getAgentWeights();
			stream.println(bestweights.length);
			for (int c = 0; c < bestweights.length; c++)
				stream.println(bestweights[c]);
			stream.close();
		}
		catch (IOException e)
		{
			System.out.println(e.toString());
		}
		
		try
		{
			System.out.println("Press enter to continue...");
			System.in.read();
		}
		catch (IOException e)
		{
			
		}
		
		System.err.println("Best score: " + projectManager.getBestAgent().getAgentScore());
		projectManager.setGUIEnabled(true);
		projectManager.runWeights(projectManager.getBestAgent().getAgentWeights());

		System.exit(0);
	}
}
