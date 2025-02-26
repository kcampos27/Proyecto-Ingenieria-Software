package proyecto;

public class Bomberman extends Contenido{
	private static Bomberman miBomberman;
	
	private Bomberman()
	{
		super("bomberman");
	}
	
	public static Bomberman getBomberman()
	{
		return miBomberman;
	}

}
