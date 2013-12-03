package ch.idsia.agents.reinforcementlearningcs175;

import java.util.ArrayList;

import ch.idsia.agents.controllers.BasicMarioAIAgent;
import ch.idsia.benchmark.mario.engine.sprites.Sprite;

public class EnvironmentProcessor implements EnvironmentProcessorInterface{
	
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
						environmentInts.add(col);
						environmentInts.add(row);
						environmentInts.add(1);
						environmentInts.add(0);
						environmentInts.add(0);
						environmentInts.add(1);
						environmentInts.add(100);
						environmentInts.add(0);
						enemyStartInt++;
						break;
					case(Sprite.KIND_ENEMY_FLOWER):
						environmentInts.add(col);
					environmentInts.add(row);
					environmentInts.add(0);
					environmentInts.add(1);
					environmentInts.add(0);
					environmentInts.add(0);
					environmentInts.add(100);
					environmentInts.add(1);
					enemyStartInt++;
						break;
					case(Sprite.KIND_GOOMBA):
						environmentInts.add(col);
					environmentInts.add(row);
					environmentInts.add(1);
					environmentInts.add(1);
					environmentInts.add(0);
					environmentInts.add(0);
					environmentInts.add(10);
					environmentInts.add(0);
					enemyStartInt++;
						break;
				    case(Sprite.KIND_GOOMBA_WINGED):
				    	environmentInts.add(col);
					environmentInts.add(row);
					environmentInts.add(1);
					environmentInts.add(1);
					environmentInts.add(0);
					environmentInts.add(1);
					environmentInts.add(10);
					environmentInts.add(0);
					enemyStartInt++;
					    break;
				    case(Sprite.KIND_WAVE_GOOMBA):
				    	break;
				    case(Sprite.KIND_GREEN_KOOPA):
				    	environmentInts.add(col);
					environmentInts.add(row);
					environmentInts.add(1);
					environmentInts.add(1);
					environmentInts.add(1);
					environmentInts.add(0);
					environmentInts.add(10);
					environmentInts.add(0);
					enemyStartInt++;
					    break;
				    case(Sprite.KIND_GREEN_KOOPA_WINGED):
				    	environmentInts.add(col);
					environmentInts.add(row);
					environmentInts.add(1);
					environmentInts.add(1);
					environmentInts.add(1);
					environmentInts.add(1);
					environmentInts.add(10);
					environmentInts.add(0);
					enemyStartInt++;
				    	break;
				    case(Sprite.KIND_RED_KOOPA):
				    	environmentInts.add(col);
					environmentInts.add(row);
					environmentInts.add(1);
					environmentInts.add(1);
					environmentInts.add(1);
					environmentInts.add(0);
					environmentInts.add(10);
					environmentInts.add(0);
					enemyStartInt++;
				    	break;
				    case(Sprite.KIND_RED_KOOPA_WINGED):
				    	environmentInts.add(col);
					environmentInts.add(row);
					environmentInts.add(1);
					environmentInts.add(1);
					environmentInts.add(1);
					environmentInts.add(1);
					environmentInts.add(10);
					environmentInts.add(0);
					enemyStartInt++;
				    	break;
				    case(Sprite.KIND_SHELL):
				    	environmentInts.add(col);
					environmentInts.add(row);
					environmentInts.add(1);
					environmentInts.add(1);
					environmentInts.add(1);
					environmentInts.add(0);
					environmentInts.add(10);
					environmentInts.add(0);
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
	    	   environmentInts.add(100);
	       	   environmentInts.add(col);
	       	   environmentInts.add(row);
	       	   environmentInts.add(0);
	       	   goodThingsStartInt++;
	    	   break;
	       case(Sprite.KIND_PRINCESS):

	    	   environmentInts.add(500);
	       	   environmentInts.add(col);
	       	   environmentInts.add(row);
	       	   environmentInts.add(0);
	       	   goodThingsStartInt++;
	    	   break;
	       case(Sprite.KIND_GREEN_MUSHROOM):
	    	   environmentInts.add(100);
	       	   environmentInts.add(col);
	       	   environmentInts.add(row);
	       	   environmentInts.add(1);
	       	   goodThingsStartInt++;
	    	   break;
	       case(Sprite.KIND_FIRE_FLOWER):
	    	   environmentInts.add(64);
	       	   environmentInts.add(col);
	       	   environmentInts.add(row);
	       	   environmentInts.add(0);
	       	   goodThingsStartInt++;
	    	   break;
	       case(Sprite.KIND_MUSHROOM):
	    	   environmentInts.add(58);
	       	   environmentInts.add(col);
	       	   environmentInts.add(row);
	       	   environmentInts.add(1);
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
		    	   environmentInts.add(1);
		       		environmentInts.add(0);
		       	   environmentInts.add(col);
		       	   environmentInts.add(row);
		       	   environmentInts.add(0);
		       	   environmentStartInt++;
		    	   break;
		       case(-24):
		    	   // breakable stuff, like a question mark box or brick
		    	   environmentInts.add(1);
	      		environmentInts.add(0);
	      	   environmentInts.add(col);
	      	   environmentInts.add(row);
	      	   environmentInts.add(1);
		       	   environmentStartInt++;
		    	   break;
		       case(-85):
		    	   // tube (A whole series of them! No, but really)
		    	   environmentInts.add(1);
	      		environmentInts.add(0);
	      	   environmentInts.add(col);
	      	   environmentInts.add(row);
	      	   environmentInts.add(0);
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
		


	
	private void fillSectionVoid(int startnum, int numValues)
	{
		for(int i = startnum, j= 0; j < numValues; i++, j++)
		{
			//Infinity for nothing
			environmentInts.add(i, Integer.MAX_VALUE);
		}
	}





}
