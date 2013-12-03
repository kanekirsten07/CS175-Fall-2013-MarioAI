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
	
	/// Sets the weights for the NN from a 1D array representation.
	public void SetWeights(float[] iweights) throws IllegalArgumentException
	{
		if (iweights.length != size)
			throw new IllegalArgumentException("The length of 'iweights' must match the size of the network " + 
			"(Expected: " + size + ", Got: " + iweights.length + ").");
		
		//Break the 1D array into the 3D structure we're actually using
		int currentIWeight = 0;
		for (int layer = 1; layer < layerSizes.length; layer++)
		{
			int numOfWeightsForNode = layerSizes[layer - 1];
			for (int node = 0; node < layerSizes[layer]; node++)
			{
				for (int weight = 0; weight < numOfWeightsForNode; weight++)
				{
					weights[layer][node][weight] = iweights[currentIWeight];
					currentIWeight++;
				}
			}
		}
	}
	
	/// Returns the 1D array representation of all the weights in the NN.
	public float[] GetWeights()
	{
		//Convert the weights[][][] array into a 1D float array
		float[] oweights = new float[size];
		
		int currentOWeight = 0;
		for (int layer = 1; layer < layerSizes.length; layer++)
		{
			int numOfWeightsForNode = layerSizes[layer - 1];
			for (int node = 0; node < layerSizes[layer]; node++)
			{
				for (int weight = 0; weight < numOfWeightsForNode; weight++)
				{
					oweights[currentOWeight] = weights[layer][node][weight];
					currentOWeight++;
				}
			}
		}
		
		return oweights;
	}
	
	/// Create a network with layers of the specified sizes (including input and output layers)
	public NeuralNet(int[] layerSizes)
	{
		this.layerSizes = Arrays.copyOf(layerSizes, layerSizes.length);
		
		//Create weights data structure
		this.size = 0;
		this.weights = new float[layerSizes.length][][];
		for (int layerIndex = 0; layerIndex < weights.length; layerIndex++)
		{
			this.weights[layerIndex] = new float[layerSizes[layerIndex]][];
			
			if (layerIndex > 0) //Input layer has no weights
			{
				int numOfWeightsForNode = layerSizes[layerIndex - 1];
				for (int nodeIndex = 0; nodeIndex < weights[layerIndex].length; nodeIndex++)
				{
					this.weights[layerIndex][nodeIndex] = new float[numOfWeightsForNode];
				}
				this.size += numOfWeightsForNode * weights[layerIndex].length;
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
