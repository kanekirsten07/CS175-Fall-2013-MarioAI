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
	
	// Helper function to determine how much of the weights array to access for a specific node/layer.
	// Returns 0 if layer index is not valid
	private int GetNumWeightsInNodeOfLayer(int layerIndex)
	{
		if(layerIndex < 0 || layerIndex >= layerSizes.length)
			return 0;
		if(layerIndex == 0)
			return 1;
		return layerSizes[layerIndex-1];
	}
	
	// Helper function to determine how much of the weights array to access for a specific layer.
	// Returns 0 if layer index is not valid
	private int GetNumNodesInLayer(int layerIndex)
	{
		if(layerIndex < 0 || layerIndex >= layerSizes.length)
			return 0;
		return layerSizes[layerIndex];
	}
	
	/// Create a network with layers of the specified sizes (including input and output layers)
	public NeuralNet(int[] layerSizes)
	{
		this.layerSizes = Arrays.copyOf(layerSizes, layerSizes.length);

		this.size = layerSizes[0];

		int sizeOfLargestLayer = layerSizes[0];

		for (int c = 1; c < layerSizes.length; c++)
		{
			this.size += layerSizes[c] * layerSizes[c-1];
			if(layerSizes[c] > sizeOfLargestLayer)
				sizeOfLargestLayer = layerSizes[c];
		}

		// weights[number of layers][max number of nodes in a layer][max number of inputs to a node]
		// The max number of nodes in a layer is also the max number of inputs to a specific node.
		//
		// i.e. If our our largest layer had 5 nodes, then the most weights a node would have would be
		// 5 (and those would be nodes of the subsequent layer).
		//
		// When computing the actual accessible indices for a given layer, 
		// many times we will have to rely on the layerSizes array to compute how much of the array to access.
		//
		// TODO: Helper functions for getting the "actual" indices we want to access for a given layer/node.
		weights = new float[layerSizes.length][sizeOfLargestLayer][sizeOfLargestLayer];
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
