package ch.idsia.agents.reinforcementlearningcs175;

public class ActionScore {
	private int thisscore;
	
	public ActionScore(boolean marioMove, int score)
	{
		thisscore = score;
	}
	
	public void setScore(int x)
	{
		thisscore = x;
	}
	
	public void intScoreBy(int x)
	{
		thisscore += x;
	}
	
	public int getScore()
	{
		return thisscore;
	}
}
