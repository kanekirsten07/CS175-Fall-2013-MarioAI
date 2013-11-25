package ch.idsia.agents.reinforcementlearningcs175;
import java.util.ArrayList;
public class Descendents {
	private ArrayList<float[]> myDescendents ;
	public Descendents()
	{
		myDescendents = new ArrayList<float[]>();
	}
	
	public void add(float[] a)
	{
		myDescendents.add(a);
	}
	
	public ArrayList<float[]> getDescendents()
	{
		return myDescendents;
	}

}
