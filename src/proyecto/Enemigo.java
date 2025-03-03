package proyecto;

public class Enemigo {
	
	String nombre;
	int x,y;
	
	public Enemigo(String pName, int pX, int pY)
	{
		x = pX;
		y = pY;
		nombre = pName;
	}
	
	public String getName()
	{
		return nombre;
	}
}
