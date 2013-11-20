package ch.idsia.agents.reinforcementlearningcs175;
import java.util.ArrayList;
public class Descendents {
	private ArrayList<ArrayList<boolean[]>> myDescendents ;
	public Descendents()
	{
		myDescendents = new ArrayList<ArrayList<boolean[]>>();
	}
	
	public void add(ArrayList<boolean[]> a)
	{
		myDescendents.add(a);
	}
	
	public ArrayList<ArrayList<boolean[]>> getDescendents()
	{
		return myDescendents;
	}

}
