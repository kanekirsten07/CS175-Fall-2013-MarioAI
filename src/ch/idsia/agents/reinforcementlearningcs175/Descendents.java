package ch.idsia.agents.reinforcementlearningcs175;
import java.util.ArrayList;
public class Descendents {
	private ArrayList<ArrayList<Boolean>> myDescendents ;
	public Descendents()
	{
		myDescendents = new ArrayList<ArrayList<Boolean>>();
	}
	
	public void add(ArrayList<Boolean> a)
	{
		myDescendents.add(a);
	}
	
	public ArrayList<ArrayList<Boolean>> getDescendents()
	{
		return myDescendents;
	}

}
