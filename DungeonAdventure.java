package dungeon;
import java.util.Scanner;
public class DungeonAdventure {
	public static Hero theHero;
	private static Monster theMonster;
	private static boolean inBattle = false;
	private static boolean endGame = false;
	
	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		System.out.println("WELCOME TO THE DUNGEON ADVENTURE WHERE YOU MUST FIND ALL FOUR OO PILLARS AND THE EXIT.\nTO MOVE USE KEYS N,S,E,W AND TO USE POTIONS PRESS P DURING EXPLORING OR \n3 DURING BATTLE, IT WILL NOT COUNT AS A TURN DURING ATTACKS, GOOD LUCK!\n\n");

		
			Dungeon.createDungeon();
			theHero = userChooseHero();//factories. 
		    theMonster = generateRandomMonster();
		    moveCharacter();

	}
	
		private static void moveCharacter() {
			String choice;
			Dungeon.printlocation();
			System.out.println("Move The Hero Using S For Down/South, N For Up/North, E For Left/East, W For Right/West, P for use Health Potion");
			while(!inBattle & theHero.isAlive() & !endGame)
				{
	
					System.out.println("Move Character(N.S.E.W.P):");
					@SuppressWarnings("resource")
					Scanner kb = new Scanner(System.in);
					choice = kb.nextLine().toUpperCase();
					
					if(choice.equals("N"))
					{
						if(Dungeon.curY <= 1)
						{
							System.out.println("Hero cannot move up/North");
						}
						else
						{
							Dungeon.curY--;
							doRoomTask();
							
						}
					}
					else if(choice.equals("E"))
					{
						if(Dungeon.curX <= 1)
						{
							System.out.println("Hero cannot move left/East");
						}
						else
						{
							Dungeon.curX--;
							doRoomTask();
							
						}
					}
					else if(choice.equals("W"))
					{
						if(Dungeon.curX >= 5)
						{
							System.out.println("Hero cannot move right/West");
						}
						else
						{
							Dungeon.curX++;
							doRoomTask();
					
						}
					}
					else if(choice.equals("S"))
					{
						if(Dungeon.curY >= 5)
						{
							System.out.println("Hero cannot move down/South");
						}
						else
						{
							Dungeon.curY++;
							doRoomTask();
							
						}
						
					}
					else if(choice.equals("P"))
					{
						if(theHero.numHealPotions < 1)
						{
							System.out.println("Hero has no more Health Potions");
						}
						else
						{
							theHero.useHealthPotions();	
						}
					}
					Dungeon.printlocation();
					
				}

	}


		private static void doRoomTask() {
			int curX = Dungeon.curX;
			int curY = Dungeon.curY;
			if(Dungeon.playGround[curX][curY].roomDescription.equals("E"))
			{
				System.out.println("You Have Encountered An Empty Room");	
			}
			if(Dungeon.playGround[curX][curY].roomDescription.equals("X"))
			{
				inBattle = true;
				System.out.println("You Have Encountered A Monster And Now Must Fight!\n");
				theMonster = generateRandomMonster();
				battle();
				inBattle = false;
				Dungeon.playGround[curX][curY].roomDescription = "E";
			}
			if(Dungeon.playGround[curX][curY].roomDescription.equals("P"))
			{
				int damageGiven;
				damageGiven = (int)(Math.random() * 19) + 1;
				
				
				if(theHero.hitPoints - damageGiven > 0) 
				{
					theHero.hitPoints = theHero.hitPoints - damageGiven;
					System.out.println("You Have Encountered A Pit And Lose " + damageGiven +" HealthPoints Leaving The Hero With " + theHero.hitPoints + " HealthPoints Left\n");
				}
			    else {
					theHero.hitPoints = 0;
					String choice;
					System.out.println("You have died by Pit fall\nPlay Again? y/n\n");
					@SuppressWarnings("resource")
					Scanner kb = new Scanner(System.in);
					choice = kb.nextLine();
					String [] args = null;
					if(choice.equals("y") || choice.contentEquals("Y"))
					{
						main(args);
					}
					else
					{
						System.out.println("GOODBYE");
						endGame = true;
					}
				}
			

			}
			if(Dungeon.playGround[curX][curY].roomDescription.equals("H"))
			{
				theHero.numHealPotions++;
				System.out.println("You Have Encountered A Health Potion And Now Have " + theHero.numHealPotions +" Health Potions\n");
				Dungeon.playGround[curX][curY].roomDescription = "E";
			}
			if(Dungeon.playGround[curX][curY].roomDescription.equals("F"))
			{
				theHero.numOfPillars++;
				String[] pillars = {"Abstraction","Polymorphism","Inheritance","Encapsulation"};
				if(theHero.numOfPillars <4)
				{
				System.out.println("You Have Encountered The " + pillars[theHero.numOfPillars-1]+ " OO Pillar! Only " + (4-theHero.numOfPillars) +" Left\n");
				Dungeon.playGround[curX][curY].roomDescription = "E";
				}
				else
				{
					System.out.println("Well Done, You've Found The Last OO Pillar " + pillars[theHero.numOfPillars-1]+ ", Now Find The Exit");
					Dungeon.playGround[curX][curY].roomDescription = "E";
				}
			}
			if(Dungeon.playGround[curX][curY].roomDescription.equals("O"))
			{
				if(theHero.numOfPillars <4)
				{
				System.out.println("You Must Find " + (4-theHero.numOfPillars) +" More Pillars Before Leaving");
				}
				else
				{
					System.out.println("Well Done, You've Found All The Pillars And The Exit, You've Won!");
					//END GAME HERE?
					String choice;
					System.out.println("Play Again? y/n");
					@SuppressWarnings("resource")
					Scanner kb = new Scanner(System.in);
					choice = kb.nextLine();
					String [] args = null;
						if(choice.equals("y") || choice.contentEquals("Y"))
						{
							main(args);
						}
						else
						{
							System.out.println("GOODBYE");
							endGame = true;
						}
					}
				}
			}
		

		private static Hero userChooseHero()
		{
			int choice;
			
			System.out.println("Choose a hero:\n" +
						       "1. Warrior\n" +
							   "2. Sorceress\n" +
							   "3. Thief");
			@SuppressWarnings("resource")
			Scanner kb = new Scanner(System.in);
			choice = kb.nextLine().charAt(0);
			switch(choice)
			{
				case '1':
					{
					 theHero = HeroFactory.createWarrior(); 
					return theHero;
					}

				case '2': 
					{
					    theHero =  HeroFactory.createSorceress();
						return theHero;
					}

				case '3':
					{
						theHero =  HeroFactory.createThief();
						return theHero;
					}

				default: 
					{
						System.out.println("invalid choice, returning Thief");
					}
					
						theHero =  new Thief();
						return theHero;
					
			}
			
		}//end chooseHero method
		
		
		public static Monster generateRandomMonster()
		{
			int choice;

			choice = (int)(Math.random() * 3) + 1;

			switch(choice)
			{
				case 1: return new Ogre();

				case 2: return new Gremlin();

				case 3: return new Skeleton();

				default: System.out.println("invalid choice, returning Skeleton");
					     return new Skeleton();
			}//end switch
		}//end generateMonster method
		
		
		public static boolean playAgain()
		{
			char again;
			@SuppressWarnings("resource")
			Scanner kb = new Scanner(System.in);
			System.out.println("Play again (y/n)?");
			again = kb.nextLine().charAt(0);
			
			return (again == 'Y' || again == 'y');
		}//end playAgain method
		
		
		public static void battle()
		{
			
			char pause = 'p';
			System.out.println(theHero.getName() + " battles " +
								theMonster.getName());
			System.out.println("---------------------------------------------");

			//do battle
			while (theHero.isAlive() && theMonster.isAlive() && pause != 'q')
			{
			    //hero goes first
				theHero.battleChoices(theMonster);

				//monster's turn (provided it's still alive!)
				if (theMonster.isAlive())
				    theMonster.attack(theHero);

				//let the player bail out if desired
				System.out.print("\n-->q to quit, anything else to continue: ");
				@SuppressWarnings("resource")
				Scanner kb = new Scanner(System.in);
				if(kb.hasNext())
				pause = kb.nextLine().charAt(0);

			}//end battle loop

			if (!theMonster.isAlive() & !endGame) {
				int chanceToDrop;
			    chanceToDrop = (int)(Math.random() * 4);
			    if(chanceToDrop <= 2)
			    {
			    	System.out.println("The Monster Dropped A Health Potion, Awarding The Hero With A Healing Potion");
			    	theHero.numHealPotions++;
			    }
			   
			    System.out.println(theHero.getName() + " Was Victorious!");
			    Dungeon.playGround[Dungeon.curX][Dungeon.curY].roomDescription = "E";
			    Dungeon.printlocation();
			}
			    else if (!theHero.isAlive())
			    {
					System.out.println(theHero.getName() + " Was Defeated, And You Lost The OO Dungeon Game");
					String choice;
					System.out.println("Play Again? y/n");
					@SuppressWarnings("resource")
					Scanner kb = new Scanner(System.in);
					choice = kb.nextLine();
					String [] args = null;
						if(choice.equals("y") || choice.contentEquals("Y"))
						{
							main(args);
						}
						else
						{
							System.out.println("GOODBYE");
							endGame = true;
						}
			    }
			    else//both are alive so user quit the game
				System.out.println("Quitters Never Win");

		}//end battle method
}
