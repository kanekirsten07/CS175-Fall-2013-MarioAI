package ch.idsia.agents.reinforcementlearningcs175;
import java.util.ArrayList;
public class ConvertToFromArray {
	
	public static int[] convertToArray(ArrayList<Integer> a)
	{
		int [] temp = new int[a.size()];
			for(int i = 0; i < a.size(); i++)
			{
				temp[i] = a.get(i);
			}
			
			return temp;
	}

	
	public static ArrayList<Integer> convertToArrayList(int [] a)
	{
		ArrayList<Integer> temp = new ArrayList<Integer>();
		for(int i = 0; i < a.length; i++)
		{
			temp.set(i, a[i]);
		}
		return temp;
	}
	
	public static float[] convertIntsToFloats(int [] input)
	{
		float[] output = new float[input.length];
		for(int i = 0; i < input.length; i++)
		{
			output[i] = input[i];
		}
		return output;
	}
}
