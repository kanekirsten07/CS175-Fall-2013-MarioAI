package ch.idsia.agents.reinforcementlearningcs175;

import java.util.ArrayList;

import ch.idsia.agents.controllers.BasicMarioAIAgent;
import ch.idsia.benchmark.mario.engine.sprites.Sprite;

public class EnvironmentProcessor implements IEnvironmentProcessor{
	
	private ArrayList<Integer> environmentInts ;
	
	public float[] processEnvironment(BasicMarioAIAgent agent, int mariocol, int mariorow) {
		int marioEgoCol = mariocol;
		int marioEgoRow = mariorow;
		float[] environmentFloats;
		/*
		 * EnvironmentInts is an arraylist that can be passed to the neural network that encompasses the environment
		 * around mario
		 * The array consists of 3 sections: Enemies, Good Items, and Environmental Blocks
		 * Enemies: 3 records consisting of these values
		 * rel x
		 * rel y
		 * canstomp
		 * canfireball
		 * dropsshell
		 * isflying
		 * scorefromkilling
		 * ispiranaplant
		 * Good Items: 5 records consisting of these values
		 * pointvalue
		 * relx
		 * rely
		 * canMove
		 * Environment: 15 records consisting of these values
		 * canbebroken
		 * candisappear
		 * relx
		 * rely
		 * canproduceitem
		 */
	
			environmentInts = new ArrayList<Integer>(119);
			int enemyNum;
			int environmentNum;
			int enemyStartInt = 0;
			int goodThingsStartInt = 0;
			int environmentStartInt = 0;
	        //iterate this each time around mario to search for enemies
			outerloop:
			for(int col = marioEgoCol - 20; col < marioEgoCol + 20; col++)
			{			
				for(int row = marioEgoRow; row < marioEgoRow + 20; row++)
				{
					enemyNum = agent.getEnemiesCellValue(row, col) ;	
					
					switch(enemyNum)
					{
					case(Sprite.KIND_BULLET_BILL):
						addEnemy(col, row, 1, 0, 0 , 1, 100, 0);
						enemyStartInt++;
						break;
					case(Sprite.KIND_ENEMY_FLOWER):
						addEnemy(col, row, 0, 1, 0 ,0 , 100, 1);
					enemyStartInt++;
						break;
					case(Sprite.KIND_GOOMBA):
						addEnemy(col, row, 1, 1, 0, 0, 10, 0);
					enemyStartInt++;
						break;
				    case(Sprite.KIND_GOOMBA_WINGED):
				    	addEnemy(col, row, 1, 1, 0, 1, 10, 0);
					enemyStartInt++;
					    break;
				    case(Sprite.KIND_WAVE_GOOMBA):
				    	break;
				    case(Sprite.KIND_GREEN_KOOPA):
				    	addEnemy(col, row, 1, 1, 1, 0, 10, 0);
					enemyStartInt++;
					    break;
				    case(Sprite.KIND_GREEN_KOOPA_WINGED):
				    	addEnemy(col, row, 1, 1, 1, 1, 10, 0);
					enemyStartInt++;
				    	break;
				    case(Sprite.KIND_RED_KOOPA):
				    	addEnemy(col, row, 1, 1, 1, 0, 10, 0);
					enemyStartInt++;
				    	break;
				    case(Sprite.KIND_RED_KOOPA_WINGED):
				    	addEnemy(col, row, 1, 1, 1, 1, 10, 0);
					enemyStartInt++;
				    	break;
				    case(Sprite.KIND_SHELL):
				    	addEnemy(col, row, 1, 1, 1, 0, 10, 0);
					enemyStartInt++;
				    	break;
				    default:
				    	break;
				   }
					if(enemyStartInt == 3)
					{
						break outerloop;
					}
				}		
			}
			
			
			switch(enemyStartInt)
			{
			case(0):
				fillSectionVoid(0, 24);
				break;
			case(1):
				fillSectionVoid(5, 16);
				break;
			case(2):
				fillSectionVoid(9, 8);
			}
	       
	       
	       // Search above, diagonal, to right etc.
	       
	       //Same as above, just with environment elements
			outerloop2:
			for(int col = marioEgoCol - 20; col < marioEgoCol + 20; col++)
			{			
				for(int row = marioEgoRow; row < marioEgoRow + 20; row++)
				{
	       environmentNum = agent.getReceptiveFieldCellValue(marioEgoRow , marioEgoCol);
	       switch(environmentNum)
	       {
	       case(Sprite.KIND_COIN_ANIM):
	    	   addGoodThing(100, col, row, 0);
	       	   goodThingsStartInt++;
	    	   break;
	       case(Sprite.KIND_PRINCESS):
	    	   addGoodThing(500, col, row, 0);
	       	   goodThingsStartInt++;
	    	   break;
	       case(Sprite.KIND_GREEN_MUSHROOM):
	    	   addGoodThing(100, col, row, 1);
	       	   goodThingsStartInt++;
	    	   break;
	       case(Sprite.KIND_FIRE_FLOWER):
	    	   addGoodThing(64, col, row, 0);
	       	   goodThingsStartInt++;
	    	   break;
	       case(Sprite.KIND_MUSHROOM):
	    	   addGoodThing(58, col, row, 1);
	       	   goodThingsStartInt++;
	    	   break;
	       default:
	    	   break;
	       }
	       if(goodThingsStartInt == 5)
	       {
	    	   break outerloop2;
	       }
				}
			}
			
			switch(goodThingsStartInt)
			{
			case(0):
				fillSectionVoid(24, 20);
				break;
			case(1):
				fillSectionVoid(28, 16);
				break;
			case(2):
				fillSectionVoid(32, 12);
				break;
			case(3):
				fillSectionVoid(36, 8);
				break;
			case(4):
				fillSectionVoid(40, 4);
				break;
			}
			
					
			
			outerloop3:
				for(int col = marioEgoCol - 20; col < marioEgoCol + 20; col++)
				{			
					for(int row = marioEgoRow; row < marioEgoRow + 20; row++)
					{
		       environmentNum = agent.getReceptiveFieldCellValue(marioEgoRow , marioEgoCol);
		       switch(environmentNum)
		       {
		       case(-60):
		    	   //grass, unbreakable brick
		    	   addEnviroBlocks(1, 0, col, row, 0);
		       	   environmentStartInt++;
		    	   break;
		       case(-24):
		    	   // breakable stuff, like a question mark box or brick
		    	   addEnviroBlocks(1, 0, col, row, 1);
		       	   environmentStartInt++;
		    	   break;
		       case(-85):
		    	   // tube (A whole series of them! No, but really)
		    	   addEnviroBlocks(1, 0, col, row, 0);
		       	   environmentStartInt++;
		    	   break;
		       default:
		    	   break;
		       }
		    	   if(goodThingsStartInt == 5)
			       {
			    	   break outerloop3;
			       }
		       
					}
				}
			fillSectionVoid(environmentInts.size()-1, 15-environmentStartInt * 5);
			
			int [] myInts = ConvertToFromArray.convertToArray(environmentInts);
			environmentFloats = ConvertToFromArray.convertIntsToFloats(myInts);
		
			return environmentFloats;
		}	
		
	private void addEnemy (int col, int row, int canstomp, int canfireball, int dropsshell, int isflying, int scorefromkilling
			, int ispiranaplant)
	{
		environmentInts.add(col);
		environmentInts.add(row);
		environmentInts.add(canstomp);
		environmentInts.add(canfireball);
		environmentInts.add(dropsshell);
		environmentInts.add(isflying);
		environmentInts.add(scorefromkilling);
		environmentInts.add(ispiranaplant);
	}

	private void addGoodThing(int score, int col, int row, int canMove)
	{
		environmentInts.add(score);
		environmentInts.add(col);
		environmentInts.add(row);
		environmentInts.add(canMove);
	}
	
	private void addEnviroBlocks(int canbebroken, int candisappear, int col, int row, int canproduceitem)
	{
		environmentInts.add(canbebroken);
		environmentInts.add(candisappear);
		environmentInts.add(col);
		environmentInts.add(row);
		environmentInts.add(canproduceitem);
	}
	
	private void fillSectionVoid(int startnum, int numValues)
	{
		for(int i = startnum, j= 0; j < numValues; i++, j++)
		{
			//Infinity for nothing
			environmentInts.add(i, Integer.MAX_VALUE);
		}
	}





}
