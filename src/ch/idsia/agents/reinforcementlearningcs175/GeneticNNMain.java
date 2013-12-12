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

		// Config me if you want!!
		int generations_to_run = 60;
		int size_of_generation = 15;
		int level_seed = 42;
		
		projectManager.setLevelSeed(level_seed);
		
		//Just show last agent TODO: more features
		boolean replay = args.length > 0 && args[0].equals("-replay");
		boolean resume = args.length > 0 && args[0].equals("-resume");
		float[] readweights1 = null;
		float[] readweights2 = null;
		if (replay || resume)
		{
			try
			{
				BufferedReader reader = new BufferedReader(new FileReader(outputFile));
				readweights1 = new float[Integer.parseInt(reader.readLine())];
				for (int c = 0; c < readweights1.length; c++)
					readweights1[c] = Float.parseFloat(reader.readLine());
				readweights2 = new float[Integer.parseInt(reader.readLine())];
				for (int c = 0; c < readweights2.length; c++)
					readweights2[c] = Float.parseFloat(reader.readLine());
				reader.close();
			}
			catch (FileNotFoundException e)
			{
				System.out.println(e.toString());
			}
			catch (IOException e)
			{
				System.out.println(e.toString());
			}
		}
		
		if (replay)
		{
			projectManager.setGUIEnabled(true);
			projectManager.runWeights(readweights1);
			return;
		}
		else if (resume)
		{
			projectManager.setInitialParents(readweights1, readweights2);
		}
		
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
			float[] bestweights = projectManager.getBestOverallAgent().getAgentWeights();
			stream.println(bestweights.length);
			for (int c = 0; c < bestweights.length; c++)
				stream.println(bestweights[c]);
			bestweights = projectManager.getBestAgent().getAgentWeights();
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
		
		System.err.println("Best score: " + projectManager.getBestOverallAgent().getAgentScore());
		projectManager.setGUIEnabled(true);
		projectManager.runWeights(projectManager.getBestOverallAgent().getAgentWeights());

		System.exit(0);
	}
}
