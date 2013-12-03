package ch.idsia.agents.reinforcementlearningcs175;
import java.util.ArrayList;
import java.util.Random;
public class GeneticAlgorithm {
	
	private float mutationValue = 0.1f;
	private int randAdd = 0;
	
	public float[] createChild(float[] a, float[] b)
	{
		Descendents d = this.createDescendents(a, b);
		Mutate(a);	
		return a;
	}


	//Swap two arrays of moves
	public Descendents createDescendents(float[] a, float[] b)
	{
	
		Random r = new Random();
		Descendents d = new Descendents();
		
		int pivot;
		if(a.length > b.length)
		{
			float[] temp = new float[b.length];
			pivot = r.nextInt(b.length);
			
			for(int i = pivot; i < b.length; i++)
			{
				temp[i] = a[i];
				a[i] = b[i];
				
				b[i] = temp[i];
			}
			
			
		}else
		{
			float[] temp = new float[a.length];
			pivot = r.nextInt(a.length);
			for(int i = pivot; i < b.length; i++)
			{
				
				temp[i] = b[i];
				
				b[i] = a[i];
				
				a[i] = temp[i];
			}
		}
		
		/*
		 * I made the Descendents class an arraylist that holds two float arrays of floats. To retrieve them you just need to
		 * get the first two objects in the returned arraylist
		 */
		d.add(a);
		d.add(b);
		return d;
		
		
		
	}
	
	public void Mutate(float[] a)
	{
		
		
		for(int i = 0; i < a.length; i++)
		{
			if(calculateMutation())
			{
				a[i] = a[i] * mutationValue + randAdd;
			}
		}
		
		
	}
	
	private void flipCoin()
	{
		Random r = new Random();
		int r1 = r.nextInt(1);
		if(r1 == 1)
		{
			randAdd = -randAdd;
		}
	}
	
	private boolean calculateMutation()
	{
		Random r = new Random();
		int r1 = r.nextInt(100);
		
		if(r1 > 0 && r1 < 10)
		{
			randAdd = r1;
			flipCoin();
			return true;
		}else
		{
			return false;
		}
	}
	
	

}
