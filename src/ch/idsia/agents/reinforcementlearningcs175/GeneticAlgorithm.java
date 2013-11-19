package ch.idsia.agents.reinforcementlearningcs175;
import java.util.ArrayList;
import java.util.Random;
public class GeneticAlgorithm {
	
	
	
	//Swap two arrays of moves
	public Descendents createDescendents(ArrayList<Boolean> a, ArrayList<Boolean> b)
	{
	
		Random r = new Random();
		Descendents d = new Descendents();
		ArrayList<Boolean> temp = new ArrayList<Boolean>();
		int pivot;
		if(a.size() > b.size())
		{
			pivot = r.nextInt(b.size());
			
			for(int i = pivot; i < b.size(); i++)
			{
				temp.set(i, a.get(i));
				a.set(i, b.get(i));
				b.set(i, temp.get(i));
			}
			
			
		}else
		{
			pivot = r.nextInt(a.size());
			for(int i = pivot; i < b.size(); i++)
			{
				temp.set(i, b.get(i));
				b.set(i, b.get(i));
				a.set(i, temp.get(i));
			}
		}
		d.add(a);
		d.add(b);
		return d;
		
		
		
	}
	
	public void Mutate(ArrayList<Boolean> a)
	{
		
	}
	
	

}
