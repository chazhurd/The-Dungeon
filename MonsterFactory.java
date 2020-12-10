package dungeon;

public class MonsterFactory {
	public static Monster createOgre()
	{
		Monster ogre = new Ogre();
		return ogre;
	}
	public static Monster createGremlin()
	{
		Monster grem = new Gremlin();
		return grem;
	}
	public static Monster createSkeleton()
	{
		Monster skele = new Skeleton();
		return skele;
	}
}
