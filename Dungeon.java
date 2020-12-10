package dungeon;

/**
 * Title: Dungeon.java
 *
 * Description: Driver file for Heroes and Monsters project
 *
 * Copyright:    Copyright (c) 2001
 * Company: Code Dogs Inc.
 * I.M. Knurdy
 *
 * History:
 *  11/4/2001: Wrote program
 *    --created DungeonCharacter class
 *    --created Hero class
 *    --created Monster class
 *    --had Hero battle Monster
 *    --fixed attack quirks (dead monster can no longer attack)
 *    --made Hero and Monster abstract
 *    --created Warrior
 *    --created Ogre
 *    --made Warrior and Ogre battle
 *    --added battleChoices to Hero
 *    --added special skill to Warrior
 *    --made Warrior and Ogre battle
 *    --created Sorceress
 *    --created Thief
 *    --created Skeleton
 *    --created Gremlin
 *    --added game play features to Dungeon.java (this file)
 *  11/27/2001: Finished documenting program
 * version 1.0
 */



/*
  This class creates the array of rooms, places the entrance, exit and pillars. 
  Maintains location of hero
  Contains to string that builds a string containing all dungeon info. 
*/
public class Dungeon
{
    public static Room[][] playGround;
    public static Room heroLocation;
    static int curX;
    static int curY;
    
    public static void createDungeon() {
    	playGround = new Room[7][7];
   
    	for(int x = 0; x < 7; x++)
    	{
    		for(int y = 0; y< 7; y++)
    		{
    			
    			playGround[x][y] = new Room();
    			
    			if(y ==0 || x==0)
    			{
    				playGround[x][y].roomDescription = "*";
    			}
    			if(y==6 || x==6)
    			{
    				playGround[x][y].roomDescription = "*";
    			}
    		}
    	}
    	
    	//make entrance and exit
    	playGround[1][1].roomDescription = "I";
    	playGround[5][5].roomDescription = "O";
    	//make monster rooms
    	playGround[3][2].roomDescription = "X";
    	playGround[1][5].roomDescription = "X";
    	playGround[3][4].roomDescription = "X";
    	playGround[5][4].roomDescription = "X";
    	playGround[4][4].roomDescription = "X";
    	//make potion rooms
    	playGround[1][3].roomDescription = "H";
    	playGround[4][2].roomDescription = "H";
    	playGround[5][3].roomDescription = "H";
    	playGround[3][4].roomDescription = "H";
    	//make pits
    	playGround[2][4].roomDescription = "P";
    	playGround[3][1].roomDescription = "P";
    	playGround[4][5].roomDescription = "P";
    	//make pillars
    	playGround[1][4].roomDescription = "F";
    	playGround[2][1].roomDescription = "F";
    	playGround[3][5].roomDescription = "F";
    	playGround[5][1].roomDescription = "F";
    	heroLocation = playGround[1][1];
    	curX = 1;
    	curY = 1;
    	
    }
    public static void printlocation() {
    	if(DungeonAdventure.theHero.isAlive())
    	{
    	String topRoom = playGround[curX][curY-1].roomDescription;
    	String leftRoom = playGround[curX-1][curY].roomDescription;
    	String rightRoom = playGround[curX+1][curY].roomDescription;
    	String bottomRoom = playGround[curX][curY+1].roomDescription;
    	String currentRoom = playGround[curX][curY].roomDescription;
    	
    	if(!topRoom.equals("*"))
    	{
    		topRoom = "-";
    	}
    	if(!bottomRoom.equals("*"))
    	{
    		bottomRoom = "-";
    	}
    	if(!rightRoom.equals("*"))
    	{
    		rightRoom = "|";
    	}
    	if(!leftRoom.equals("*"))
    	{
    		leftRoom = "|";
    	}
		System.out.println("  " + topRoom + "\n" + leftRoom + " " + currentRoom + " " + rightRoom + "\n  " + bottomRoom);
    	}
		
	}
    

    
    @SuppressWarnings("unused")
	private static void printDungeon() {
    	
    	String dungeonPrint = "";

		    	for(int x = 0; x < 7; x++)
		    	{
		    		dungeonPrint += "\n";
		    		
		    		for(int y = 0; y< 7; y++)
		    		{
		    			
		    			if((y == 6 || y == 0 || x == 0 || x == 6))
		    			{
		    				dungeonPrint += "*";
		    			}
		    			else
		    			{
		    				dungeonPrint += playGround[x][y].roomDescription;
		    			}
		    			
		    		}
		    	}
    		
     	System.out.println(dungeonPrint);
	}



}//end Dungeon class