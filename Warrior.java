package dungeon;
import java.util.Scanner;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */




public class Warrior extends Hero
{
	private DungeonCharacter opponent;

    public Warrior()
	{

		super("Warrior", 125, 4, .8, 35, 60, .2);


    }//end constructor


	private void setOpponent(DungeonCharacter opponent2) {
		this.opponent = opponent2;
		
	}


	public void attack(DungeonCharacter opponent)
	{
		System.out.println(name + " swings a mighty sword at " +
							opponent.getName() + ":");
		super.attack(opponent);
	}//end override of attack method




    public void battleChoices(DungeonCharacter opponent)
	{
		int choice;
		setOpponent(opponent);
		super.battleChoices(opponent);

		do
		{
		    System.out.println("1. Attack Opponent");
		    System.out.println("2. Crushing Blow on Opponent");
		    System.out.println("3. Use Healing Potion");
		    System.out.print("Choose an option: ");
		    @SuppressWarnings("resource")
			Scanner kb = new Scanner(System.in);
		    choice = kb.nextInt();
		    

		    switch (choice)
		    {
			    case 1: attack(opponent);
			        break;
			    case 2: doSpecial();
			        break;
			    case 3: useHealthPotions();
			    	battleChoices(opponent);
			    	break;
			    default:
			        System.out.println("invalid choice!");
		    }//end switch

			numTurns--;
			if (numTurns > 0)
			    System.out.println("Number of turns remaining is: " + numTurns);

		} while(numTurns > 0);

    }//end battleChoices method


	@Override
	public void doSpecial() {

		setOpponent(this.opponent);
		if (Math.random() <= .4)
		{
			int blowPoints = (int)(Math.random() * 76) + 100;
			System.out.println(name + " lands a CRUSHING BLOW for " + blowPoints
								+ " damage!");
			this.opponent.subtractHitPoints(blowPoints);
		}//end blow succeeded
		else
		{
			System.out.println(name + " failed to land a crushing blow");
			System.out.println();
		}//blow failed
		
	}

}//end Hero class