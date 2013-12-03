package ch.idsia.agents.reinforcementlearningcs175;
import org.junit.*;
import java.util.Arrays;

import static org.junit.Assert.*;

public class NNUnitTests
{
	@Test
	public void TestGetSetWeights()
	{
		int[] dim = new int[] { 4, 10, 10, 4 };
		NeuralNet net = new NeuralNet(dim);
		
		float[] weights = new float[10*4 + 10*10 + 4*10];
		for (int c = 0; c < weights.length; c++)
			weights[c] = c;
		
		net.SetWeights(weights);
		
		assertTrue(Arrays.equals(weights, net.GetWeights()));
	}
}