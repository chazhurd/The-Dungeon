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

public class Thief extends Hero
{
	private DungeonCharacter opponent;
	
    public Thief()
	{
		super("Thief", 75, 6, .8, 20, 40, .5);



    }//end constructor


    public void battleChoices(DungeonCharacter opponent)
	{
		super.battleChoices(opponent);
		int choice;
		setOpponent(opponent);
		


		do
		{
		    System.out.println("1. Attack Opponent");
		    System.out.println("2. Surprise Attack");
		    System.out.println("3. Use Health Potion");
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

    }

	private void setOpponent(DungeonCharacter opponent2) {
		this.opponent =  opponent2;
		
	}


	@Override
	public void doSpecial() {
	
		double surprise = Math.random();
		if (surprise <= .4)
		{
			System.out.println("Surprise attack was successful!\n" +
								name + " gets an additional turn.");
			numTurns++;
			attack(opponent);
		}//end surprise
		else if (surprise >= .9)
		{
			System.out.println("Uh oh! " + opponent.getName() + " saw you and" +
								" blocked your attack!");
		}
		else
		    attack(opponent);


	}
}