package dungeon;

public class HeroFactory {
	
	public static Hero createWarrior()
	{
		Hero warrior = new Warrior();
		return warrior;
	}
	public static Hero createSorceress()
	{
		Hero sorceress = new Sorceress();
		return sorceress;
	}
	public static Hero createThief()
	{
		Hero thief = new Thief();
		return thief;	
	}
}
