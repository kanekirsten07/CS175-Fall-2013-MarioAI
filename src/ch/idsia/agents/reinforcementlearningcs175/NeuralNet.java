package ch.idsia.agents.reinforcementlearningcs175;
import java.lang.IllegalArgumentException;
import java.lang.IllegalStateException;
import java.util.Arrays;

/// This class represents a neural net of arbitrary size.
public class NeuralNet
{
	// Stats, for reference
	private int[] layerSizes;
	private int size;
	
	/// Get the size of the weight vector defining this net
	public int GetSize() { return size; }
	
	public int GetInputDimension() { return layerSizes[0]; }
	public int GetOutputDimension() { return layerSizes[layerSizes.length-1]; }
	
	// Structure
	// First index is for the layer, second is for the node, third is for each weight going into that node
	private float[][][] weights;
	//TODO: optimization: Make a SparseArray class and use it for this for speed and memory
	
	public void SetWeights(float[] weights) throws IllegalArgumentException
	{
		if (weights.length != size)
			throw new IllegalArgumentException("The length of 'weights' must match the size of the network.");
		
		//TODO: break the flat weights array into the three-dimensional this.weights array
	}
	
	/// Create a network with layers of the specified sizes (including input and output layers)
	public NeuralNet(int[] layerSizes)
	{
		this.layerSizes = Arrays.copyOf(layerSizes, layerSizes.length);

		this.size = 0;
		this.weights = new float[layerSizes.length][][];

		for (int layerIndex = 0; layerIndex < weights.length; layerIndex++)
		{
			this.weights[layerIndex] = new float[layerSizes[layerIndex]][];

			for (int nodeIndex = 0; nodeIndex < weights[layerIndex].length; nodeIndex++)
			{
				// If this is a node on the first layer, num of weights is 1, otherwise it is
				// the number of nodes in the previous layer
				int numOfWeightsForNode = (layerIndex == 0) ? 1 : layerSizes[layerIndex - 1];

				this.weights[layerIndex][nodeIndex] = new float[numOfWeightsForNode];
				this.size += numOfWeightsForNode;	
			}
		}
	}
	
	/// Solves the network for the specified inputs
	public boolean[] Solve(float[] input) throws IllegalArgumentException, IllegalStateException
	{
		if (weights == null)
			throw new IllegalStateException("'weights' hasn't been initialized with 'SetWeights' yet.");
		if (input.length != GetInputDimension())
			throw new IllegalArgumentException("Size of 'input' must match the size of the first layer of the net.");
		
		boolean[] result = null;
		for (int c = 1; c < weights.length; c++)
		{
			//TODO: solve layers with SolveLayer and propagate
		}
		
		return result;
	}
	
	private boolean[] SolveLayer(int layer, boolean[] previousLayer) throws Exception
	{
		if (previousLayer.length != weights[layer][0].length)
			throw new Exception("Internal NeuralNet error: previous results vector is the wrong size.");
		
		//TODO: solve each perceptron in this layer based on the passed values
		
		return null;
	}
	
	/// Customizable activation function for the network
	private boolean Threshold(float value)
	{
		return value > 0;
	}
}
